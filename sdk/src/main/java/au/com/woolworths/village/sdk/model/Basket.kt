package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

interface Basket: Serializable {
    fun items(): List<Items>

    interface Items: Serializable {
        fun label(): String
        fun description(): String?
        fun quantity(): BigDecimal?
        fun unitPrice(): BigDecimal?
        fun unitMeasure(): String?
        fun totalPrice(): BigDecimal?
        fun tags(): Map<String, String>
    }
}

