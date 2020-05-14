package au.com.woolworths.village.app

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import au.com.woolworths.village.app.databinding.PaymentConfirmBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback

import java.math.BigDecimal
import java.text.NumberFormat
import kotlin.math.roundToInt

class PaymentConfirm : AppCompatActivity() {
    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()
    private val payment = Payment().apply {
        amount = BigDecimal("26.00")
        instrument = PaymentInstrument("Debit Card", "1426")
        basket.apply {
            this.items.add(BasketItem("WW Creamy Pumpkin Soup", BigDecimal("3")))
            this.items.add(BasketItem("Cheese and Chive Triangle Single", BigDecimal("2.33")))
            this.items.add(BasketItem("Dairy Farmers Daily 2L", BigDecimal("4.6")))
            this.items.add(BasketItem("Primo TSMK Bacon 200g", BigDecimal("7.85")))
            this.items.add(BasketItem("Gourmet Tomatoes per kg 0.100 kg NET @ $6.90/kg", BigDecimal("0.69")))
        }
        total = BigDecimal("18.47")
        tax = BigDecimal("0.72")
    }

    private lateinit var bindings: PaymentConfirmBinding
    private var animationDuration: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animationDuration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        createView()
        bindPayment()
    }

    fun makePayment() {
        applyAnimations()

        bindings.action.text = getString(R.string.paying)
        bindings.paymentComplete.visibility = View.VISIBLE
    }

    @Suppress("UNUSED_PARAMETER")
    fun paymentComplete(button: View) {
        val intent = Intent(this, PaymentReceipt::class.java).apply {
            putExtra(PAYMENT, payment)
        }

        startActivity(intent)
    }

    private fun createView() {
        bindings = PaymentConfirmBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        updateSheetBehaviour()
        bindSlideToPayListener()
    }

    private fun bindPayment() {
        bindings.amountToPay.text = currencyFormat.format(payment.amount)
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

    private fun applyAnimations() {
        /*
         * If we fade the layout, the contents also disappears. So fade the background instead
         */
        val paymentMethodBackground: Drawable = bindings.paymentMethod.background

        val slideToPayFade = ObjectAnimator.ofFloat(bindings.slideToPay.bindings.background, "alpha", 1f, 0f).apply {
            duration = animationDuration
        }

        val paymentMethodFade = ValueAnimator.ofFloat(1f, 0f).apply {
            duration = animationDuration

            addUpdateListener { animation ->
                paymentMethodBackground.alpha = 255.times(animation.animatedValue as Float).roundToInt()
            }
        }

        val animation = AnimatorSet().apply {
            play(slideToPayFade).with(paymentMethodFade)
        }

        animation.start()
    }
}
