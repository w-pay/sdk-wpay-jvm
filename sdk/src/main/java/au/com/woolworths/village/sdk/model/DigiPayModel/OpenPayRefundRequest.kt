package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Openpay Refunds endpoint.
 *
 * @category Model
 */
interface OpenPayRefundRequest : Serializable {
    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String

    /** The merchant order number of the transaction. */
    val orderNumber: String?

    /** The merchants transaction date and time. The timestamp format is ISO8601. */
    val merchantTransactedAt: String?

    /** List of refunds */
    val refunds: List<OpenPayRefund>

    val storeData: StoreData?
}

interface StoreData {
    /** The refund transaction store id. */
    val storeId: String
}

interface OpenPayRefund : Serializable {
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the payment transaction in the container.
     */
    val paymentTransactionRef: String

    /** The amount you want to refund. */
    val amount: BigDecimal

    /** The GST amount of the amount you want to refund. */
    val gstAmount: BigDecimal?

    /** The reason or justification for the refund. */
    val reason: String?
}
