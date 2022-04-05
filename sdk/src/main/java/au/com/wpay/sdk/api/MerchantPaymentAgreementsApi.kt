package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.ChargePaymentAgreementRequest
import au.com.wpay.sdk.model.FraudPayload
import au.com.wpay.sdk.model.digitalpay.DigitalPayPaymentAgreementResponse

/**
 * @category API
 */
class MerchantPaymentAgreementsApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Charge a [PaymentAgreement]s
     *
     * @param paymentToken The ID.
     * @param chargePaymentAgreementRequest details of charge to make against the payment agreement
     * @param fraudPayload Used to complete the fraud check
     */
    suspend fun charge(
        paymentToken: String,
        chargePaymentAgreementRequest: ChargePaymentAgreementRequest,
        fraudPayload: FraudPayload? = null
    ): ApiResult<DigitalPayPaymentAgreementResponse> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::fromData)({ parser, el -> tryDecoding<DigitalPayPaymentAgreementResponse>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.PUT,
            url = HttpRequestUrl.String("/instore/merchant/payments/agreements/:paymentToken"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentToken" to paymentToken
            ),
            queryParams = null,
            body = ApiRequestBody(
                data = chargePaymentAgreementRequest,
                meta = Meta(fraudPayload)
            )
        )))
    }

    /**
     * Delete a [PaymentAgreement] by its associated payment token
     *
     * @param paymentToken The ID.
     */
    suspend fun delete(paymentToken: String): ApiResult<Unit> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<Unit>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.DELETE,
            url = HttpRequestUrl.String("/instore/merchant/payments/agreements/:paymentToken"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentToken" to paymentToken
            ),
            queryParams = null,
            body = null
        )))
    }
}
