package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.*

class CustomerPaymentAgreementsApi(
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a list of customer's [PaymentAgreement]s
     */
    suspend fun list(): ApiResult<PaymentAgreements> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall(unitEncoder),
            unmarshall(::fromData)({ parser, el -> tryDecoding<PaymentAgreements>(parser, el) })
        )

        return apiResult(client(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/payments/agreements")
        )))
    }

    /**
     * Retrieve a [PaymentAgreement] by its associated payment token
     *
     * @param paymentToken The ID.
     */
    suspend fun getById(paymentToken: String): ApiResult<PaymentAgreement> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall(unitEncoder),
            unmarshall(::fromData)({ parser, el -> tryDecoding<PaymentAgreement>(parser, el) })
        )

        return apiResult(client(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/payments/agreements/:paymentToken"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentToken" to paymentToken
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Create a [PaymentAgreement]
     *
     * @param paymentAgreement The details for the new payment agreement
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     * @param fraudPayload Used to complete the fraud check
     */
    suspend fun create(
        paymentAgreement: CreatePaymentAgreementRequest,
        challengeResponses: List<ChallengeResponse>? = null,
        fraudPayload: FraudPayload? = null
    ): ApiResult<PaymentAgreement> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: ApiRequestBody<CreatePaymentAgreementRequest, Meta> -> tryEncoding(parser, data) }),
            unmarshall(::fromData)({ parser, el -> tryDecoding<PaymentAgreement>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/customer/payments/agreements"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                paymentAgreement,
                meta = when (challengeResponses) {
                    null -> Meta(fraudPayload)
                    else -> Meta(fraudPayload, challengeResponses)
                }
            )
        )))
    }

    /**
     * Update a [PaymentAgreement]
     *
     * @param paymentToken The payment token to update
     * @param paymentAgreement The updates to apply to the payment agreement
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     * @param fraudPayload Used to complete the fraud check
     */
    suspend fun update(
        paymentToken: String,
        paymentAgreement: UpdatePaymentAgreementRequest,
        challengeResponses: List<ChallengeResponse>? = null,
        fraudPayload: FraudPayload? = null
    ): ApiResult<PaymentAgreement> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: ApiRequestBody<UpdatePaymentAgreementRequest, Meta> -> tryEncoding(parser, data) }),
            unmarshall(::fromData)({ parser, el -> tryDecoding<PaymentAgreement>(parser, el) })
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/customer/payments/agreements/:paymentToken"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentToken" to paymentToken
            ),
            queryParams = null,
            body = ApiRequestBody(
                paymentAgreement,
                meta = when (challengeResponses) {
                    null -> Meta(fraudPayload)
                    else -> Meta(fraudPayload, challengeResponses)
                }
            )
        )))
    }
}
