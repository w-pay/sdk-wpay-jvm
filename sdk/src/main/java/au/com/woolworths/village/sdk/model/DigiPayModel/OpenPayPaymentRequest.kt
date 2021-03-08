package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

interface TransactionType{
	openPay: OpenPay
}

/**
 * The JSON request structure of the Openpay Payments endpoint
 *
 * @category Model
 */
interface OpenPayPaymentRequest: Serializable {
	/** The container transaction type to use for openpay instruments */
	val transactionType: TransactionType

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

	/** The channel from which this charge is originating, eg. Online, In-Store. */
	val channel String

	/** The unique identifier for the merchants trading account. */
	val tradingAccountId String?

	/** The merchants transaction date and time. The timestamp format is ISO8601. */
	val merchantTransactedAt String?

	/** List of payments */
	val payments List<OpenPayPayments>

	/** OpenPay store data */
	val storeData OpenPayStoreData
}

enum OpenPay{
	PREAUTH,
	PURCHASE
}

interface OpenPayStoreData: Serializable {
	/** The payment transaction store id. */
	val storeId String

	/** A pin for the payment method id. */
	val pin String
}

interface OpenPayPayments: Serializable {
	/** The payment token. */
	val paymentToken String

	/** The amount you want to pay with the payment instrument. */
	val amount BigDecimal

	/** The GST amount of the full amount you want to pay with the payment instrument. */
	val gstAmount BigDecimal?
}
