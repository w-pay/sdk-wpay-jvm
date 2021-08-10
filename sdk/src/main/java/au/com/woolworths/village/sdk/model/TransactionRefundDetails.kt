package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Request payload containing the refund reason and instructions
 */
interface TransactionRefundDetails : Serializable {
    /** The reason for the refund, or other message logged with the transaction */
    val reason: String

    /**
     * An optional client reference to be associated with the transaction.
     *
     * If not supplied the transactionId will be used.
     */
    val clientReference: String?
}