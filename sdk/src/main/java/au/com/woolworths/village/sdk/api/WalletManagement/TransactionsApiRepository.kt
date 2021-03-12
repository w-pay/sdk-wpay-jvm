package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.ApiResult

interface TransactionsApiRepository {
	/**
	 * Get the transaction history of a consumer.
	 *
	 * @param transactionHistoryRequest Detail about transactions to recieve history for.
	 */
	fun history(
		transactionHistoryRequest: TransactionHistoryRequest
	): ApiResult<TransactionHistoryResponse>
}
