package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Refunds endpoint.
 *
 * @category Model
 */
interface DigitalPayRefundRequest : Serializable {
    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String

    /** The merchant order number of the transaction. */
    val orderNumber: String

    /** List of refunds */
    val refunds: List<DigitalPayRefund>
}

interface DigitalPayRefund : Serializable {
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the payment transaction in the container.
     */
    val paymentTransactionRef: String

    /** The amount you want to refund. */
    val amount: BigDecimal
}
