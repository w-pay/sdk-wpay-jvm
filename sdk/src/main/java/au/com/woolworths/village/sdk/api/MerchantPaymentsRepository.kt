package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.*

interface MerchantPaymentsRepository {
    /**
     * Retrieve a list of the payments initiated by the merchant, both pending and complete
     *
     * @param type The type of payment requests to return
     * @param page The page of results to return with 1 indicating the first page (defaults to 1).
     * @param pageSize The number of records to return for this page (current default is 25)
     */
    fun listPayments(
        type: String?,
        page: Int?,
        pageSize: Int?
    ): ApiResult<MerchantPaymentSummaries>

    /**
     * Create a new payment request for a customer
     *
     * @param paymentRequest The details of the new payment request
     */
    fun createPaymentRequest(paymentRequest: NewPaymentRequest): ApiResult<CreatePaymentRequestResult>

    /**
     * Retrieve a payment request by it's ID. The request may have been "completed" that is the customer has made a payment.
     *
     * @param paymentRequestId The ID of the payment request to return.
     */
    fun getPaymentRequestDetailsBy(paymentRequestId: String): ApiResult<MerchantPaymentDetails>

    /**
     * Delete a payment request
     *
     * @param paymentRequestId The payment request to delete
     */
    fun deletePaymentRequest(paymentRequestId: String): ApiResult<Unit>

    /**
     * Refund a transaction to a customer
     *
     * @param transactionId The transaction to refund.
     * @param refundDetails The details of the refund.
     */
    fun refundTransaction(
        transactionId: String,
        refundDetails: TransactionRefundDetails
    ): ApiResult<MerchantTransactionSummary>
}