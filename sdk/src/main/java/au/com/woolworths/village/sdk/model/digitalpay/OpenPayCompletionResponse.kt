package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON response structure of the OpenPay Completions endpoint.
 */
@Serializable
data class OpenPayCompletionResponse(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the whole/grouped transaction in the container.
     */
    val transactionReceipt: String,

    /** List of completion responses */
    val completionResponses: List<OpenPayTransactionCompletionResponse>
) : ModelType

@Serializable
data class OpenPayTransactionCompletionResponse(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the credit card transaction in the container.
     */
    val paymentTransactionRef: String,

    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the completion transaction in the container.
     */
    val completionTransactionRef: String,

    /** The amount processed in the completion. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /**
     * The external service code (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String,

    /**
     * The external service message (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String,

    /** This array is only included in the response if it is enabled in the consumers API configuration. */
    val extendedTransactionData: List<OpenPayExtendedTransactionData>? = null
) : ModelType
