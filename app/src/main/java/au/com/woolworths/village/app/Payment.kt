package au.com.woolworths.village.app

import java.io.Serializable
import java.math.BigDecimal

// TODO: Flesh out
class Payment: Serializable {
    var amount: BigDecimal = BigDecimal.ZERO
    var instrument: PaymentInstrument? = null
    var basket: Basket = Basket()
    var total: BigDecimal? = null
    var tax: BigDecimal? = null
}

class PaymentInstrument(val type: String, val lastFour: String): Serializable {

}

class Basket: Serializable {
    val items: MutableList<BasketItem> = ArrayList()
}

class BasketItem(val productName: String, val price: BigDecimal): Serializable {

}