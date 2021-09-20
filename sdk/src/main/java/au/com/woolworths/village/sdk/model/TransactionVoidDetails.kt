package au.com.woolworths.village.sdk.model

/**
 * Request payload containing the void instructions
 */
interface TransactionVoidDetails {
    /** An order number to be associated with the transaction. */
    val orderNumber: String

    /** A client reference to be associated with the transaction. */
    val clientReference: String

    /**
     * List of voids. Can be used to execute a void on multiple payment instruments.
     *
     * If voids is not supplied any pre-authorised sub transactions will be voided
     */
    val voids: List<VoidItem>?
}

/**
 * Payment reference of the pre-authorised transaction to be voided.
 */
interface VoidItem {
    /** The payment reference for this transaction */
    val paymentTransactionRef: String
}
