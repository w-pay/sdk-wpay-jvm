package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON success response structure of the Openpay Voids endpoint.
 *
 * @category Model
 */
interface OpenPayVoidResponse: Serializable {
	/** Container reference in the transaction logs. This number uniquely identifies the whole/grouped transaction in the container. */
	val transactionReceipt: String

	/** List of void responses */
	val voidResponses: List<OpenPayVoidTransactionResponse>
}

interface OpenPayVoidTransactionResponse: Serializable {
	/** Container reference in the transaction logs. This number uniquely identifies the openpay transaction in the container. */
	val paymentTransactionRef: String

	/** Container reference in the transaction logs. This number uniquely identifies the void transaction in the container. */
	val voidTransactionRef: String

	/** The amount processed in the void. */
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
