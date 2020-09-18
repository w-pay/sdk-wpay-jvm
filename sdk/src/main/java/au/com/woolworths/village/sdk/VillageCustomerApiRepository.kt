package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.model.*
import org.threeten.bp.OffsetDateTime

/**
 * Map of customer preferences.
 */
typealias CustomerPreferences = Map<String, Map<String, String>>

/**
 * Defines the API operations that the SDK can use to call the Customer Village API
 *
 * The SDK is technology agnostic with applications being able to choose an implementation that
 * meets the needs and preexisting technology choices of the application.
 *
 * Implementations of the protocol may provide additional constraints on the user.
 */
interface VillageCustomerApiRepository: VillageAdministrationApiRepository {
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
    ): ApiResult<CustomerTransactionSummaries>

    /**
     * Retrieve details about a specific transaction
     *
     * @param transactionId The transaction id
     */
    fun retrieveTransactionDetails(transactionId: String): ApiResult<CustomerTransactionDetails>

    /**
     * Retrieve a [CustomerPaymentRequest] by a QR code ID associated to the request
     *
     * @param qrCodeId The QR Code ID.
     */
    fun retrievePaymentRequestDetailsByQRCodeId(qrCodeId: String): ApiResult<CustomerPaymentRequest>

    /**
     * Retrieve a [CustomerPaymentRequest] by its ID
     *
     * @param paymentRequestId The ID.
     */
    fun retrievePaymentRequestDetailsById(paymentRequestId: String): ApiResult<CustomerPaymentRequest>

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
    ): ApiResult<CustomerTransactionSummary>

    /**
     * Retrieve the customer's registered [PaymentInstruments]
     *
     * @param wallet Whether to return only instruments registered by the customer for the merchant, or for the merchant and Everyday Pay
     */
    fun retrievePaymentInstruments(wallet: Wallet): ApiResult<AllPaymentInstruments>

    /**
     * Delete a [PaymentInstrument] from a [Wallet]
     *
     * @param instrument The payment instrument to delete.
     */
    fun deletePaymentInstrument(instrument: PaymentInstrumentIdentifier): ApiResult<Unit>

    /**
     * Initiate the addition of a new [PaymentInstrument] for the customer.
     *
     * To complete the addition the customer will have to use the returned URL details to enter
     * the instrument details.
     *
     * @param instrument Initial details to begin the addition workflow.
     */
    fun initiatePaymentInstrumentAddition(
        instrument: PaymentInstrumentAddition
    ): ApiResult<PaymentInstrumentAdditionResult>

    /**
     * Retrieve a customer's preferences
     */
    fun retrievePreferences(): ApiResult<CustomerPreferences>

    /**
     * Update the preferences for a customer
     *
     * @param preferences The preferences of the customer.
     */
    fun setPreferences(preferences: CustomerPreferences): ApiResult<Unit>

    /**
     * Retrieve a [PaymentSession] by it's ID
     *
     * @param paymentSessionId The payment session ID.
     */
    fun retrievePaymentSessionById(paymentSessionId: String): ApiResult<PaymentSession>

    /**
     * Retrieve a [PaymentSession] by a QR code ID associated to the session.
     *
     * @param qrCodeId The QR code ID.
     */
    fun retrievePaymentSessionByQRCodeId(qrCodeId: String): ApiResult<PaymentSession>

    /**
     * Update a [PaymentSession]
     *
     * @param paymentSessionId The payment session to update
     * @param session The updates to apply to the session
     */
    fun updatePaymentSession(
        paymentSessionId: String,
        session: CustomerUpdatePaymentSessionRequest
    ): ApiResult<Unit>

    /**
     * Delete a [PaymentSession]
     *
     * @param paymentSessionId The payment session to delete
     */
    fun deletePaymentSession(paymentSessionId: String): ApiResult<Unit>

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
    ): ApiResult<Unit>
}