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
