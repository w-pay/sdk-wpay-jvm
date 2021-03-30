package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.*

interface CustomerPaymentRequestsRepository {
    /**
     * Retrieve a [CustomerPaymentRequest] by its ID
     *
     * @param paymentRequestId The ID.
     */
    fun getById(paymentRequestId: String): ApiResult<CustomerPaymentRequest>

    /**
     * Retrieve a [CustomerPaymentRequest] by a QR code ID associated to the request
     *
     * @param qrCodeId The QR Code ID.
     */
    fun getByQRCodeId(qrCodeId: String): ApiResult<CustomerPaymentRequest>

    /**
     * Make a payment for a [CustomerPaymentRequest]
     *
     * @param paymentRequestId The [CustomerPaymentRequest] to pay for.
     * @param primaryInstrument The primary (or only) instrument to use to make the payment. If not present then the primary instrument from the customer preferences will be used.
     * @param secondaryInstruments Other payment instruments to use to split payment.
     * @param clientReference An optional client reference to be associated with the transaction.
     * @param preferences Optional payment preferences.
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     */
    fun makePayment(
        paymentRequestId: String,
        primaryInstrument: String?,
        secondaryInstruments: List<SecondaryPaymentInstrument>?,
        clientReference: String?,
        preferences: PaymentPreferences?,
        challengeResponses: List<ChallengeResponse>?
    ): ApiResult<CustomerTransactionSummary>
}