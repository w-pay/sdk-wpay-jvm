package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

interface CustomerTransactions: Serializable {
    interface UsedPaymentInstrument: Serializable {
        fun paymentInstrumentId(): String

        fun amount(): BigDecimal

        fun paymentTransactionRef(): String?
    }
}

interface CustomerTransactionSummaries: CustomerTransactions {
    fun transactions(): List<CustomerTransactionSummary>
}

interface CustomerTransactionSummary: TransactionSummary {
    fun merchantId(): String

    fun instruments(): List<CustomerTransactions.UsedPaymentInstrument>
}

interface CustomerTransactionDetails: CustomerTransactionSummary {
    fun basket(): Basket?
}