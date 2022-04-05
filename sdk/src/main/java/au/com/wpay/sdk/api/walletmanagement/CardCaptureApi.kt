package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.walletmanagement.InitiateCardCaptureRequest
import au.com.wpay.sdk.model.walletmanagement.InitiateCardCaptureResponse

class CardCaptureApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Get a credit card detials input iframe (URL) for the consumer. This API is rate limited to 10 requests per minute per shopper id.
     *
     * @param initiateCardCaptureRequest Detail of the card capture to recieve the iframe (URL) for.
     */
    suspend fun initCapture(
        initiateCardCaptureRequest: InitiateCardCaptureRequest
    ): ApiResult<InitiateCardCaptureResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<InitiateCardCaptureResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/cards/initcapture"),
            body = initiateCardCaptureRequest
        )))
    }

    /**
     * Get a credit card detials input iframe (URL) for a guest user. This API is rate limited to 10 requests per minute per guest shopper id.
     *
     * @param initiateCardCaptureRequest Detail of the card capture to recieve the iframe (URL) for.
     */
    suspend fun guestInitCapture(
        initiateCardCaptureRequest: InitiateCardCaptureRequest
    ): ApiResult<InitiateCardCaptureResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<InitiateCardCaptureResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/guest/cards/initcapture"),
            body = initiateCardCaptureRequest
        )))
    }
}
