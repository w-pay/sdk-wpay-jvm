package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Payments endpoint
 *
 * @category Model
 */
interface DigitalPayPaymentRequest: Serializable {
	/**
	 * Transaction type containers to use for all instruments.
	 *
	 * This object is only required if the payments request contains apple pay instruments.
	 */
	val transactionType DigitalPayTransactionType

	/** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
	val clientReference String

	/** The merchant order number of the transaction. */
	val orderNumber String

	/** This object is only required if the payments request contains paypal instruments. */
	val shippingAddress DigitalPayAddress

	/** List of payments */
	val payments List<DigitalPayPayment>

	/** Extended merchant data */
	val extendedMerchantData List<ExtendedMerchantData>?

	/** Set to null to skip the cybersource fraud check. */
	val fraudPayload DigitalPayFraudPayload?

	/** Store data */
	val storeData DigitalPayStoreData?
}

interface DigitalPayPayment: Serializable {
	/**
	 * The payment instrument id from the card capture iframe response or the list payment instruments response.
	 *
	 * This property can be omitted if the payment token property is present.
	 */
	val paymentInstrumentId String

	/**
	 * The payment token from the card capture iframe response or the list payment instruments response.
	 *
	 * This property can be omitted if the payment instrument id property is present.
	 */
	val paymentToken String

	/** The amount you want to pay with the payment instrument. */
	val amount BigDecimal

	/**
	 * The step-up token is used to track additional credit card information (eg. CVV and expiry) attached to the payment instrument.
	 *
	 * It's only valid for a predefined time and if an expired step-up token is used during payment, the payment for that instrument will fail and the user will have to get a new step-up token before retrying the payment. A step-up token is returned in the response of a credit card iframe.
	 *
	 * This property is currently only required for credit card instruments and only if specific credit card information (eg. CVV and expiry) is required during payment.
	 */
	val stepUpToken String?

	/**
	 * The passcode is used to send additional information (eg. gift card PIN) for the payment instrument.
	 *
	 * This property is currently only required for gift card instruments and only if the gift card PIN is required during payment.
	 *
	 * This property should NOT be used with credit card instruments (see stepUpToken).
	 */
	val passcode String?
}

interface DigitalPayStoreData: Serializable {
	/** The in-store payment transaction store id. */
	val storeId String

	/** The in-store payment transaction store id. This is a 12 digit \"0\" [zero] padded numeric string. */
	val rrn String

	/** The in-store payment transaction timestamp. The timestamp format is milliseconds since epoch. */
	val transactionTimestamp BigDecimal
}

interface ExtendedMerchantData: Serializable {
	/** The name of the extended merchant data field. */
	val field String get() = "correlationId"

	/** The value of the extended merchant data field. */
	val value String
}
