package au.com.woolworths.village.app

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import au.com.woolworths.village.app.api.PaymentService

import au.com.woolworths.village.app.databinding.PaymentConfirmBinding
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
    }

    fun makePayment() {
        applyMakingPaymentAnimations()

        bindings.action.text = getString(R.string.paying)

        Handler().postDelayed(this::paymentComplete, 3000)
    }

    private fun paymentComplete() {
        val payment: Payment = data.payment.value!!

        val intent = Intent(this, PaymentReceipt::class.java).apply {
            putExtra(PAYMENT, payment)
        }

        startActivity(intent)
    }

    private fun createViewModel() {
        data = ViewModelProvider(this).get(ViewModel::class.java)

        data.payment.observe(this, Observer { bindPayment() })
    }

    private fun createView() {
        bindings = PaymentConfirmBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        bindPayment()
        bindSlideToPayListener()
        updateSheetBehaviour()
    }

    private fun bindPayment() {
        data.payment.value?.let {
            bindings.amountToPay.text = currencyFormat.format(it.amount)
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

}

class ViewModel : androidx.lifecycle.ViewModel() {
    private val paymentService: PaymentService = PaymentService();

    val payment: MutableLiveData<Payment> = MutableLiveData()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val paymentDetails = paymentService.retrievePaymentDetails("")

                payment.postValue(paymentDetails)
            }
        }
    }
}
