package au.com.woolworths.village.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import au.com.woolworths.village.app.databinding.PaymentReceiptBinding
import java.nio.charset.Charset
import java.text.NumberFormat

const val PAYMENT = "au.com.woolworths.village.app.Payment"

class PaymentReceipt : AppCompatActivity() {
    private lateinit var bindings: PaymentReceiptBinding

    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindings = PaymentReceiptBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        val payment: Payment = intent.getSerializableExtra(PAYMENT) as Payment

        bindings.amountPaid.text = currencyFormat.format(payment.amount)
        bindings.paymentInstrument.text = toUtf8("${payment.instrument!!.type} \u00E2\u0080\u00A2\u00E2\u0080\u00A2\u00E2\u0080\u00A2\u00E2\u0080\u00A2 ${payment.instrument!!.lastFour}")
    }

    private fun toUtf8(str: String) =
        String(str.toByteArray(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"))
}