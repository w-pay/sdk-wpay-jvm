package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON success response structure of the Refunds endpoint.
 *
 * @category Model
 */
interface DigitalPayRefundResponse: Serializable {
	/**
	 * Container reference in the transaction logs.
	 *
	 * This number uniquely identifies the whole/grouped transaction in the container.
	 */
	val transactionReceipt String

	/** A flag to indicate if a split refund was only partially successful, ie. at least 1 of the refunds had a successful result. */
	val partialSuccess Boolean?

	/** List of refund response */
	val refundResponses DigitalPayRefundTransactionResponse
}

interface DigitalPayRefundTransactionResponse: Serializable {
	/**
	 * Container reference in the transaction logs.
	 *
	 * This number uniquely identifies the credit card transaction in the container.
	 */
	val paymentTransactionRef String

	/**
	 * Container reference in the transaction logs.
	 *
	 * This number uniquely identifies the refund transaction in the container.
	 */
	val refundTransactionRef String

	/** The amount processed in the refund. */
	val amount BigDecimal

	/** The error code. Only present if an error occurred during payment. */
	val errorCode String?

	/** The error message. Only present if an error occurred during payment. */
	val errorMessage String?

	/** The error detail. Only present if an error occurred during payment. */
	val errorDetail String?

	/**
	 * The external service code (from eg. Webpay).
	 *
	 * This property is only included in the response if it is enabled in the consumers API configuration.
	 */
	val externalServiceCode String?

	/**
	 * The external service message (from eg. Webpay).
	 *
	 * This property is only included in the response if it is enabled in the consumers API configuration.
	 */
	val externalServiceMessage String
}
