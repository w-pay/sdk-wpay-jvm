package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Completions endpoint.
 *
 * @category Model
 */
interface DigitalPayCompletionRequest: Serializable {
	/**
	 * A merchant application specific reference number.
	 *
	 * This number should uniquely identify the transaction in the merchant’s system.
	 */
	val clientReference String

	/** The merchant order number of the transaction. */
	val orderNumber String

	/** List of completions */
	val completions List<DigitalPayCompletion>
}

interface DigitalPayCompletion: Serializable {
	/**
	 * Container reference in the transaction logs.
	 *
	 * This number uniquely identifies the credit card transaction in the container.
	 */
	val paymentTransactionRef String

	/** The amount you want to process in the completion. */
	val amount BigDecimal
}
