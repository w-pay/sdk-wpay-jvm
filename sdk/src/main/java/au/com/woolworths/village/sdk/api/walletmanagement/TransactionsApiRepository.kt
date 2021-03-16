package au.com.woolworths.village.sdk.api.walletmanagement

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.walletmanagement.TransactionHistoryRequest
import au.com.woolworths.village.sdk.model.walletmanagement.TransactionHistoryResponse

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
