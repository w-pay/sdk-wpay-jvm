package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

interface CustomerTransactions: Serializable {
    interface UsedPaymentInstrument: Serializable {
        val paymentInstrumentId: String

        val amount: BigDecimal

        val paymentTransactionRef: String?
    }
}

interface CustomerTransactionSummaries: CustomerTransactions {
    val transactions: List<CustomerTransactionSummary>
}

interface CustomerTransactionSummary: TransactionSummary {
    val merchantId: String

    val instruments: List<CustomerTransactions.UsedPaymentInstrument>
}

interface CustomerTransactionDetails: CustomerTransactionSummary {
    val basket: Basket?
}