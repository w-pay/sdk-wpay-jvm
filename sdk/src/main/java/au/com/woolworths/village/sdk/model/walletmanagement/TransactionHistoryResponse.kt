package au.com.woolworths.village.sdk.model.walletmanagement

import java.math.BigDecimal

/**
 * The JSON response structure of the Transaction History endpoint.
 *
 * @category Model
 */
interface TransactionHistoryResponse {
    /* The number of transactions returned in the results. */
    val returned: BigDecimal

    /* The total number of transactions available in the container. */
    val total: BigDecimal

    val transactions: List<Transaction>
}

interface Transaction {
    /* The container transaction type. */
    val transactionType: ContainerTransactionType

    /* Container reference in the transaction logs. This number uniquely identifies the transaction in the container. */
    val transactionRef: String

    /* The container transaction timestamp. The timestamp format is ISO8601. */
    val transactionTimestamp: String

    /* A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. The current implementation assigns the "clientReference" value to this property. */
    val applicationRef: String

    /* The container application name of the merchant. */
    val applicationName: String

    /* A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String

    /* The merchant order number of the transaction. */
    val orderNumber: String

    /* The bin (first 4 digits) of the card number used in the transaction. Will be null for transactions where bin is not applicable. */
    val bin: String

    /* The type of payment instrument used in the transaction. For credit card transactions this property will contain the scheme. */
    val network: String

    /* The suffix (last 4 digits) of the card number used in the transaction. Will be null for transactions where suffix is not applicable. */
    val cardSuffix: String

    /* The amount of the transaction. */
    val amount: BigDecimal

    /* The comment set in the tokenization request of Google/Apple Pay instruments. Will be null for transactions where comment is not applicable. */
    val comment: String

    /* The type of the payment instrument used in the transaction. */
    val paymentInstrumentType: String
}

enum class ContainerTransactionType {
    PREAUTH,
    PURCHASE,
    COMPLETION,
    VOID,
    REFUND
}