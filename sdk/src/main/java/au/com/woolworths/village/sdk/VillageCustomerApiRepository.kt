package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.model.*
import org.threeten.bp.OffsetDateTime

typealias CustomerPreferences = Map<String, Map<String, String>>

/**
 * Defines the low level API operations that the SDK can use to call the Customer Village API
 */
interface VillageCustomerApiRepository: VillageAdministrationApiRepository {
    fun retrieveTransactions(
        paymentRequestId: String?,
        page: Int?,
        pageSize: Int?,
        endTime: OffsetDateTime?,
        startTime: OffsetDateTime?
    ): ApiResult<CustomerTransactionSummaries>

    fun retrieveTransactionDetails(transactionId: String): ApiResult<CustomerTransactionDetails>

    fun retrievePaymentRequestDetailsByQRCode(qrCodeId: String): ApiResult<CustomerPaymentRequest>

    fun retrievePaymentRequestDetailsById(paymentRequestId: String): ApiResult<CustomerPaymentRequest>

    fun makePayment(
        paymentRequestId: String,
        instrument: PaymentInstrumentIdentifier,
        secondaryInstruments: List<SecondaryPaymentInstrument>?,
        clientReference: String?,
        challengeResponses: List<ChallengeResponse>?
    ): ApiResult<CustomerTransactionSummary>

    /**
     * @param wallet When set to MERCHANT retrieves only from merchant wallet. EVERYDAY_PAY retrieves both merchant and everyday pay wallet instruments.
     */
    fun retrievePaymentInstruments(wallet: Wallet): ApiResult<AllPaymentInstruments>

    fun deletePaymentInstrument(instrumentId: String): ApiResult<Unit>

    fun initiatePaymentInstrumentAddition(
        instrument: PaymentInstrumentAddition
    ): ApiResult<PaymentInstrumentAdditionResult>

    fun retrievePreferences(): ApiResult<CustomerPreferences>

    fun setPreferences(preferences: CustomerPreferences): ApiResult<Unit>

    fun retrievePaymentSessionById(paymentSessionId: String): ApiResult<PaymentSession>

    fun retrievePaymentSessionByQRCode(qrCodeId: String): ApiResult<PaymentSession>

    fun updatePaymentSession(
        paymentSessionId: String,
        session: CustomerUpdatePaymentSessionRequest
    ): ApiResult<Unit>
}