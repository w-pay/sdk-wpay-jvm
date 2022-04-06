package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.walletmanagement.WalletDeleteRequest

class WalletApi(
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Delete a consumers wallet. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param walletDeleteRequest Detail of the consumer who will have their the wallet deleted.
     */
    suspend fun delete(walletDeleteRequest: WalletDeleteRequest): ApiResult<Unit> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: WalletDeleteRequest -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)(unitDecoder)
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/wallet/delete"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = walletDeleteRequest
        )))
    }
}
