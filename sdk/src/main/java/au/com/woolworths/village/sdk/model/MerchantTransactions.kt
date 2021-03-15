package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface MerchantTransactions : Serializable {

}

/**
 * List of merchant transactions.
 */
interface MerchantTransactionSummaries : MerchantTransactions {
    /** The resulting list of transactions. */
    val transactions: List<MerchantTransactionSummary>
}

/**
 * Summary information of the resulting transaction
 */
interface MerchantTransactionSummary : TransactionSummary {
    /** The ID of the wallet associated with this transaction */
    val walletId: String
}

/**
 * Detailed information for a single transaction
 */
interface MerchantTransactionDetails : MerchantTransactionSummary {
    /** The [Basket] associated to the transaction. */
    val basket: Basket?

    /** Optional extra details from the POS. */
    val posPayload: PosPayload?

    /** Optional extra details from the merchant. */
    val merchantPayload: MerchantPayload?
}