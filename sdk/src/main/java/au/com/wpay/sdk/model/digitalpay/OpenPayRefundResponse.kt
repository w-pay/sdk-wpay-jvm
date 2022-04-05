package au.com.wpay.sdk.model.digitalpay

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON success response structure of the Openpay Refunds endpoint.
 */
@Serializable
data class OpenPayRefundResponse(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the whole/grouped transaction in the container.
     */
    val transactionReceipt: String,

    /** List of refund response */
    val refundResponses: List<OpenPayRefundTransactionResponse>
) : ModelType

@Serializable
data class OpenPayRefundTransactionResponse(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the openpay transaction in the container.
     */
    val paymentTransactionRef: String,

    /** Container reference in the transaction logs. This number uniquely identifies the refund transaction in the container. */
    val refundTransactionRef: String,

    /** The amount processed in the refund. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** This array is only included in the response if it is enabled in the consumers API configuration. */
    val extendedTransactionData: List<OpenPayExtendedTransactionData>? = null,

    /**
     * The external service code (from eg. Openpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String? = null,

    /**
     * The external service message (from eg. Openpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String? = null
) : ModelType
