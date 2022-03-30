package au.com.woolworths.village.sdk.model.walletmanagement

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class TransactionHistoryRequest(
    /** The container transaction types to include in the results. */
    val transactionTypes: List<TransactionClass>,

    /** The ids of the payment instruments to include in the results. */
    val paymentInstrumentIds: List<String>,

    /** A merchant application specific reference number to include in the results. */
    val clientReference: String,

    /** A container reference number to include in the results. */
    val transactionRef: String,

    /** A merchant order number to include in the results. */
    val orderNumber: String,

    /** Limit transactions included in the results FROM this timestamp. The timestamp format is ISO8601. */
    val startDate: String,

    /** Limit transactions included in the results TO this timestamp . The timestamp format is ISO8601. */
    val endDate: String,

    /** The max number of transactions to include in the results. */
    val maxRecords: Int
) : ModelType

@Serializable
data class TransactionHistoryResponse(
    /** The number of transactions returned in the results. */
    val returned: Int,

    /** The total number of transactions available in the container. */
    val total: Int,

    val transactions: List<Transaction>
) : ModelType

@Serializable
data class Transaction(
    /** The container transaction type. */
    val transactionType: ContainerTransactionType,

    /** Container reference in the transaction logs. This number uniquely identifies the transaction in the container. */
    val transactionRef: String,

    /** The container transaction timestamp. The timestamp format is ISO8601. */
    val transactionTimestamp: String,

    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. The current implementation assigns the "clientReference" value to this property. */
    val applicationRef: String,

    /** The container application name of the merchant. */
    val applicationName: String,

    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String,

    /** The merchant order number of the transaction. */
    val orderNumber: String,

    /** The bin (first 4 digits) of the card number used in the transaction. Will be null for transactions where bin is not applicable. */
    val bin: String? = null,

    /** The type of payment instrument used in the transaction. For credit card transactions this property will contain the scheme. */
    val network: String,

    /** The suffix (last 4 digits) of the card number used in the transaction. Will be null for transactions where suffix is not applicable. */
    val cardSuffix: String? = null,

    /** The amount of the transaction. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** The comment set in the tokenization request of Google/Apple Pay instruments. Will be null for transactions where comment is not applicable. */
    val comment: String,

    /** The type of the payment instrument used in the transaction. */
    val paymentInstrumentType: String
) : ModelType

enum class ContainerTransactionType {
    PREAUTH,
    PURCHASE,
    COMPLETION,
    VOID,
    REFUND
}

enum class TransactionClass {
    PREAUTH,
    PURCHASE,
    COMPLETION,
    VOID,
    REFUND
}
