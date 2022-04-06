package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.walletmanagement.TokenizePaypalRequest
import au.com.wpay.sdk.model.walletmanagement.TokenizePaypalResponse

class PayPalApi(
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Create a payment instrument id for a provided paypal account.
     *
     * @param tokenizePaypalRequest Detail of the paypal account to be tokenized.
     */
    suspend fun tokenize(tokenizePaypalRequest: TokenizePaypalRequest): ApiResult<TokenizePaypalResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: TokenizePaypalRequest -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizePaypalResponse>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/paypal/tokenize"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = tokenizePaypalRequest
        )))
    }

    /**
     * 	Create a payment instrument id for a provided paypal account of a guest user.
     *
     * @param tokenizePaypalRequest Detail of the paypal account to be tokenized.
     */
    suspend fun guestTokenize(tokenizePaypalRequest: TokenizePaypalRequest): ApiResult<TokenizePaypalResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: TokenizePaypalRequest -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<TokenizePaypalResponse>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/guest/paypal/tokenize"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = tokenizePaypalRequest
        )))
    }
}
