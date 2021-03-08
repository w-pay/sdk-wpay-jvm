package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON response structure of the Import Payment InstrumentsApi endpoint.
 *
 * @category Model
 */
interface ImportPaymentInstrumentsResponse: Serializable {
	/* The IDM (Gigya) UID or merchant shopper id of the user. Do NOT use an email address! */
	val uid String

	/* The merchant shopper id of the user. */
	val shopperId String

	val creditCards List<CreditCardResult>?
	val payPal Paypal?
}

class PayPal{
	/* The PayPalApi customer id. */
	val customerId: String

	/* The PayPalApi email id. */
	val payPalId: String

	/* The PayPalApi payment method token.*/
	val paymentMethodToken: String

	/* The import process result for the paypal instrument. */
	val result: ResultEnum

	/* The import process error message if "result" is "ERROR". Will be null if "result" is not "ERROR". */
	val errorMessage: ErrorMessage?
}

enum class ResultEnum{
	OK,
	DUP,
	EXP,
	ERROR
}

interface ErrorMessage{
	val description String
}

interface CreditCardResult {
	/* WebPay reference in the transaction logs. This number uniquely identifies the transaction in WebPay. */
	val transactionRef String

	/* The WebPay transaction timestamp. The timestamp format is ISO8601. */
	val transactionTimestamp String

	/* The WebPay transaction type. */
	val transactionType String

	/* The WebPay transaction response code. */
	val transactionResponseCode String

	/* The WebPay transaction response text. */
	val transactionResponseText String

	/* The merchant order number used in the WebPay transaction. */
	val orderNumber String

	/* The bin (first 6 digits) of the credit card number used in the WebPay transaction. */
	val bin String

	/* The suffix (last 4 digits) of the credit card number used in the WebPay transaction. */
	val cardSuffix String

	/* The month of the expiry date of the credit card. */
	val expiryMonth String

	/* The year of the expiry date of the credit card. */
	val expiryYear String

	/* The amount of the WebPay transaction. */
	val amount BigDecimal

	/* The import process result for the credit card instrument.*/
	val result ResultEnum

	val errorMessage ErrorMessage?
}


enum class ResultEnum{
	OK, DUP, EXP, ERROR
}
