package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.walletmanagement.WalletDeleteRequest

class WalletApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Delete a consumers wallet. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param walletDeleteRequest Detail of the consumer who will have their the wallet deleted.
     */
    suspend fun delete(walletDeleteRequest: WalletDeleteRequest): ApiResult<Unit> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<Unit>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/wallet/delete"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = walletDeleteRequest
        )))
    }
}
