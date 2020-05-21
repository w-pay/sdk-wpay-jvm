package au.com.woolworths.village.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import au.com.woolworths.village.app.databinding.PaymentReceiptBinding
import au.com.woolworths.village.sdk.dto.BasketItems
import au.com.woolworths.village.sdk.dto.CustomerPaymentDetail
import kotlinx.android.synthetic.main.receipt_row.view.*
import java.math.BigDecimal
import java.nio.charset.Charset
import java.text.NumberFormat

const val PAYMENT = "au.com.woolworths.village.app.Payment"
const val INSTRUMENT = "au.com.woolworths.village.app.Instrument"

class PaymentReceipt : AppCompatActivity() {
    private lateinit var bindings: PaymentReceiptBinding
    private lateinit var basketItemsAdapter: RecyclerView.Adapter<*>
    private lateinit var basketItemsManager: RecyclerView.LayoutManager

    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindings = PaymentReceiptBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        val payment: CustomerPaymentDetail = intent.getSerializableExtra(PAYMENT) as CustomerPaymentDetail
        basketItemsAdapter = payment.basket?.items?.let { BasketItemsAdapter(it) }!!
        basketItemsManager = LinearLayoutManager(this)

        bindings.amountPaid.text = currencyFormat.format(payment.grossAmount)
        bindings.paymentInstrument.text = toUtf8("Credit Card \u00E2\u0080\u00A2\u00E2\u0080\u00A2\u00E2\u0080\u00A2\u00E2\u0080\u00A2 1234")
        bindings.basketItems.apply {
            layoutManager = basketItemsManager
            adapter = basketItemsAdapter
        }

        bindings.totalRow.item.text = "${payment.basket?.items?.size} items"
        bindings.totalRow.amount.text = currencyFormat.format(payment.grossAmount)

        bindings.taxRow.item.text = "Including GST"
        bindings.taxRow.amount.text = currencyFormat.format(BigDecimal.ZERO)
    }

    private fun toUtf8(str: String) =
        String(str.toByteArray(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"))

    class BasketItemsAdapter(private val items: List<BasketItems>) : RecyclerView.Adapter<BasketItemsAdapter.ItemViewHolder>() {
        class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view)

        private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.receipt_row, parent, false)

            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = items[position]

            holder.itemView.item.text = item.label
            holder.itemView.amount.text = currencyFormat.format(item.totalPrice)
        }

        override fun getItemCount(): Int = items.size
    }
}