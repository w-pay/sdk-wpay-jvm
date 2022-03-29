package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Refunds endpoint.
 */
@Serializable
data class DigitalPayRefundRequest(
    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String,

    /** The merchant order number of the transaction. */
    val orderNumber: String,

    /** List of refunds */
    val refunds: List<DigitalPayRefund>
) : ModelType

@Serializable
data class DigitalPayRefund(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the payment transaction in the container.
     */
    val paymentTransactionRef: String,

    /** The amount you want to refund. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal
) : ModelType

/**
 * The JSON success response structure of the Refunds endpoint.
 */
@Serializable
data class DigitalPayRefundResponse(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the whole/grouped transaction in the container.
     */
    val transactionReceipt: String,

    /** A flag to indicate if a split refund was only partially successful, ie. at least 1 of the refunds had a successful result. */
    val partialSuccess: Boolean? = null,

    /** List of refund response */
    val refundResponses: List<DigitalPayRefundTransactionResponse>
) : ModelType

@Serializable
data class DigitalPayRefundTransactionResponse(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the credit card transaction in the container.
     */
    val paymentTransactionRef: String,

    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the refund transaction in the container.
     */
    val refundTransactionRef: String,

    /** The amount processed in the refund. */
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
    val externalServiceMessage: String
) : ModelType
