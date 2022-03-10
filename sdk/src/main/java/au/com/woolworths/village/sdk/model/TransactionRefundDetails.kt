package au.com.woolworths.village.sdk.model

import kotlinx.serialization.Serializable

/**
 * Request payload containing the refund reason and instructions
 */
@Serializable
data class TransactionRefundDetails(
    /** The reason for the refund, or other message logged with the transaction */
    val reason: String,

    /**
     * An optional client reference to be associated with the transaction.
     *
     * If not supplied the transactionId will be used.
     */
    val clientReference: String? = null
) : ModelType