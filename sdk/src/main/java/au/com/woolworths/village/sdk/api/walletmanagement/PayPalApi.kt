package au.com.woolworths.village.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizePaypalRequest
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizePaypalResponse

class PayPalApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Create a paymment intrument id for a provided paypal account.
     *
     * @param tokenizePaypalRequest Detail of the paypal account to be tokenized.
     */
    suspend fun tokenize(tokenizePaypalRequest: TokenizePaypalRequest): ApiResult<TokenizePaypalResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(TokenizePaypalResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/paypal/tokenize"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = tokenizePaypalRequest
        )))
    }

    /**
     * 	Create a paymment intrument id for a provided paypal account of a guest user.
     *
     * @param tokenizePaypalRequest Detail of the paypal account to be tokenized.
     */
    suspend fun guestTokenize(tokenizePaypalRequest: TokenizePaypalRequest): ApiResult<TokenizePaypalResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(TokenizePaypalResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/guest/paypal/tokenize"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = tokenizePaypalRequest
        )))
    }
}
