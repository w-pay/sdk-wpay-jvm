package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.ISODateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

interface CustomerTransactions : ModelType

interface CustomerTransactionSummaryType : TransactionSummaryType {
    /** The ID of the merchant associated with this transaction */
    val merchantId: String
}

/**
 * List of customer transactions
 */
@Serializable
data class CustomerTransactionSummaries(
    /** The resulting list of transactions. */
    val transactions: List<CustomerTransactionSummary>
) : CustomerTransactions

/**
 * Summary information of a transaction performed by a customer
 */
@Serializable
data class CustomerTransactionSummary(
    override val paymentRequestId: String,
    override val merchantReferenceId: String,

    @Serializable(with = CurrencySerializer::class)
    override val grossAmount: BigDecimal,
    override val transactionId: String,
    override val clientReference: String? = null,
    override val type: TransactionSummaryType.PaymentType,

    @Serializable(with = ISODateSerializer::class)
    override val executionTime: OffsetDateTime,
    override val status: TransactionSummaryType.PaymentStatus,
    override val rollback: TransactionSummaryType.SummaryRollback? = null,
    override val subTransactions: List<JsonObject>? = null,
    override val refundReason: String? = null,
    override val instruments: List<TransactionSummaryType.UsedPaymentInstrument>,
    override val merchantId: String,
) : CustomerTransactionSummaryType

/**
 * Detailed information for a single transaction
 */
@Serializable
data class CustomerTransactionDetails(
    override val paymentRequestId: String,
    override val merchantReferenceId: String,

    @Serializable(with = CurrencySerializer::class)
    override val grossAmount: BigDecimal,
    override val transactionId: String,
    override val clientReference: String? = null,
    override val type: TransactionSummaryType.PaymentType,

    @Serializable(with = ISODateSerializer::class)
    override val executionTime: OffsetDateTime,
    override val status: TransactionSummaryType.PaymentStatus,
    override val rollback: TransactionSummaryType.SummaryRollback? = null,
    override val subTransactions: List<JsonObject>? = null,
    override val refundReason: String? = null,
    override val instruments: List<TransactionSummaryType.UsedPaymentInstrument>,
    override val merchantId: String,

    /** The [Basket] associated to the the transaction */
    val basket: Basket? = null
) : CustomerTransactionSummaryType