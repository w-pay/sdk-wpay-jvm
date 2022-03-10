package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.helpers.optionalParam
import au.com.woolworths.village.sdk.helpers.params
import au.com.woolworths.village.sdk.model.ChargePaymentAgreementRequest
import au.com.woolworths.village.sdk.model.CustomerTransactionSummaries
import au.com.woolworths.village.sdk.model.FraudPayload
import au.com.woolworths.village.sdk.model.digitalpay.DigitalPayPaymentAgreementResponse

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
        val unmarshaller = unmarshall(::fromData)(DigitalPayPaymentAgreementResponse::class)
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
        val unmarshaller = unmarshall(::jsonPassthrough)(Unit::class)
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
