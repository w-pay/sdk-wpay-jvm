package au.com.woolworths.village.sdk.model

import java.math.BigDecimal

/**
 * Request payload containing the completion instructions
 */
interface TransactionCompletionDetails {
    /** An order number to be associated with the transaction. */
    val orderNumber: String

    /** A client reference to be associated with the transaction. */
    val clientReference: String

    /**
     * List of completions with amounts. Can be used to execute a completion on multiple payment instruments.
     *
     * If completions is not supplied any pre-authorised sub transactions will be completed
     */
    val completions: List<CompletionItem>?
}

/**
 * Payment reference of the pre-authorised transaction to be completed.
 */
interface CompletionItem {
    /** The payment reference for this transaction */
    val paymentTransactionRef: String

    /** The amount of the completed transaction */
    val amount: BigDecimal
}