package au.com.woolworths.village.app.api

import au.com.woolworths.village.app.BasketItem
import au.com.woolworths.village.app.Payment
import au.com.woolworths.village.app.PaymentInstrument
import java.math.BigDecimal

class PaymentService {
    suspend fun retrievePaymentDetails(paymentId: String): Payment {
        return Payment().apply {
            amount = BigDecimal("26.00")
            instrument = PaymentInstrument("Debit Card", "1426")
            basket.apply {
                items.add(BasketItem("WW Creamy Pumpkin Soup", BigDecimal("3")))
                items.add(BasketItem("Cheese and Chive Triangle Single", BigDecimal("2.33")))
                items.add(BasketItem("Dairy Farmers Daily 2L", BigDecimal("4.6")))
                items.add(BasketItem("Primo TSMK Bacon 200g", BigDecimal("7.85")))
                items.add(BasketItem("Gourmet Tomatoes per kg 0.100 kg NET @ $6.90/kg", BigDecimal("0.69")))
            }
            total = BigDecimal("18.47")
            tax = BigDecimal("0.72")
        }
    }
}