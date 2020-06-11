package au.com.woolworths.village.app

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import au.com.woolworths.village.sdk.dto.MakeCustomerPaymentResults
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute
import com.microsoft.appcenter.distribute.UpdateTrack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

import java.text.NumberFormat
import kotlin.math.roundToInt

class PaymentConfirm : AppCompatActivity() {
    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()

    private lateinit var data: ViewModel
    private lateinit var bindings: PaymentConfirmBinding

    private var animationDuration: Long = 0
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!AppCenter.isConfigured() ) {
            Distribute.setUpdateTrack(UpdateTrack.PRIVATE)
            Distribute.setEnabledForDebuggableBuild(true)

            AppCenter.start(application, getString(R.string.app_center_secret),
                Analytics::class.java,
                Crashes::class.java,
                Distribute::class.java)
        }

        animationDuration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        createViewModel()
        createView()

        // can't pay until we have the payment instruments.
        bindings.slideToPay.lock()
    }

    override fun onDestroy() {
        dialog?.dismiss()

        super.onDestroy()
    }

    fun makePayment() {
        applyMakingPaymentAnimations()

        bindings.action.text = getString(R.string.paying)

        data.makePayment()
        Handler().postDelayed(this::paymentComplete, 2000)
    }

    private fun paymentComplete() {
        data.paymentResult.value ?: run {
            /*
             * The payment hasn't completed yet so we need to wait
             */
            data.paymentResult.observe(this, Observer { paymentComplete() })
        }

        val paymentData = data

        when(data.paymentRequest.value) {
           is ApiResult.Success -> {
               val intent = Intent(this, PaymentReceipt::class.java).apply {
                   putExtra(PAYMENT, paymentData.paymentRequestDetails)
                   putExtra(INSTRUMENT, paymentData.selectedPaymentInstrument)
               }

               startActivity(intent)

               finish()
           }

            is ApiResult.Error -> {
                showErrorDialog(R.string.payment_failed)
            }
       }
    }

    private fun createViewModel() {
        data = ViewModelProvider(this).get(ViewModel::class.java)

        data.paymentRequest.observe(this, Observer { bindPaymentRequestDetails() })
        data.paymentInstruments.observe(this, Observer { bindPaymentInstrument() })
        data.qrCodeId.observe(this, Observer {
            it?.let {
                data.retrievePaymentDetails(it)
                data.retrievePaymentInstruments()
            } ?: showMissingDetailsError(R.string.missing_qr_code)
        })

        intent?.let { data.parseQRCodeDetails(it.data) } ?: run { data.qrCodeId.value = null }
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
                is ApiResult.Success -> {
                    bindings.amountToPay.text = currencyFormat.format(it.value.grossAmount)
                }

                is ApiResult.Error -> {
                    showMissingDetailsError(R.string.payment_retrieve_details_error)
                }
            }
        }
    }

    private fun bindPaymentInstrument() {
        // TODO: We pick a card be default.
        data.paymentInstruments.value?.let {
            when (it) {
                is ApiResult.Success -> {
                    bindings.slideToPay.unlock()
                }

                is ApiResult.Error ->
                    showMissingDetailsError(R.string.payment_instruments_retrieve_error)
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

    private fun showMissingDetailsError(messageId: Int) {
        bindings.amountToPay.text = "???"

        showErrorDialog(messageId)
    }

    private fun showErrorDialog(messageId: Int) {
        if (dialog == null) {
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

            dialog = builder?.create()
            dialog?.show()
        }
        else {
            Log.w("PaymentConfirm", "Trying to show error dialog but already shown")
        }
    }
}

class ViewModel : androidx.lifecycle.ViewModel() {
    private val paymentService: PaymentService = PaymentService()

    val qrCodeId: MutableLiveData<String?> = MutableLiveData()
    val paymentRequest: MutableLiveData<ApiResult<CustomerPaymentDetail>> = MutableLiveData()
    val paymentInstruments: MutableLiveData<ApiResult<GetCustomerPaymentInstrumentsResultsData>> = MutableLiveData()
    val paymentResult: MutableLiveData<ApiResult<MakeCustomerPaymentResults>> = MutableLiveData()

    lateinit var paymentRequestDetails: CustomerPaymentDetail
    lateinit var selectedPaymentInstrument: GetCustomerPaymentInstrumentsResultsDataCreditCards

    fun makePayment() {
        viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 paymentResult.postValue(paymentService.makePayment(
                     paymentRequestDetails.paymentRequestId,
                     selectedPaymentInstrument.paymentInstrumentId
                 ))
             }
        }
    }

    fun retrievePaymentDetails(qrCodeId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = paymentService.retrievePaymentRequestDetails(qrCodeId)

                when (result) {
                    is ApiResult.Success -> paymentRequestDetails = result.value
                }

                paymentRequest.postValue(result)
            }
        }
    }

    fun parseQRCodeDetails(qrCodeContents: Uri?) {
        if (qrCodeContents != null) {
            setHostname(qrCodeContents)

            qrCodeId.value = qrCodeContents.pathSegments.last()
        }
        else {
            qrCodeId.value = null
        }
    }

    private fun setHostname(qrCodeContents: Uri) {
        val port: String = if (qrCodeContents.port > -1) ":${qrCodeContents.port}" else ""
        val hostname = "${qrCodeContents.scheme}://${qrCodeContents.host}${port}"

        paymentService.setHost(hostname)
    }

    fun retrievePaymentInstruments() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var result = paymentService.retrievePaymentInstruments()

                when (result) {
                    is ApiResult.Success -> {
                        if (result.value.creditCards.isEmpty()) {
                            result = ApiResult.Error(Exception("No payment instruments"))
                        }
                        else {
                            selectedPaymentInstrument = result.value.creditCards[0]
                        }
                    }
                }

                paymentInstruments.postValue(result)
            }
        }
    }
}
