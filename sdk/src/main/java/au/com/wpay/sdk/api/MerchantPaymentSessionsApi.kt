package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.CreatePaymentSessionRequest
import au.com.wpay.sdk.model.CreatePaymentSessionResult
import au.com.wpay.sdk.model.MerchantUpdatePaymentSessionRequest
import au.com.wpay.sdk.model.PaymentSession

class MerchantPaymentSessionsApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Create a new [PaymentSession]
     *
     * @param request The details for the new session.
     */
    suspend fun create(
        request: CreatePaymentSessionRequest
    ): ApiResult<CreatePaymentSessionResult> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::fromData)({ parser, el -> tryDecoding<CreatePaymentSessionResult>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/merchant/payment/session"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = request,
                meta = Meta()
            )
        )))
    }

    /**
     * Retrieve a [PaymentSession]
     *
     * @param paymentSessionId The ID of the payment session to retrieve.
     */
    suspend fun getById(paymentSessionId: String): ApiResult<PaymentSession> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::fromData)({ parser, el -> tryDecoding<PaymentSession>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/merchant/payment/session/:paymentSessionId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentSessionId" to paymentSessionId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Update a [PaymentSession]
     *
     * @param paymentSessionId The payment session to update
     * @param session The details to update the session with
     */
    suspend fun update(
        paymentSessionId: String,
        session: MerchantUpdatePaymentSessionRequest
    ): ApiResult<Unit> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<Unit>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/merchant/payment/session/:paymentSessionId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentSessionId" to paymentSessionId
            ),
            queryParams = null,
            body = ApiRequestBody(
                data = session,
                meta = Meta()
            )
        )))
    }

    /**
     * Delete a [PaymentSession]
     *
     * @param paymentSessionId The payment session to delete.
     */
    suspend fun delete(paymentSessionId: String): ApiResult<Unit> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<Unit>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.DELETE,
            url = HttpRequestUrl.String("/instore/merchant/payment/session/:paymentSessionId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentSessionId" to paymentSessionId
            ),
            queryParams = null,
            body = null
        )))
    }
}
