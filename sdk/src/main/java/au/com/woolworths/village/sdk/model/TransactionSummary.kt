package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime

/**
 * Summary information of a transaction
 */
interface TransactionSummary : Payment {
    /**
     * Allowed types of transactions
     */
    enum class PaymentType {
        /** A payment by a customer to a merchant */
        PAYMENT,

        /** A payment by a merchant to a customer undoing a previously made customer payment */
        REFUND
    }

    /**
     * Allowed states that a transaction can be in
     */
    enum class PaymentStatus {
        /** The transaction is being processed */
        PROCESSING,

        /** The transaction was approved */
        APPROVED,

        /** The transaction was rejected */
        REJECTED,
    }

    /** The ID of the transaction */
    val transactionId: String

    /** An optional client reference associated with the transaction. */
    val clientReference: String?

    /** The type of transaction. */
    val type: PaymentType

    /** Timestamp of when the transaction occurred */
    val executionTime: OffsetDateTime

    /** The current status of the transactions */
    val status: PaymentStatus

    /** The error detail returned by downstream processes when the payment is REJECTED */
    val statusDetail: Any?

    /** The reason provided for the refund. Only provided for REFUND transactions */
    val refundReason: String?
}