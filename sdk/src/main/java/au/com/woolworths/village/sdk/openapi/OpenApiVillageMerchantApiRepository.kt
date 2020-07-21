package au.com.woolworths.village.sdk.openapi

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.X_WALLET_ID
import au.com.woolworths.village.sdk.MerchantPreferences
import au.com.woolworths.village.sdk.RequestHeadersFactory
import au.com.woolworths.village.sdk.VillageMerchantApiRepository
import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.model.CreatePaymentSessionRequest
import au.com.woolworths.village.sdk.model.MerchantSchema
import au.com.woolworths.village.sdk.model.MerchantSchemaSummary
import au.com.woolworths.village.sdk.model.MerchantTransactionSummary
import au.com.woolworths.village.sdk.model.PaymentSession
import au.com.woolworths.village.sdk.model.openapi.*
import au.com.woolworths.village.sdk.openapi.client.ApiException
import au.com.woolworths.village.sdk.openapi.dto.*
import org.threeten.bp.OffsetDateTime

class OpenApiVillageMerchantApiRepository(
    requestHeadersFactory: RequestHeadersFactory,
    contextRoot: String
) : OpenApiClientFactory(requestHeadersFactory, contextRoot),
    VillageMerchantApiRepository
{
    override fun retrieveTransactions(
        page: Int?,
        pageSize: Int?,
        endTime: OffsetDateTime?,
        startTime: OffsetDateTime?
    ): ApiResult<MerchantTransactionSummaries> {
        val api = createMerchantApi()

        return try {
            val data = api.getMerchantTransactions(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                startTime,
                endTime,
                pageSize,
                page).data

            ApiResult.Success(OpenApiMerchantTransactionSummaries(data.transactions))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun retrieveTransactionDetails(transactionId: String): ApiResult<MerchantTransactionDetails> {
        val api = createMerchantApi()
        return try {
            val data = api.getMerchantTransactionDetails(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                transactionId
            ).data

            ApiResult.Success(OpenApiMerchantTransactionDetails(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun createPaymentRequestQRCode(details: NewPaymentRequestQRCode): ApiResult<QRCode> {
        val api = createMerchantApi()

        return try {
            val body = PaymentQRCodeDetails()
            body.data = MerchantQrData().apply {
                referenceId = details.referenceId()
                timeToLive = details.timeToLive()

                referenceType = when (details.referenceType()) {
                    QRCodePaymentReferenceType.PAYMENT_SESSION -> MerchantQrData.ReferenceTypeEnum.SESSION
                    QRCodePaymentReferenceType.PAYMENT_REQUEST -> MerchantQrData.ReferenceTypeEnum.REQUEST
                }
            }

            val data = api.createPaymentQRCode(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                body
            ).data

            ApiResult.Success(OpenApiQRCode(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun retrievePaymentRequestQRCodeContent(qrCodeId: String): ApiResult<QRCode> {
        val api = createMerchantApi()

        return try {
            val data = api.getPaymentQRCodeContent(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                qrCodeId
            ).data

            ApiResult.Success(OpenApiQRCode(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun cancelPaymentQRCode(qrCodeId: String): ApiResult<Unit> {
        val api = createMerchantApi()

        return try {
            api.cancelPaymentQRCode(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                qrCodeId
            )

            ApiResult.Success(Unit)
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun retrievePaymentList(type: String?, page: Int?, pageSize: Int?): ApiResult<MerchantPaymentSummaries> {
        val api = createMerchantApi()

        return try {
            val data = api.getMerchantPayments(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                type,
                pageSize,
                page
            ).data

            ApiResult.Success(OpenApiMerchantPaymentSummaries(data.payments))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun createNewPaymentRequest(paymentRequest: NewPaymentRequest): ApiResult<CreatePaymentRequestResult> {
        val api = createMerchantApi()

        return try {
            val body = MerchantPaymentRequest()
            body.data = MerchantPaymentsData().apply {
                merchantReferenceId = paymentRequest.merchantReferenceId()
                grossAmount = paymentRequest.grossAmount()
                generateQR = paymentRequest.generateQR()
                paymentRequest.maxUses()?.let { maxUses = it }
                paymentRequest.timeToLivePayment()?.let { timeToLivePayment = it }
                paymentRequest.timeToLiveQR()?.let { timeToLiveQR = it }
                paymentRequest.specificWalletId()?.let { specificWalletId = it }

                paymentRequest.posPayload()?.let { posPayload = it.run {
                    val dto = au.com.woolworths.village.sdk.openapi.dto.PosPayload()
                    dto.schemaId(schemaId())
                    dto.payload(payload())
                } }

                paymentRequest.merchantPayload()?.let { merchantPayload = it.run {
                    val dto = au.com.woolworths.village.sdk.openapi.dto.MerchantPayload()
                    dto.schemaId(schemaId())
                    dto.payload(payload())
                } }

                paymentRequest.basket()?.let { aBasket ->
                    basket = aBasket.run {
                        val basket = au.com.woolworths.village.sdk.openapi.dto.Basket()

                        basket.items = items().map { anItem ->
                            val item = au.com.woolworths.village.sdk.openapi.dto.BasketItems()
                            item.label = anItem.label()
                            anItem.description()?.let { item.description = it }
                            anItem.quantity()?.let { item.quantity = it }
                            anItem.unitPrice()?.let { item.unitPrice = it }
                            anItem.unitMeasure()?.let { item.unitMeasure = it }
                            anItem.totalPrice()?.let { item.totalPrice = it }
                            item.tags = anItem.tags()

                            return@map item
                        }

                        return@run basket
                    }
                }
            }

            val data = api.createPaymentRequest(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                body
            ).data

            ApiResult.Success(OpenApiCreatePaymentRequestResult(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun retrievePaymentRequestDetails(paymentRequestId: String): ApiResult<MerchantPaymentDetails> {
        val api = createMerchantApi()

        return try {
            val data = api.getMerchantPaymentDetails(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                paymentRequestId
            ).data

            ApiResult.Success(OpenApiMerchantPaymentDetails(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun deletePaymentRequest(paymentRequestId: String): ApiResult<Unit> {
        val api = createMerchantApi()

        return try {
            api.deleteMerchantPayment(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                paymentRequestId
            )

            ApiResult.Success(Unit)
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun refundTransaction(
        transactionId: String,
        refundDetails: TransactionRefundDetails
    ): ApiResult<MerchantTransactionSummary> {
        val api = createMerchantApi()

        return try {
            val body = RefundMerchantTransactionRequest()
            body.data = MerchantTransactionsTransactionIdRefundData().apply {
                reason = refundDetails.reason()
            }

            val data = api.refundMerchantTransaction(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                transactionId,
                body
            ).data

            ApiResult.Success(OpenApiMerchantTransactionSummary(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun retrievePreferences(): ApiResult<MerchantPreferences> {
        val api = createMerchantApi()

        return try {
            val data = api.getMerchantPreferences(
                getDefaultHeader(api.apiClient, X_WALLET_ID)
            ).data

            ApiResult.Success(data)
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun setPreferences(preferences: MerchantPreferences): ApiResult<Unit> {
        val api = createMerchantApi()

        return try {
            val body = au.com.woolworths.village.sdk.openapi.dto.MerchantPreferences()
            body.data = preferences

            api.setMerchantPreferences(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                body
            )

            ApiResult.Success(Unit)
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun retrieveSchemas(): ApiResult<MerchantSchemaSummaries> {
        val api = createMerchantApi()

        return try {
            val data = api.getMerchantSchemas(
                getDefaultHeader(api.apiClient, X_WALLET_ID)
            ).data

            ApiResult.Success(OpenApiMerchantSchemaSummaries(data.schemas))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun retrieveSchemaDetails(schemaId: String): ApiResult<MerchantSchema> {
        val api = createMerchantApi()

        return try {
            val data = api.getMerchantSchemaDetails(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                schemaId
            ).data

            ApiResult.Success(OpenApiMerchantSchema(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun createSchema(schema: MerchantSchema): ApiResult<MerchantSchemaSummary> {
        val api = createMerchantApi()

        return try {
            val body = au.com.woolworths.village.sdk.openapi.dto.MerchantSchema()
            body.data = MerchantSchemaData().apply {
                this.schema = schema.schema()
                schema.type()?.let { type = it }
                schema.description()?.let { description = it }
            }

            val data = api.createMerchantSchema(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                body
            ).data

            ApiResult.Success(OpenApiMerchantSchemaSummary(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun createPaymentSession(request: CreatePaymentSessionRequest): ApiResult<CreatePaymentSessionResult> {
        val api = createMerchantApi()

        return try {
            val body = au.com.woolworths.village.sdk.openapi.dto.CreatePaymentSessionRequest()
            body.data = MerchantPaymentSessionData().apply {
                location = request.location()
                additionalInfo = toDynamicPayload(request.additionalInfo())
                generateQR = request.generateQR()
                timeToLivePaymentSession = request.timeToLivePaymentSession()
                timeToLiveQR = request.timeToLiveQR()
            }

            val data = api.createCustomerPaymentSession(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                body
            ).data

            ApiResult.Success(OpenApiCreatePaymentSessionResult(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun retrievePaymentSession(paymentSessionId: String): ApiResult<PaymentSession> {
        val api = createMerchantApi()

        return try {
            val data = api.getMerchantPaymentSession(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                paymentSessionId
            ).data

            ApiResult.Success(OpenApiPaymentSession(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }

    override fun checkHealth(): ApiResult<HeathCheck> {
        val api = createAdministrationApi()
        return try {
            val data = api.checkHealth().data

            ApiResult.Success(OpenApiHealthCheck(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(toApiException(e))
        }
    }
}