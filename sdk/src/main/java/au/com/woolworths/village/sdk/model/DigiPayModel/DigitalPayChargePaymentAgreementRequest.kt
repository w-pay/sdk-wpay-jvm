package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Charge Payment Agreement endpoint.
 *
 * @category Model
 */
interface DigitalPayChargePaymentAgreementRequest: Serializable {
	/** Transaction type containers to use for all instruments. */
	val transactionType DigitalPayTransactionType

	/**
	 * A merchant application specific reference number.
	 *
	 * This number should uniquely identify the transaction in the merchant’s system.
	 */
	val clientReference String

	/**
	 * A merchant application specific reference number.
	 *
	 * This number should uniquely identify the customer in the merchant’s system.
	 */
	val customerRef String?

	/** The merchant order number of the transaction. */
	val orderNumber String

	/**
	 * The payment token of the payment agreement.
	 *
	 * The payment token is a unique identifier for the payment agreement.
	 */
	val paymentToken String

	/** The amount that will be charged against the payment instrument linked to the payment agreement. */
	val amount BigDecimal

	/** Digital Pay fraud payload */
	val fraudPayload DigitalPayFraudPayload?
}
