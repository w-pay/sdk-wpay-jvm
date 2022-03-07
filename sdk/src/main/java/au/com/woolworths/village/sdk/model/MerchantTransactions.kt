package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.ISODateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

interface MerchantTransactions : ModelType

interface MerchantTransactionSummaryType : TransactionSummaryType {
    /** The ID of the wallet associated with this transaction */
    val walletId: String
}

/**
 * List of merchant transactions.
 */
@Serializable
data class MerchantTransactionSummaries(
    /** The resulting list of transactions. */
    val transactions: List<MerchantTransactionSummary>
) : MerchantTransactions

/**
 * Summary information of the resulting transaction
 */
@Serializable
data class MerchantTransactionSummary(
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
    override val walletId: String,
) : MerchantTransactionSummaryType

/**
 * Detailed information for a single transaction
 */
@Serializable
data class MerchantTransactionDetails(
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
    override val walletId: String,

    /** The [Basket] associated to the transaction. */
    val basket: Basket? = null,

    /** Optional extra details from the POS. */
    val posPayload: PosPayload? = null,

    /** Optional extra details from the merchant. */
    val merchantPayload: MerchantPayload? = null,
) : MerchantTransactionSummaryType