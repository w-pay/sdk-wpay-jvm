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

    fun retrievePaymentRequestDetailsByRequestId(paymentRequestId: String): ApiResult<CustomerPaymentRequest>

    fun makePayment(paymentRequest: CustomerPaymentRequest, instrument: PaymentInstrument): ApiResult<CustomerTransactionSummary>

    fun retrievePaymentInstruments(): ApiResult<PaymentInstruments>

    fun initiatePaymentInstrumentAddition(instrument: PaymentInstrumentAddition): ApiResult<PaymentInstrumentAdditionResult>

    fun retrievePreferences(): ApiResult<CustomerPreferences>

    fun setPreferences(preferences: CustomerPreferences): ApiResult<Unit>
}