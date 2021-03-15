package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

interface CustomerTransactions : Serializable {
    /**
     * An instrument used for a transaction
     */
    interface UsedPaymentInstrument : Serializable {
        /** The ID of the [PaymentInstrument] */
        val paymentInstrumentId: String

        /** The amount charged against or refunded to this instrument */
        val amount: BigDecimal

        /** The reference for the payment */
        val paymentTransactionRef: String?
    }
}

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

    /** The instruments used to make the payment. For refunds and cash back amounts will be negative */
    val instruments: List<CustomerTransactions.UsedPaymentInstrument>
}

/**
 * Detailed information for a single transaction
 */
interface CustomerTransactionDetails : CustomerTransactionSummary {
    /** The [Basket] associated to the the transaction */
    val basket: Basket?
}