package au.com.woolworths.village.sdk.model.digitalpay

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON response structure of the Completions endpoint.
 *
 * @category Model
 */
interface DigitalPayCompletionResponse : Serializable {
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the whole/grouped transaction in the container.
     */
    val transactionReceipt: String

    /**
     * A flag to indicate if a split completion was only partially successful,
     *
     * ie. at least 1 of the completions had a successful result.
     */
    val partialSuccess: Boolean?

    val completionResponses: List<DigitalPayTransactionCompletionResponse>
}

interface DigitalPayTransactionCompletionResponse : Serializable {
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the credit card transaction in the container.
     */
    val paymentTransactionRef: String

    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the completion transaction in the container.
     */
    val completionTransactionRef: String

    /** The amount processed in the completion. */
    val amount: BigDecimal

    /** The error code. Only present if an error occurred during payment. */
    val errorCode: String?

    /** The error message. Only present if an error occurred during payment. */
    val errorMessage: String?

    /** The error detail. Only present if an error occurred during payment. */
    val errorDetail: String?

    /**
     * The external service code (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String?

    /**
     * The external service message (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String?
}
