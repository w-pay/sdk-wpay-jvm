package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.walletmanagement.TokenizeGooglePayRequest
import au.com.wpay.sdk.model.walletmanagement.TokenizeGooglePayResponse

data class GooglePayApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Create a payment token for a provided Google Pay wallet item.
     *
     * @param tokenizeGooglePayRequest Detail of the Google Pay wallet item to be tokenized.
     */
    suspend fun tokenize(
        tokenizeGooglePayRequest: TokenizeGooglePayRequest
    ): ApiResult<TokenizeGooglePayResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeGooglePayResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/googlepay/tokenize"),
            body = tokenizeGooglePayRequest
        )))
    }

    /**
     * Create a payment token for a provided Google Pay wallet item of a guest user.
     *
     * @param tokenizeGooglePayRequest Detail of the Google Pay wallet item to be tokenized.
     */
    suspend fun guestTokenize(
        tokenizeGooglePayRequest: TokenizeGooglePayRequest
    ): ApiResult<TokenizeGooglePayResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeGooglePayResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/guest/googlepay/tokenize"),
            body = tokenizeGooglePayRequest
        )))
    }

    /**
     * Update a Google Pay payment instrument.
     *
     * @param paymentToken The payment token of the google pay payment instrument to update.
     * @param tokenizeGooglePayRequest Detail of the Google Pay wallet item to be tokenized.
     */
    suspend fun update(
        paymentToken: String,
        tokenizeGooglePayRequest: TokenizeGooglePayRequest
    ): ApiResult<TokenizeGooglePayResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeGooglePayResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/googlepay/tokenize/:paymentToken"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentToken" to paymentToken
            ),
            queryParams = null,
            body = tokenizeGooglePayRequest
        )))
    }
}
