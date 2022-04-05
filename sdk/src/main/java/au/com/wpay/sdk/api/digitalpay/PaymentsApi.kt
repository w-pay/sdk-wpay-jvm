package au.com.wpay.sdk.api.digitalpay

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*

import au.com.wpay.sdk.model.digitalpay.*

class PaymentsApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Make payments to a merchant using payment instruments.
     *
     * @param paymentRequest detail of payment to be made
     */
    suspend fun pay(paymentRequest: DigitalPayPaymentRequest): ApiResult<DigitalPayPaymentResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<DigitalPayPaymentResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/payments"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = paymentRequest
        )))
    }

    /**
     * Make guest payments to a merchant using guest payment intruments.
     *
     * @param paymentRequest detail of payment to be made
     */
    suspend fun guestPayment(paymentRequest: DigitalPayPaymentRequest): ApiResult<DigitalPayPaymentResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<DigitalPayPaymentResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/guest/payments"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = paymentRequest
        )))
    }

    /**
     * Complete pre-authed Openpay payments. This API is IP restricted to allow unauthenticated server side calls.
     *
     *  @param completionRequest detail of payment to be completed
     */
    suspend fun complete(
        completionRequest: DigitalPayCompletionRequest
    ): ApiResult<DigitalPayCompletionResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<DigitalPayCompletionResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/completions"),
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
    suspend fun voidPayment(voidRequest: DigitalPayVoidRequest): ApiResult<DigitalPayVoidResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<DigitalPayVoidResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/voids"),
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
    suspend fun refund(refundRequest: DigitalPayRefundRequest): ApiResult<DigitalPayRefundResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<DigitalPayRefundResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/refunds"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = refundRequest
        )))
    }
}
