package au.com.wpay.sdk.model

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.DecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * Basket of items associated with a payment request.
 */
@Serializable
data class Basket(
    /** Items in the basket */
    val items: List<Item>
) : ModelType {
    /**
     * An item in a [Basket]
     */
    @Serializable
    data class Item(
        /** Short label for the basket item. */
        val label: String,

        /** Longer description of the basket item. */
        val description: String? = null,

        /** The number of units of the item in the basket if multiple is possible. */
        @Serializable(with = DecimalSerializer::class)
        val quantity: BigDecimal? = null,

        /** The unit price of the item. May be positive or negative. */
        @Serializable(with = CurrencySerializer::class)
        val unitPrice: BigDecimal? = null,

        /** Optional display string for the measure of the unit. */
        val unitMeasure: String? = null,

        /** The total price of the item. May be positive or negative. */
        @Serializable(with = CurrencySerializer::class)
        val totalPrice: BigDecimal? = null,

        /** Additional key/value pairs for the item defined by the merchant. */
        val tags: Map<String, String>
    ) : ModelType
}

