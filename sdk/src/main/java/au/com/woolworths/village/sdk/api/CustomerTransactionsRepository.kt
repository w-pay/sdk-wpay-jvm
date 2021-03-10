package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.CustomerTransactionDetails
import au.com.woolworths.village.sdk.model.CustomerTransactionSummaries
import org.threeten.bp.OffsetDateTime

interface CustomerTransactionsRepository {
    /**
     * Retrieve a list of previously executed transactions for the customer.
     *
     * @param paymentRequestId If present, limits the list of transactions to those that relate to the payment request.
     * @param page The page of results to return with 1 indicating the first page (defaults to 1).
     * @param pageSize The number of records to return for this page (current default is 25)
     * @param endTime If present, transactions newer than this time will not be returned.
     * @param startTime If present, transactions older than this time will not be returned
     */
    fun list(
        paymentRequestId: String?,
        page: Int?,
        pageSize: Int?,
        endTime: OffsetDateTime?,
        startTime: OffsetDateTime?
    ): ApiResult<CustomerTransactionSummaries>

    /**
     * Retrieve details about a specific transaction
     *
     * @param transactionId The transaction id
     */
    fun getById(transactionId: String): ApiResult<CustomerTransactionDetails>
}