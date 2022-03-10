package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.helpers.optionalParam
import au.com.woolworths.village.sdk.helpers.params
import au.com.woolworths.village.sdk.model.*

class MerchantPaymentsApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a list of the payments initiated by the merchant, both pending and complete
     *
     * @param type The type of payment requests to return
     * @param page The page of results to return with 1 indicating the first page (defaults to 1).
     * @param pageSize The number of records to return for this page (current default is 25)
     */
    suspend fun listPayments(
        type: String? = null,
        page: Int? = null,
        pageSize: Int? = null
    ): ApiResult<MerchantPaymentSummaries> {
        val unmarshaller = unmarshall(::fromData)(MerchantPaymentSummaries::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/merchant/payments"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = params(
                optionalParam("type", type),
                optionalParam("page", page),
                optionalParam("pageSize", pageSize)
            ),
            body = null
        )))
    }

    /**
     * Create a new payment request for a customer
     *
     * @param paymentRequest The details of the new payment request
     */
    suspend fun createPaymentRequest(
        paymentRequest: NewPaymentRequest
    ): ApiResult<CreatePaymentRequestResult> {
        val unmarshaller = unmarshall(::fromData)(CreatePaymentRequestResult::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/merchant/payments"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = paymentRequest,
                meta = Meta()
            )
        )))
    }

    /**
     * Retrieve a payment request by it's ID. The request may have been "completed" that is the customer has made a payment.
     *
     * @param paymentRequestId The ID of the payment request to return.
     */
    suspend fun getPaymentRequestDetailsBy(
        paymentRequestId: String
    ): ApiResult<MerchantPaymentDetails> {
        val unmarshaller = unmarshall(::fromData)(MerchantPaymentDetails::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/merchant/payments/:paymentRequestId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentRequestId" to paymentRequestId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Delete a payment request
     *
     * @param paymentRequestId The payment request to delete
     */
    suspend fun deletePaymentRequest(paymentRequestId: String): ApiResult<Unit> {
        val unmarshaller = unmarshall(::jsonPassthrough)(Unit::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.DELETE,
            url = HttpRequestUrl.String("/instore/merchant/payments/:paymentRequestId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentRequestId" to paymentRequestId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Refund a transaction to a customer
     *
     * @param transactionId The transaction to be refunded.
     * @param refundDetails The details of the refund.
     */
    suspend fun refundTransaction(
        transactionId: String,
        refundDetails: TransactionRefundDetails
    ): ApiResult<MerchantTransactionSummary> {
        val unmarshaller = unmarshall(::fromData)(MerchantTransactionSummary::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/merchant/transactions/:transactionId/refund"),
            headers = emptyMap(),
            pathParams = mapOf(
                "transactionId" to transactionId
            ),
            queryParams = null,
            body = ApiRequestBody(
                data = refundDetails,
                meta = Meta()
            )
        )))
    }

    /**
     * Complete a pre-authorised transaction
     *
     * @param transactionId The transaction to be completed.
     * @param completionDetails The details of the completions.
     */
    suspend fun completeTransaction(
        transactionId: String,
        completionDetails: TransactionCompletionDetails
    ): ApiResult<MerchantTransactionSummary> {
        val unmarshaller = unmarshall(::fromData)(MerchantTransactionSummary::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/merchant/transactions/:transactionId/completion"),
            headers = emptyMap(),
            pathParams = mapOf(
                "transactionId" to transactionId
            ),
            queryParams = null,
            body = ApiRequestBody(
                data = completionDetails,
                meta = Meta()
            )
        )))
    }

    /**
     * Void a pre-authorised transaction
     *
     * @param transactionId The transaction to be voided.
     * @param voidDetails The details of the voids.
     */
    suspend fun voidTransaction(
        transactionId: String,
        voidDetails: TransactionVoidDetails
    ): ApiResult<MerchantTransactionSummary> {
        val unmarshaller = unmarshall(::fromData)(MerchantTransactionSummary::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/merchant/transactions/:transactionId/void"),
            headers = emptyMap(),
            pathParams = mapOf(
                "transactionId" to transactionId
            ),
            queryParams = null,
            body = ApiRequestBody(
                data = voidDetails,
                meta = Meta()
            )
        )))
    }
}