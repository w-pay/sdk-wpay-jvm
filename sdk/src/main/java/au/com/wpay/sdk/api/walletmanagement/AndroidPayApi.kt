package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.walletmanagement.TokenizeAndroidPayRequest
import au.com.wpay.sdk.model.walletmanagement.TokenizeAndroidPayResponse

class AndroidPayApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Create a payment instrument id for a provided Android Pay wallet item.
     *
     * @param tokenizeAndroidPayRequest Detail of the Android Pay wallet item to be tokenized.
     */
    suspend fun tokenize(
        tokenizeAndroidPayRequest: TokenizeAndroidPayRequest
    ): ApiResult<TokenizeAndroidPayResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeAndroidPayResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/androidpay/tokenize"),
            body = tokenizeAndroidPayRequest
        )))
    }

    /**
     * Update an Android Pay payment instrument.
     *
     * @param paymentInstrumentId The id of the Android Pay payment instrument to update.
     * @param tokenizeAndroidPayRequest Detail of the Android Pay wallet item to be tokenized.
     */
    suspend fun update(
        paymentInstrumentId: String,
        tokenizeAndroidPayRequest: TokenizeAndroidPayRequest
    ): ApiResult<TokenizeAndroidPayResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeAndroidPayResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/androidpay/tokenize/:paymentInstrumentId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentInstrumentId" to paymentInstrumentId
            ),
            queryParams = null,
            body = tokenizeAndroidPayRequest
        )))
    }
}
