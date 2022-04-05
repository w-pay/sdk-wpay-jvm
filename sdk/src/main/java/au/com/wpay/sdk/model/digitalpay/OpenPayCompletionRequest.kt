package au.com.wpay.sdk.model.digitalpay

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the OpenPay Completions endpoint.
 */
@Serializable
data class OpenPayCompletionRequest(
    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String,

    /** The merchant order number of the transaction. */
    val orderNumber: String,

    /** The merchants transaction date and time. The timestamp format is ISO8601. */
    val merchantTransactedAt: String,

    /** List of completions */
    val completions: List<OpenPayCompletion>
) : ModelType

@Serializable
data class OpenPayCompletion(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the credit card transaction in the container.
     */
    val paymentTransactionRef: String,

    /** The amount you want to process in the completion. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** The GST amount of the amount you want to process in the completion. */
    @Serializable(with = CurrencySerializer::class)
    val gstAmount: BigDecimal? = null
) : ModelType
