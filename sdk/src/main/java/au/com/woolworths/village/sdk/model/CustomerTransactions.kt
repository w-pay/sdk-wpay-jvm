package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

interface CustomerTransactions : Serializable

/**
 * List of customer transactions
 */
interface CustomerTransactionSummaries : CustomerTransactions {
    /** The resulting list of transactions. */
    val transactions: List<CustomerTransactionSummary>
}

/**
 * Summary information of a transaction performed by a customer
 */
interface CustomerTransactionSummary : TransactionSummary {
    /** The ID of the merchant associated with this transaction */
    val merchantId: String
}

/**
 * Detailed information for a single transaction
 */
interface CustomerTransactionDetails : CustomerTransactionSummary {
    /** The [Basket] associated to the the transaction */
    val basket: Basket?
}