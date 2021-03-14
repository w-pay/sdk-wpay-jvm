package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * Basket of items associated with a payment request.
 */
interface Basket : Serializable {
    /** Items in the basket */
    val items: List<Items>

    /**
     * An item in a [Basket]
     */
    interface Items : Serializable {
        /** Short label for the basket item. */
        val label: String

        /** Longer description of the basket item. */
        val description: String?

        /** The number of units of the item in the basket if multiple is possible. */
        val quantity: BigDecimal?

        /** The unit price of the item. May be positive or negative. */
        val unitPrice: BigDecimal?

        /** Optional display string for the measure of the unit. */
        val unitMeasure: String?

        /** The total price of the item. May be positive or negative. */
        val totalPrice: BigDecimal?

        /** Additional key/value pairs for the item defined by the merchant. */
        val tags: Map<String, String>
    }
}

