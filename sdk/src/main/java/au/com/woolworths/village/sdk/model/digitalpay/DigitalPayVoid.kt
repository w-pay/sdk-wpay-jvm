package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable

/**
 * The JSON request structure of the Voids endpoint.
 */
@Serializable
data class DigitalPayVoidRequest(
    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String,

    /** The merchant order number of the transaction. */
    val orderNumber: String,

    /** List of voided payments */
    val voids: List<DigitalPayVoid>
) : ModelType

@Serializable
data class DigitalPayVoid(
    /** Container reference in the transaction logs. This number uniquely identifies the payment transaction in the container. */
    val paymentTransactionRef: String
) : ModelType

/**
 * The JSON success response structure of the Voids endpoint.
 */
@Serializable
data class DigitalPayVoidResponse(
    /** Container reference in the transaction logs. This number uniquely identifies the whole/grouped transaction in the container. */
    val transactionReceipt: String,

    /** A flag to indicate if a split void was only partially successful, ie. at least 1 of the voids had a successful result. */
    val partialSuccess: Boolean? = null,

    /** List of void responses */
    val voidResponses: List<DigitalPayVoidTransactionResponse>
) : ModelType

@Serializable
data class DigitalPayVoidTransactionResponse(
    /** Container reference in the transaction logs. This number uniquely identifies the credit card transaction in the container. */
    val paymentTransactionRef: String,

    /** Container reference in the transaction logs. This number uniquely identifies the void transaction in the container. */
    val voidTransactionRef: String,

    /**
     * The external service code (from eg. WebPay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String? = null,

    /**
     * The external service message (from eg. WebPay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String? = null,

    /** The error code. Only present if an error occurred during payment. */
    val errorCode: String? = null,

    /** The error message. Only present if an error occurred during payment. */
    val errorMessage: String? = null,

    /** The error detail. Only present if an error occurred during payment. */
    val errorDetail: String? = null
) : ModelType

