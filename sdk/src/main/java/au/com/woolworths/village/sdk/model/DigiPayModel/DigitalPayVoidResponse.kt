package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The JSON success response structure of the Voids endpoint.
 *
 * @category Model
 */
interface DigitalPayVoidResponse: Serializable {
	/** Container reference in the transaction logs. This number uniquely identifies the whole/grouped transaction in the container. */
	val transactionReceipt String

	/** A flag to indicate if a split void was only partially successful, ie. at least 1 of the voids had a successful result. */
	val partialSuccess Boolean?

	/** List of void responses */
	val voidResponses List<DigitalPayVoidTransactionResponse>
}

interface DigitalPayVoidTransactionResponse: Serializable {
	/** Container reference in the transaction logs. This number uniquely identifies the credit card transaction in the container. */
	val paymentTransactionRef String

	/** Container reference in the transaction logs. This number uniquely identifies the void transaction in the container. */
	val voidTransactionRef String

	/**
	 * The external service code (from eg. WebPay).
	 *
	 * This property is only included in the response if it is enabled in the consumers API configuration.
	 */
	val externalServiceCode String?

	/**
	 * The external service message (from eg. WebPay).
	 *
	 * This property is only included in the response if it is enabled in the consumers API configuration.
	 */
	val externalServiceMessage String?

	/** The error code. Only present if an error occurred during payment. */
	val errorCode String?

	/** The error message. Only present if an error occurred during payment. */
	val errorMessage String?

	/** The error detail. Only present if an error occurred during payment. */
	val errorDetail String?
}
