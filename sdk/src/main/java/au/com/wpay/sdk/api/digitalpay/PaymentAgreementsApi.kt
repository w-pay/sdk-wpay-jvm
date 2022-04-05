package au.com.wpay.sdk.api.digitalpay

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.digitalpay.DigitalPayChargePaymentAgreementRequest
import au.com.wpay.sdk.model.digitalpay.DigitalPayCreatePaymentAgreementRequest
import au.com.wpay.sdk.model.digitalpay.DigitalPayPaymentAgreementResponse
import au.com.wpay.sdk.model.digitalpay.DigitalPayUpdatePaymentAgreementRequest

class PaymentAgreementApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Create a new payment agreement which will be added to the users wallet after validating the payment instrument.
     *
     * This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param paymentAgreementRequest detail of payment agreement to be created
     */
    suspend fun create(
        paymentAgreementRequest: DigitalPayCreatePaymentAgreementRequest
    ): ApiResult<DigitalPayPaymentAgreementResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(DigitalPayPaymentAgreementResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/paymentagreements"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = paymentAgreementRequest
        )))
    }

    /**
     * Update an existing payment agreement and validate the payment instrument if changed.
     *
     * This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param paymentToken The payment agreement to update
     * @param paymentAgreementRequest detail of payment agreement to be updated
     */
    suspend fun update(
        paymentToken: String,
        paymentAgreementRequest: DigitalPayUpdatePaymentAgreementRequest
    ): ApiResult<DigitalPayPaymentAgreementResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(DigitalPayPaymentAgreementResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/paymentagreements/:paymentToken"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentToken" to paymentToken
            ),
            queryParams = null,
            body = paymentAgreementRequest
        )))
    }

    /**
     * Perform charge transaction against a payment agreement.
     *
     * This service will use the provided information to perform the charge transaction.
     *
     * A charge payment is made by the merchant to charge a customer as per their payment agreement.
     *
     * This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param chargeRequest detail of payment agreement to be charged
     */
    suspend fun charge(
        chargeRequest: DigitalPayChargePaymentAgreementRequest
    ): ApiResult<DigitalPayPaymentAgreementResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(DigitalPayPaymentAgreementResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/paymentagreements/charge"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = chargeRequest
        )))
    }

    /**
     * Delete an existing payment agreement.
     *
     * This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param paymentToken The payment agreement to delete
     */
    suspend fun delete(paymentToken: String): ApiResult<Unit> {
        val unmarshaller = unmarshall(::jsonPassthrough)(Unit::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.DELETE,
            url = HttpRequestUrl.String("/paymentagreements/:paymentToken"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentToken" to paymentToken
            ),
            queryParams = null,
            body = null
        )))
    }
}
