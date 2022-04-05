package au.com.wpay.sdk.api.digitalpay

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.digitalpay.*

class OpenPayApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Make payments to a merchant using Openpay payment tokens.
     *
     * @param paymentRequest detail of payment to be made
     */
    suspend fun pay(paymentRequest: OpenPayPaymentRequest): ApiResult<OpenPayPaymentResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<OpenPayPaymentResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/openpay/payments"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = paymentRequest
        )))
    }

    /**
     * Complete pre-authed Openpay payments. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param completionRequest detail of payment to be completed
     */
    suspend fun complete(completionRequest: OpenPayCompletionRequest): ApiResult<OpenPayCompletionResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<OpenPayCompletionResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/openpay/completions"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = completionRequest
        )))
    }

    /**
     * Void (cancel) pre-authed Openpay payments. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param voidRequest detail of payment to be voided
     */
    suspend fun voidPayment(voidRequest: OpenPayVoidRequest): ApiResult<OpenPayVoidResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<OpenPayVoidResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/openpay/voids"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = voidRequest
        )))
    }

    /**
     * Make payments to a merchant using Openpay payment tokens.
     *
     * @param refundRequest detail of payment to be refunded
     */
    suspend fun refund(refundRequest: OpenPayRefundRequest): ApiResult<OpenPayRefundResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<OpenPayRefundResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/openpay/refunds"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = refundRequest
        )))
    }
}
