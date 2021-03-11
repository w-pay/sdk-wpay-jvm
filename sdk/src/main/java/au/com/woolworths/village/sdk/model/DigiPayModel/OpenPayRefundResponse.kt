package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON success response structure of the Openpay Refunds endpoint.
 *
 * @category Model
 */
interface OpenPayRefundResponse: Serializable {
	/**
	 * Container reference in the transaction logs.
	 *
	 * This number uniquely identifies the whole/grouped transaction in the container.
	 */
	val transactionReceipt: String

	/** List of refund response */
	val refundResponses: List<OpenPayRefundTransactionResponse>
}

interface OpenPayRefundTransactionResponse: Serializable {
	/**
	 * Container reference in the transaction logs.
	 *
	 * This number uniquely identifies the openpay transaction in the container.
	 */
	val paymentTransactionRef: String

	/** Container reference in the transaction logs. This number uniquely identifies the refund transaction in the container. */
	val refundTransactionRef: String

	/** The amount processed in the refund. */
	val amount: BigDecimal

	/** This array is only included in the response if it is enabled in the consumers API configuration. */
	val extendedTransactionData: OpenPayExtendedTransactionData?

	/**
	 * The external service code (from eg. Openpay).
	 *
	 * This property is only included in the response if it is enabled in the consumers API configuration.
	 */
	val externalServiceCode: String?

	/**
	 * The external service message (from eg. Openpay).
	 *
	 * This property is only included in the response if it is enabled in the consumers API configuration.
	 */
	val externalServiceMessage: String?
}
