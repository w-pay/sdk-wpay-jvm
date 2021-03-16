package au.com.woolworths.village.sdk.model.digitalpay

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the OpenPay Completions endpoint.
 *
 * @category Model
 */
interface OpenPayCompletionRequest : Serializable {
    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String

    /** The merchant order number of the transaction. */
    val orderNumber: String

    /** The merchants transaction date and time. The timestamp format is ISO8601. */
    val merchantTransactedAt: String

    /** List of completions */
    val completions: List<OpenPayCompletion>
}

interface OpenPayCompletion : Serializable {
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the credit card transaction in the container.
     */
    val paymentTransactionRef: String

    /** The amount you want to process in the completion. */
    val amount: BigDecimal

    /** The GST amount of the amount you want to process in the completion. */
    val gstAmount: BigDecimal?
}
