package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

interface Basket: Serializable {
    val items: List<Items>

    interface Items: Serializable {
        val label: String
        val description: String?
        val quantity: BigDecimal?
        val unitPrice: BigDecimal?
        val unitMeasure: String?
        val totalPrice: BigDecimal?
        val tags: Map<String, String>
    }
}

