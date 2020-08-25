package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.model.*
import org.threeten.bp.OffsetDateTime

typealias MerchantPreferences = Map<String, Map<String, String>>

interface VillageMerchantApiRepository: VillageAdministrationApiRepository {
    fun retrieveTransactions(
        page: Int?,
        pageSize: Int?,
        endTime: OffsetDateTime?,
        startTime: OffsetDateTime?
    ): ApiResult<MerchantTransactionSummaries>

    fun retrieveTransactionDetails(transactionId: String): ApiResult<MerchantTransactionDetails>

    fun createPaymentRequestQRCode(details: NewPaymentRequestQRCode): ApiResult<QRCode>

    fun retrievePaymentRequestQRCodeContent(qrCodeId: String): ApiResult<QRCode>

    fun cancelPaymentQRCode(qrCodeId: String): ApiResult<Unit>

    fun retrievePaymentList(
        type: String?,
        page: Int?,
        pageSize: Int?
    ): ApiResult<MerchantPaymentSummaries>

    fun createNewPaymentRequest(paymentRequest: NewPaymentRequest): ApiResult<CreatePaymentRequestResult>

    fun retrievePaymentRequestDetails(paymentRequestId: String): ApiResult<MerchantPaymentDetails>

    fun deletePaymentRequest(paymentRequestId: String): ApiResult<Unit>

    fun refundTransaction(transactionId: String, refundDetails: TransactionRefundDetails): ApiResult<MerchantTransactionSummary>

    fun retrievePreferences(): ApiResult<MerchantPreferences>

    fun setPreferences(preferences: MerchantPreferences): ApiResult<Unit>

    fun retrieveSchemas(): ApiResult<MerchantSchemaSummaries>

    fun retrieveSchemaDetails(schemaId: String): ApiResult<MerchantSchema>

    fun createSchema(schema: MerchantSchema): ApiResult<MerchantSchemaSummary>

    fun createPaymentSession(request: CreatePaymentSessionRequest): ApiResult<CreatePaymentSessionResult>

    fun retrievePaymentSession(paymentSessionId: String): ApiResult<PaymentSession>

    fun updatePaymentSession(paymentSessionId: String, session: MerchantUpdatePaymentSessionRequest): ApiResult<Unit>

    fun deletePaymentSession(paymentSessionId: String): ApiResult<Unit>
}