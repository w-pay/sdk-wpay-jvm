package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.wpay.sdk.*
import au.com.wpay.sdk.helpers.optionalParam
import au.com.wpay.sdk.helpers.params
import au.com.wpay.sdk.model.MerchantTransactionDetails
import au.com.wpay.sdk.model.MerchantTransactionSummaries
import org.threeten.bp.OffsetDateTime

class MerchantTransactionsApi(
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a list of previously executed transactions with the merchant.
     *
     * @param page The page of results to return with 1 indicating the first page (defaults to 1).
     * @param pageSize The number of records to return for this page (current default is 25)
     * @param endTime If present, transactions newer than this time will not be returned.
     * @param startTime If present, transactions older than this time will not be returned
     */
    suspend fun list(
        page: Int? = null,
        pageSize: Int? = null,
        endTime: OffsetDateTime? = null,
        startTime: OffsetDateTime? = null
    ): ApiResult<MerchantTransactionSummaries> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall(unitEncoder),
            unmarshall(::fromData)({ parser, el -> tryDecoding<MerchantTransactionSummaries>(parser, el) })
        )

        return apiResult(client(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/merchant/transactions"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = params(
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
    suspend fun getById(transactionId: String): ApiResult<MerchantTransactionDetails> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall(unitEncoder),
            unmarshall(::fromData)({ parser, el -> tryDecoding<MerchantTransactionDetails>(parser, el) })
        )

        return apiResult(client(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/merchant/transactions/:transactionId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "transactionId" to transactionId
            ),
            queryParams = null,
            body = null
        )))
    }
}
