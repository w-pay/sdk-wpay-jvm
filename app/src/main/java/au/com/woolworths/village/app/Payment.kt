package au.com.woolworths.village.app

import java.io.Serializable
import java.math.BigDecimal

// TODO: Flesh out
class Payment: Serializable {
    var amount: BigDecimal = BigDecimal.ZERO
    var instrument: PaymentInstrument? = null
}

class PaymentInstrument(val type: String, val lastFour: String): Serializable {

}