package au.com.wpay.sdk.model

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.ISODateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import kotlinx.serialization.json.JsonObject
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

/**
 * Summary information of a transaction
 */
@Suppress("EXPERIMENTAL_API_USAGE")
interface TransactionSummaryType : PaymentType {
    /**
     * Allowed types of transactions
     */
    @Serializable
    enum class PaymentType {
        /** A payment by a customer to a merchant */
        @SerialName("PAYMENT")
        @JsonNames("payment")
        PAYMENT,

        /** A payment by a merchant to a customer undoing a previously made customer payment */
        @SerialName("REFUND")
        @JsonNames("refund")
        REFUND,

        /** A preauthorised amount is reserved but will not be taken from a customer's account until a completion is performed */
        @SerialName("PREAUTH")
        @JsonNames("preauth")
        PREAUTH,

        /** Complete a preauthorised transaction and take the amount from the customer's account */
        @SerialName("COMPLETION")
        @JsonNames("completion")
        COMPLETION,

        /** Void a preauthorised transaction so that the money will not be taken from the customer's account */
        @SerialName("VOID")
        @JsonNames("void")
        VOID
    }

    /**
     * Allowed states that a transaction can be in
     */
    @Serializable
    enum class PaymentStatus {
        /** The transaction is being processed */
        @SerialName("PROCESSING")
        @JsonNames("processing")
        PROCESSING,

        /** The transaction was approved */
        @SerialName("APPROVED")
        @JsonNames("approved")
        APPROVED,

        /** The transaction was rejected */
        @SerialName("REJECTED")
        @JsonNames("rejected")
        REJECTED,
    }

    @Serializable
    enum class SummaryRollback {
        @SerialName("REQUIRED")
        @JsonNames("required")
        REQUIRED,

        @SerialName("NOT_REQUIRED")
        @JsonNames("not_required")
        NOT_REQUIRED,

        @SerialName("FAILED")
        @JsonNames("failed")
        FAILED,

        @SerialName("SUCCESSFUL")
        @JsonNames("successful")
        SUCCESSFUL
    }

    /**
     * An instrument used for a transaction
     */
    @Serializable
    data class UsedPaymentInstrument(
        /** The ID of the [PaymentInstrument] */
        val paymentInstrumentId: String,

        /** The type of the payment instrument */
        val instrumentType: String,

        /** The list of transactions associated with the instrument." */
        val transactions: List<UsedPaymentInstrumentTransaction>
    ) : ModelType

    /**
     * A subtransaction associated with a payment instrument
     */
    @Serializable
    data class UsedPaymentInstrumentTransaction(
        /** The type of transaction. */
        val type: PaymentType? = null,

        /** Timestamp of when the transaction occurred */
        @Serializable(with = ISODateSerializer::class)
        val executionTime: OffsetDateTime? = null,

        /** The reference for the payment. If a refund this is the reference to the transaction being refunded. */
        val paymentTransactionRef: String? = null,

        /** The reference for the refund. */
        val refundTransactionRef: String? = null,

        /** The current status of the transactions */
        val status: PaymentStatus? = null,

        /** The amount charged against or refunded to this instrument */
        @Serializable(with = CurrencySerializer::class)
        val amount: BigDecimal?
    ) : ModelType

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

    /**
     * Array of transaction responses returned by downstream processes
     *
     * Due the payload not having a type in the API spec, it is "dynamically" typed.
     */
    val subTransactions: List<JsonObject>?

    /** The reason provided for the refund. Only provided for REFUND transactions */
    val refundReason: String?

    /** The instruments used to make the payment. For refunds and cash back amounts will be negative */
    val instruments: List<UsedPaymentInstrument>
}
