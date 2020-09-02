package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface MerchantTransactions: Serializable {

}

interface MerchantTransactionSummaries: MerchantTransactions {
    val transactions: List<MerchantTransactionSummary>
}

interface MerchantTransactionSummary: TransactionSummary {
    val walletId: String
}

interface MerchantTransactionDetails: MerchantTransactionSummary {
    val basket: Basket?
    val posPayload: PosPayload?
    val merchantPayload: MerchantPayload?
}