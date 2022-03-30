package au.com.woolworths.village.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.model.digitalpay.DigitalPayPaymentResponse
import au.com.woolworths.village.sdk.model.walletmanagement.TransactionHistoryRequest
import au.com.woolworths.village.sdk.model.walletmanagement.TransactionHistoryResponse

class TransactionsApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Get the transaction history of a consumer.
     *
     * @param transactionHistoryRequest Detail about transactions to recieve history for.
     */
    suspend fun history(
        transactionHistoryRequest: TransactionHistoryRequest
    ): ApiResult<TransactionHistoryResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(TransactionHistoryResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/transactions"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = transactionHistoryRequest
        )))
    }
}
