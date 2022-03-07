package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.model.digitalpay.PaymentTransactionType

class CustomerPaymentRequestsApi(
    val client: SdkApiClient,
    val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a [CustomerPaymentRequest] by its ID
     *
     * @param paymentRequestId The ID.
     */
    suspend fun getById(paymentRequestId: String): ApiResult<CustomerPaymentRequest> {
        val unmarshaller = unmarshall(::fromData)(CustomerPaymentRequest::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/payments/:paymentRequestId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentRequestId" to paymentRequestId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Retrieve a [CustomerPaymentRequest] by a QR code ID associated to the request
     *
     * @param qrCodeId The QR Code ID.
     */
    suspend fun getByQRCodeId(qrCodeId: String): ApiResult<CustomerPaymentRequest> {
        val unmarshaller = unmarshall(::fromData)(CustomerPaymentRequest::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/qr/:qrCodeId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "qrCodeId" to qrCodeId
            ),
            queryParams = null,
            body = null
        )))
    }
    /**
     * Make a payment for a [CustomerPaymentRequest]
     *
     * @param paymentRequestId The [CustomerPaymentRequest] to pay for.
     * @param primaryInstrument The primary (or only) instrument to use to make the payment. If not present then the primary instrument from the customer preferences will be used.
     * @param secondaryInstruments Other payment instruments to use to split payment.
     * @param clientReference An optional client reference to be associated with the transaction.
     * @param preferences Optional payment preferences.
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     * @param fraudPayload Used to complete the fraud check.
     * @param transactionType  The transaction types to use for each instrument type.
     * @param allowPartialSuccess An optional flag allowing the consumer to indicate that a partial success will not trigger a failure and rollback
     */
    suspend fun makePayment(
        paymentRequestId: String,
        primaryInstrument: String? = null,
        secondaryInstruments: List<SecondaryPaymentInstrument>? = null,
        clientReference: String? = null,
        preferences: PaymentPreferences? = null,
        challengeResponses: List<ChallengeResponse>? = null,
        fraudPayload: FraudPayload? = null,
        transactionType: PaymentTransactionType? = null,
        allowPartialSuccess: Boolean? = null
    ): ApiResult<CustomerTransactionSummary> {
        val unmarshaller = unmarshall(::fromData)(CustomerTransactionSummary::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.PUT,
            url = HttpRequestUrl.String("/instore/customer/payments/:paymentRequestId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentRequestId" to paymentRequestId
            ),
            queryParams = null,
            body = ApiRequestBody(
                data = PaymentDetailsDTO(
                    primaryInstrumentId = primaryInstrument,
                    secondaryInstruments = secondaryInstruments,
                    skipRollback = null,
                    allowPartialSuccess = allowPartialSuccess,
                    clientReference = clientReference,
                    preferences = preferences,
                    transactionType = transactionType
                ),
                meta = when (challengeResponses) {
                    null -> Meta(fraudPayload)
                    else -> Meta(fraudPayload, challengeResponses)
                }
            )
        )))
    }

    /**
     * Create a new {@link CustomerPaymentRequest} and immediately make a charge against it
     *
     * @param immediatePaymentRequest Details of the payment being made
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     * @param fraudPayload Used to complete the fraud check.
     */
    suspend fun makeImmediatePayment(
        immediatePaymentRequest: ImmediatePaymentRequest,
        challengeResponses: List<ChallengeResponse>? = null,
        fraudPayload: FraudPayload? = null
    ): ApiResult<CustomerTransactionSummary> {
        val unmarshaller = unmarshall(::fromData)(CustomerTransactionSummary::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/customer/payments"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = immediatePaymentRequest,
                meta = when (challengeResponses) {
                    null -> Meta(fraudPayload)
                    else -> Meta(fraudPayload, challengeResponses)
                }
            )
        )))
    }
}