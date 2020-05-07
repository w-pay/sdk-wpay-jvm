package au.com.woolworths.village.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import au.com.woolworths.village.app.databinding.PaymentConfirmBinding

import java.math.BigDecimal
import java.text.NumberFormat

class PaymentConfirm : AppCompatActivity() {
    private lateinit var bindings: PaymentConfirmBinding

    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()
    private val payment = Payment().apply {
        amount = BigDecimal("26.00")
        instrument = PaymentInstrument("Debit Card", "1426")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindings = PaymentConfirmBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        bindings.amountToPay.text = currencyFormat.format(payment.amount)
    }

    fun makePayment(button: View) {
        bindings.action.text = getString(R.string.paying)

        button.visibility = View.GONE
        bindings.paymentComplete.visibility = View.VISIBLE
    }

    @Suppress("UNUSED_PARAMETER")
    fun paymentComplete(button: View) {
        val intent = Intent(this, PaymentReceipt::class.java).apply {
            putExtra(PAYMENT, payment)
        }

        startActivity(intent)
    }
}
