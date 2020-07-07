package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface MerchantTransactions: Serializable {

}

interface MerchantTransactionSummaries: MerchantTransactions {
    fun transactions(): List<MerchantTransactionSummary>
}

interface MerchantTransactionSummary: TransactionSummary {
    fun walletId(): String
}

interface MerchantTransactionDetails: MerchantTransactionSummary {
    fun basket(): Basket?
    fun posPayload(): PosPayload?
    fun merchantPayload(): MerchantPayload?
}