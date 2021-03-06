package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.walletmanagement.TokenizeApplePayRequest
import au.com.wpay.sdk.model.walletmanagement.TokenizeApplePayResponse

class ApplePayApi(
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Create a payment instrument id for a provided Apple Pay wallet item.
     *
     * @param tokenizeApplePayRequest Detail of the Apple Pay wallet item to be tokenized.
     */
    suspend fun tokenize(tokenizeApplePayRequest: TokenizeApplePayRequest): ApiResult<TokenizeApplePayResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: TokenizeApplePayRequest -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeApplePayResponse>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/applepay/tokenize"),
            body = tokenizeApplePayRequest
        )))
    }

    /**
     * Create a payment instrument id for a provided Apple Pay wallet item of a guest user.
     *
     * @param tokenizeApplePayRequest Detail of the Apple Pay wallet item to be tokenized.
     */
    suspend fun guestTokenize(
        tokenizeApplePayRequest: TokenizeApplePayRequest
    ): ApiResult<TokenizeApplePayResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: TokenizeApplePayRequest -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeApplePayResponse>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/guest/applepay/tokenize"),
            body = tokenizeApplePayRequest
        )))
    }

    /**
     * Update an Apple Pay payment instrument.
     *
     * @param paymentInstrumentId The id of the Apple Pay payment instrument to update.
     * @param tokenizeApplePayRequest Detail of the Apple Pay wallet item to be tokenized.
     */
    suspend fun update(
        paymentInstrumentId: String,
        tokenizeApplePayRequest: TokenizeApplePayRequest
    ): ApiResult<TokenizeApplePayResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: TokenizeApplePayRequest -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizeApplePayResponse>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/applepay/tokenize/:paymentInstrumentId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentInstrumentId" to paymentInstrumentId
            ),
            queryParams = null,
            body = tokenizeApplePayRequest
        )))
    }
}
