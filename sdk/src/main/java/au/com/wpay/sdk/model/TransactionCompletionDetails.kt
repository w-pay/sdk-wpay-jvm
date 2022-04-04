package au.com.wpay.sdk.model

import au.com.wpay.sdk.CurrencySerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * Request payload containing the completion instructions
 */
@Serializable
data class TransactionCompletionDetails(
    /** An order number to be associated with the transaction. */
    val orderNumber: String,

    /** A client reference to be associated with the transaction. */
    val clientReference: String,

    /**
     * List of completions with amounts. Can be used to execute a completion on multiple payment instruments.
     *
     * If completions is not supplied any pre-authorised sub transactions will be completed
     */
    val completions: List<CompletionItem>? = null
) : ModelType

/**
 * Payment reference of the pre-authorised transaction to be completed.
 */
@Serializable
data class CompletionItem(
    /** The payment reference for this transaction */
    val paymentTransactionRef: String,

    /** The amount of the completed transaction */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal
) : ModelType
