package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.helpers.optionalParam
import au.com.woolworths.village.sdk.helpers.params
import au.com.woolworths.village.sdk.model.CustomerTransactionDetails
import au.com.woolworths.village.sdk.model.CustomerTransactionSummaries
import au.com.woolworths.village.sdk.model.TermsAndConditionsAcceptances
import org.threeten.bp.OffsetDateTime

class CustomerTransactionsApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a list of previously executed transactions for the customer.
     *
     * @param paymentRequestId If present, limits the list of transactions to those that relate to the payment request.
     * @param page The page of results to return with 1 indicating the first page (defaults to 1).
     * @param pageSize The number of records to return for this page (current default is 25)
     * @param endTime If present, transactions newer than this time will not be returned.
     * @param startTime If present, transactions older than this time will not be returned
     */
    suspend fun list(
        paymentRequestId: String? = null,
        page: Int? = null,
        pageSize: Int? = null,
        endTime: OffsetDateTime? = null,
        startTime: OffsetDateTime? = null
    ): ApiResult<CustomerTransactionSummaries> {
        val unmarshaller = unmarshall(::fromData)(CustomerTransactionSummaries::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/transactions"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = params(
                optionalParam("paymentRequestId", paymentRequestId),
                optionalParam("page", page),
                optionalParam("pageSize", pageSize),
                optionalParam("endTime", endTime?.toIsoDateTime()),
                optionalParam("startTime", startTime?.toIsoDateTime())
            ),
            body = null
        )))
    }

    /**
     * Retrieve details about a specific transaction
     *
     * @param transactionId The transaction id
     */
    suspend fun getById(transactionId: String): ApiResult<CustomerTransactionDetails> {
        val unmarshaller = unmarshall(::fromData)(CustomerTransactionDetails::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/transactions/:transactionId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "transactionId" to transactionId
            ),
            queryParams = null,
            body = null
        )))
    }
}