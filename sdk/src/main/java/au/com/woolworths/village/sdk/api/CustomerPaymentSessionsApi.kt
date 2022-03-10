package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.model.*

class CustomerPaymentSessionsApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a [PaymentSession] by it's ID
     *
     * @param paymentSessionId The payment session ID.
     */
    suspend fun getById(paymentSessionId: String): ApiResult<PaymentSession> {
        val unmarshaller = unmarshall(::fromData)(PaymentSession::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/payment/session/:paymentSessionId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentSessionId" to paymentSessionId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Retrieve a [PaymentSession] by a QR code ID associated to the session.
     *
     * @param qrCodeId The QR code ID.
     */
    suspend fun getByQRCodeId(qrCodeId: String): ApiResult<PaymentSession> {
        val unmarshaller = unmarshall(::fromData)(PaymentSession::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/payment/session/qr/:qrId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "qrId" to qrCodeId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Update a [PaymentSession]
     *
     * @param paymentSessionId The payment session to update
     * @param session The updates to apply to the session
     */
    suspend fun update(
        paymentSessionId: String,
        session: CustomerUpdatePaymentSessionRequest
    ): ApiResult<Unit> {
        val unmarshaller = unmarshall(::jsonPassthrough)(Unit::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/customer/payment/session/:paymentSessionId"),
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
     * @param paymentSessionId The payment session to delete
     */
    suspend fun delete(paymentSessionId: String): ApiResult<Unit> {
        val unmarshaller = unmarshall(::jsonPassthrough)(Unit::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.DELETE,
            url = HttpRequestUrl.String("/instore/customer/payment/session/:paymentSessionId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentSessionId" to paymentSessionId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Pre-approve payment for a [PaymentSession]
     *
     * @param paymentSessionId The [PaymentSession] to pre-approve payment for.
     * @param primaryInstrument The primary (or only) instrument to use to make the payment. If not present then the primary instrument from the customer preferences will be used.
     * @param secondaryInstruments Other payment instruments to use to split payment.
     * @param clientReference An optional client reference to be associated with the transaction.
     * @param preferences Optional payment preferences.
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     */
    suspend fun preApprove(
        paymentSessionId: String,
        primaryInstrument: String? = null,
        secondaryInstruments: List<SecondaryPaymentInstrument>? = null,
        clientReference: String? = null,
        preferences: PaymentPreferences? = null,
        challengeResponses: List<ChallengeResponse>? = null
    ): ApiResult<Unit> {
        val unmarshaller = unmarshall(::jsonPassthrough)(Unit::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.PUT,
            url = HttpRequestUrl.String("/instore/customer/payment/session/:paymentSessionId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentSessionId" to paymentSessionId
            ),
            queryParams = null,
            body = ApiRequestBody(
                data = PaymentDetailsDTO(
                    primaryInstrumentId = primaryInstrument,
                    secondaryInstruments = secondaryInstruments,
                    skipRollback = null,
                    allowPartialSuccess = null,
                    clientReference = clientReference,
                    preferences = preferences,
                    transactionType = null
                ),
                meta = when (challengeResponses) {
                    null -> Meta(null)
                    else -> Meta(null, challengeResponses)
                }
            )
        )))
    }
}