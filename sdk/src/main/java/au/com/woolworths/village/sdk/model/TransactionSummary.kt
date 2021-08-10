package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime
import java.io.Serializable
import java.math.BigDecimal

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

    enum class SummaryRollback {
        REQUIRED,
        NOT_REQUIRED,
        FAILED,
        SUCCESSFUL
    }

    /**
     * An instrument used for a transaction
     */
    interface UsedPaymentInstrument : Serializable {
    /** The ID of the [PaymentInstrument] */
        val paymentInstrumentId: String

        /** The type of the payment instrument */
        val instrumentType: String

        /** The list of transactions associated with the instrument." */
        val transactions: List<UsedPaymentInstrumentTransaction>
    }

    /**
     * A subtransaction associated with a payment instrument
     */
    interface UsedPaymentInstrumentTransaction : Serializable {
        /** The type of transaction. */
        val type: PaymentType?

        /** Timestamp of when the transaction occurred */
        val executionTime: OffsetDateTime?

        /** The reference for the payment. If a refund this is the reference to the transaction being refunded. */
        val paymentTransactionRef: String?

        /** The reference for the refund. */
        val refundTransactionRef: String?

        /** The current status of the transactions */
        val status: PaymentStatus?

        /** The amount charged against or refunded to this instrument */
        val amount: BigDecimal?
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

    /** The rollback state of this transaction */
    val rollback: SummaryRollback?

    /** Array of transaction responses returned by downstream processes */
    val subTransactions: List<Any>?

    /** The reason provided for the refund. Only provided for REFUND transactions */
    val refundReason: String?

    /** The instruments used to make the payment. For refunds and cash back amounts will be negative */
    val instruments: List<UsedPaymentInstrument>
}