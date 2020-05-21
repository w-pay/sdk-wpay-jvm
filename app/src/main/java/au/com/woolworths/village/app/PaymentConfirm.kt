package au.com.woolworths.village.app

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import au.com.woolworths.village.app.api.ApiResult
import au.com.woolworths.village.app.api.PaymentService

import au.com.woolworths.village.app.databinding.PaymentConfirmBinding
import au.com.woolworths.village.sdk.dto.CustomerPaymentDetail
import au.com.woolworths.village.sdk.dto.GetCustomerPaymentInstrumentsResultsData
import au.com.woolworths.village.sdk.dto.GetCustomerPaymentInstrumentsResultsDataCreditCards
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.text.NumberFormat
import kotlin.math.roundToInt

class PaymentConfirm : AppCompatActivity() {
    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()

    private lateinit var data: ViewModel

    private lateinit var bindings: PaymentConfirmBinding
    private var animationDuration: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animationDuration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        createViewModel()
        createView()

        // can't pay until we have the payment instruments.
        bindings.slideToPay.lock()
    }

    fun makePayment() {
        applyMakingPaymentAnimations()

        bindings.action.text = getString(R.string.paying)

        Handler().postDelayed(this::paymentComplete, 3000)
    }

    private fun paymentComplete() {
        when(val result = data.paymentRequest.value) {
           is ApiResult.Success -> {
               val intent = Intent(this, PaymentReceipt::class.java).apply {
                   putExtra(PAYMENT, result.value)
               }

               startActivity(intent)
           }
       }
    }

    private fun createViewModel() {
        data = ViewModelProvider(this).get(ViewModel::class.java)

        data.paymentRequest.observe(this, Observer { bindPaymentRequestDetails() })
        data.paymentInstruments.observe(this, Observer { bindPaymentInstrument() })
    }

    private fun createView() {
        bindings = PaymentConfirmBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        bindPaymentRequestDetails()
        bindSlideToPayListener()
        updateSheetBehaviour()
    }

    private fun bindPaymentRequestDetails() {
        data.paymentRequest.value?.let {
            when (it) {
                is ApiResult.Success ->
                    bindings.amountToPay.text = currencyFormat.format(it.value.grossAmount)
                is ApiResult.Error -> {
                    bindings.amountToPay.text = "???"

                    showApiResultError(R.string.payment_retrieve_details_error)
                }
            }
        }
    }

    private fun bindPaymentInstrument() {
        // TODO: We pick a card be default.
        data.paymentInstruments.value?.let {
            when (it) {
                is ApiResult.Success -> {
                    data.selectedPaymentInstrument = it.value.creditCards[0]
                    bindings.slideToPay.unlock()
                }

                is ApiResult.Error ->
                    showApiResultError(R.string.payment_instruments_retrieve_error)
            }
        }
    }

    private fun updateSheetBehaviour() {
        BottomSheetBehavior.from(bindings.paymentMethod).apply {
            val behavior = this

            addBottomSheetCallback(object : BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {

                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> { }
                        BottomSheetBehavior.STATE_EXPANDED -> { }
                        BottomSheetBehavior.STATE_COLLAPSED -> { }
                        BottomSheetBehavior.STATE_DRAGGING -> {
                            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                        BottomSheetBehavior.STATE_SETTLING -> { }
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> { }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            })
        }
    }

    private fun bindSlideToPayListener() {
        bindings.slideToPay.listener = object : OnSwipedListener {
            override fun onSwiped() {
               makePayment()
            }
        }
    }

    private fun applyMakingPaymentAnimations() {
        /*
         * If we fade the layout, the contents also disappears. So fade the background instead
         */
        val paymentMethodFade = ValueAnimator.ofFloat(1f, 0f).apply {
            duration = animationDuration

            addUpdateListener { animation ->
                bindings.paymentMethod.background.alpha = 255.times(animation.animatedValue as Float).roundToInt()
            }
        }

        val slideToPayFade = fadeFactory(bindings.slideToPay.bindings.background)
        val paymentProgressFade = fadeFactory(bindings.slideToPay.bindings.paymentProgress, 0f, 1f)

        val animation = AnimatorSet().apply {
            play(slideToPayFade)
                .with(paymentMethodFade)
                .with(paymentProgressFade)
        }

        animation.start()
    }

    private fun fadeFactory(view: View, start: Float = 1f, end: Float = 0f): ObjectAnimator {
        view.visibility = View.VISIBLE

        return ObjectAnimator.ofFloat(view, "alpha", start, end).apply {
            duration = animationDuration
        }
    }

    private fun showApiResultError(messageId: Int) {
        when(data.paymentRequest.value) {
            is ApiResult.Error -> {
                val builder: AlertDialog.Builder? = this.let {
                    AlertDialog.Builder(it)
                }

                builder?.apply {
                    setCancelable(false)
                    setMessage(messageId)
                    setTitle(R.string.payment_error_title)

                    setPositiveButton(R.string.ok) { _, _ ->
                        finish()
                    }
                }

                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }
        }
    }
}

class ViewModel : androidx.lifecycle.ViewModel() {
    private val paymentService: PaymentService = PaymentService()

    val paymentRequest: MutableLiveData<ApiResult<CustomerPaymentDetail>> = MutableLiveData()
    val paymentInstruments: MutableLiveData<ApiResult<GetCustomerPaymentInstrumentsResultsData>> = MutableLiveData()
    lateinit var selectedPaymentInstrument: GetCustomerPaymentInstrumentsResultsDataCreditCards

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // TODO: Should parse from QR code.
                paymentService.setHost("http://192.168.1.15:3000")

                // TODO: Payment Id should come from QR code.
                paymentRequest.postValue(paymentService.retrievePaymentRequestDetails("7a104d34-1442-4f02-970c-8ea847533c4b"))
                paymentInstruments.postValue(paymentService.retrievePaymentInstruments())
            }
        }
    }
}
