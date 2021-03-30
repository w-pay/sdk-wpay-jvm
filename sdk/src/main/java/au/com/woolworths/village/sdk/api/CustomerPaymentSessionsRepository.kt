package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.ChallengeResponse
import au.com.woolworths.village.sdk.model.CustomerUpdatePaymentSessionRequest
import au.com.woolworths.village.sdk.model.PaymentSession
import au.com.woolworths.village.sdk.model.SecondaryPaymentInstrument

interface CustomerPaymentSessionsRepository {
    /**
     * Retrieve a [PaymentSession] by it's ID
     *
     * @param paymentSessionId The payment session ID.
     */
    fun getById(paymentSessionId: String): ApiResult<PaymentSession>

    /**
     * Retrieve a [PaymentSession] by a QR code ID associated to the session.
     *
     * @param qrCodeId The QR code ID.
     */
    fun getByQRCodeId(qrCodeId: String): ApiResult<PaymentSession>

    /**
     * Update a [PaymentSession]
     *
     * @param paymentSessionId The payment session to update
     * @param session The updates to apply to the session
     */
    fun update(
        paymentSessionId: String,
        session: CustomerUpdatePaymentSessionRequest
    ): ApiResult<Unit>

    /**
     * Delete a [PaymentSession]
     *
     * @param paymentSessionId The payment session to delete
     */
    fun delete(paymentSessionId: String): ApiResult<Unit>

    /**
     * Pre-approve payment for a [PaymentSession]
     *
     * @param paymentSessionId The [PaymentSession] to pre-approve payment for.
     * @param primaryInstrument The primary (or only) instrument to use to make the payment.
     * @param secondaryInstruments Other payment instruments to use to split payment.
     * @param clientReference An optional client reference to be associated with the transaction.
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     */
    fun preApprove(
        paymentSessionId: String,
        primaryInstrument: String,
        secondaryInstruments: List<SecondaryPaymentInstrument>?,
        clientReference: String?,
        challengeResponses: List<ChallengeResponse>?
    ): ApiResult<Unit>
}