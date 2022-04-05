package au.com.wpay.sdk.model.digitalpay

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Completions endpoint.
 */
@Serializable
data class DigitalPayCompletionRequest(
    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String,

    /** The merchant order number of the transaction. */
    val orderNumber: String,

    /** List of completions */
    val completions: List<DigitalPayCompletion>
) : ModelType

@Serializable
data class DigitalPayCompletion(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the credit card transaction in the container.
     */
    val paymentTransactionRef: String,

    /** The amount you want to process in the completion. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal
) : ModelType

/**
 * The JSON response structure of the Completions endpoint.
 */
@Serializable
data class DigitalPayCompletionResponse(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the whole/grouped transaction in the container.
     */
    val transactionReceipt: String,

    /**
     * A flag to indicate if a split completion was only partially successful,
     *
     * ie. at least 1 of the completions had a successful result.
     */
    val partialSuccess: Boolean? = null,

    val completionResponses: List<DigitalPayTransactionCompletionResponse>
) : ModelType

@Serializable
data class DigitalPayTransactionCompletionResponse(
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

    /** The error code. Only present if an error occurred during payment. */
    val errorCode: String? = null,

    /** The error message. Only present if an error occurred during payment. */
    val errorMessage: String? = null,

    /** The error detail. Only present if an error occurred during payment. */
    val errorDetail: String? = null,

    /**
     * The external service code (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String? = null,

    /**
     * The external service message (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String? = null
) : ModelType
