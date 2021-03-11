package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Transaction History endpoint.
 *
 * @category Model
 */
interface TransactionHistoryRequest {
	/* The container transaction types to include in the results. */
	val transactionTypes: List<TransactionClass>

	/* The ids of the payment instruments to include in the results. */
	val paymentInstrumentIds: List<String>

	/* A merchant application specific reference number to include in the results. */
	val clientReference: String

	/* A container reference number to include in the results. */
	val transactionRef: String

	/* A merchant order number to include in the results. */
	val orderNumber: String

	/* Limit transactions included in the results FROM this timestamp. The timestamp format is ISO8601. */
	val startDate: String

	/* Limit transactions included in the results TO this timestamp . The timestamp format is ISO8601. */
	val endDate: String

	/* The max number of transactions to include in the results. */
	val maxRecords: BigDecimal
}

enum class TransactionClass{
	PREAUTH,
	PURCHASE,
	COMPLETION,
	VOID,
	REFUND
}