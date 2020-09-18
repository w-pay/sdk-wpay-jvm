package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.model.*
import org.threeten.bp.OffsetDateTime

/**
 * Entry point into the SDK for customers. It is responsible for managing the relationship between
 * the app concerns, and calling the API.
 *
 * @constructor
 * @param A The type of authentication details
 * @param api The API repository to use
 * @param authenticator The [ApiAuthenticator] to use to obtain authorisation needed to access the API
*/
class CustomerVillage<A : Any>(
    private val api: VillageCustomerApiRepository,
    private val authenticator: ApiAuthenticator<A>
): Configurable {
    /**
     * Allows the application to change the host the SDK sends API requests too.
     *
     * This allows the application to read data from an outside source (eg: a QR code) and have
     * the SDK use the same host.
     */
    override fun setHost(host: String) {
        authenticator.setHost(host)
        api.setHost(host)
    }

    /**
     * Used to have the SDK authenticate with the API.
     */
    fun authenticate(): ApiResult<A> {
        return authenticator.authenticate()
    }

    /**
     * Retrieve a [CustomerPaymentRequest] by the QR code ID associated to the request
     *
     * @param qrCodeId The QR code ID
     */
    fun retrievePaymentRequestDetailsByQRCodeId(qrCodeId: String): ApiResult<CustomerPaymentRequest> {
        return api.retrievePaymentRequestDetailsByQRCodeId(qrCodeId)
    }

    /**
     * Retrieve the customer's registered [PaymentInstruments]
     *
     * @param wallet Whether to return only instruments registered by the customer for the merchant, or for the merchant and Everyday Pay
     */
    fun retrievePaymentInstruments(wallet: Wallet): ApiResult<AllPaymentInstruments> {
        return api.retrievePaymentInstruments(wallet)
    }

    /**
     * Make a payment for a [CustomerPaymentRequest]
     *
     * @param paymentRequestId The [CustomerPaymentRequest] to pay for.
     * @param instrument The primary (or only) instrument to use to make the payment.
     * @param secondaryInstruments Other payment instruments to use to split payment.
     * @param clientReference An optional client reference to be associated with the transaction.
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     */
    fun makePayment(
        paymentRequestId: String,
        instrument: PaymentInstrumentIdentifier,
        secondaryInstruments: List<SecondaryPaymentInstrument>?,
        clientReference: String?,
        challengeResponses: List<ChallengeResponse>?
    ): ApiResult<CustomerTransactionSummary> {
        return api.makePayment(
            paymentRequestId,
            instrument,
            secondaryInstruments,
            clientReference,
            challengeResponses
        )
    }

    /**
     * Retrieve a [PaymentSession] by it's ID
     *
     * @param paymentSessionId The ID of the session
     */
    fun retrievePaymentSessionById(paymentSessionId: String): ApiResult<PaymentSession> {
        return api.retrievePaymentSessionById(paymentSessionId)
    }

    /**
     * Retrieve a [PaymentSession] by a QR code ID associated to the session
     *
     * @param qrCodeId The QR code ID
     */
    fun retrievePaymentSessionByQRCodeId(qrCodeId: String): ApiResult<PaymentSession> {
        return api.retrievePaymentSessionByQRCodeId(qrCodeId)
    }

    /**
     * Delete a [PaymentSession]
     *
     * @param paymentSessionId The payment session ID
     */
    fun deletePaymentSession(paymentSessionId: String): ApiResult<Unit> {
        return api.deletePaymentSession(paymentSessionId)
    }

    /**
     * Pre-approve payment for a [PaymentSession]
     *
     * @param paymentSessionId The [PaymentSession] to pre-approve payment for.
     * @param primaryInstrument The primary (or only) instrument to use to make the payment.
     * @param secondaryInstruments Other payment instruments to use to split payment.
     * @param clientReference An optional client reference to be associated with the transaction.
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     */
    fun preApprovePaymentSession(
        paymentSessionId: String,
        primaryInstrument: PaymentInstrumentIdentifier,
        secondaryInstruments: List<SecondaryPaymentInstrument>?,
        clientReference: String?,
        challengeResponses: List<ChallengeResponse>?
    ): ApiResult<Unit> {
        return api.preApprovePaymentSession(
            paymentSessionId,
            primaryInstrument,
            secondaryInstruments,
            clientReference,
            challengeResponses
        )
    }

    /**
     * Retrieve a list of previously executed transactions for the customer.
     *
     * @param paymentRequestId If present, limits the list of transactions to those that relate to the payment request.
     * @param page The page of results to return with 1 indicating the first page (defaults to 1).
     * @param pageSize The number of records to return for this page (current default is 25)
     * @param endTime If present, transactions newer than this time will not be returned.
     * @param startTime If present, transactions older than this time will not be returned
     */
    fun retrieveTransactions(
        paymentRequestId: String?,
        page: Int?,
        pageSize: Int?,
        endTime: OffsetDateTime?,
        startTime: OffsetDateTime?
    ): ApiResult<CustomerTransactionSummaries> {
        return api.retrieveTransactions(paymentRequestId, page, pageSize, endTime, startTime)
    }
}