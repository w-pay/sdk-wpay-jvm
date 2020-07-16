package au.com.woolworths.village.sdk.openapi

import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.model.openapi.*
import au.com.woolworths.village.sdk.openapi.client.ApiException
import au.com.woolworths.village.sdk.openapi.dto.CustomerInstrumentsData
import au.com.woolworths.village.sdk.openapi.dto.CustomerPaymentSessionPaymentSessionIdData
import au.com.woolworths.village.sdk.openapi.dto.CustomerPaymentsPaymentRequestIdData
import au.com.woolworths.village.sdk.openapi.dto.InstrumentAdditionDetails
import org.threeten.bp.OffsetDateTime

/**
 * Uses an Open API generator client to interact with the Village API
 */
class OpenApiVillageCustomerApiRepository(
    requestHeadersFactory: RequestHeadersFactory,
    contextRoot: String
) : OpenApiClientFactory(requestHeadersFactory, contextRoot),
    VillageCustomerApiRepository
{
    override fun checkHealth(): ApiResult<HeathCheck> {
        val api = createAdministrationApi()
        return try {
            val data = api.checkHealth().data

            ApiResult.Success(OpenApiHealthCheck(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrieveTransactions(
        paymentRequestId: String?,
        page: Int?,
        pageSize: Int?,
        endTime: OffsetDateTime?,
        startTime: OffsetDateTime?
    ): ApiResult<CustomerTransactionSummaries> {
        val api = createCustomerApi()
        return try {
            val data = api.getCustomerTransactions(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                paymentRequestId,
                startTime,
                endTime,
                pageSize,
                page
            ).data

            ApiResult.Success(OpenApiCustomerTransactionSummaries(data.transactions))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrieveTransactionDetails(transactionId: String): ApiResult<CustomerTransactionDetails> {
        val api = createCustomerApi()
        return try {
            val data = api.getCustomerTransactionDetails(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                transactionId
            ).data

            ApiResult.Success(OpenApiCustomerTransactionDetails(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrievePaymentRequestDetailsByQRCode(qrCodeId: String): ApiResult<CustomerPaymentRequest> {
        val api = createCustomerApi()
        return try {
            val data = api.getCustomerPaymentDetailsByQRCodeId(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                qrCodeId
            ).data

            ApiResult.Success(OpenApiCustomerPaymentRequest(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrievePaymentRequestDetailsByRequestId(paymentRequestId: String): ApiResult<CustomerPaymentRequest> {
        val api = createCustomerApi()
        return try {
            val data = api.getCustomerPaymentDetailsByPaymentId(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                paymentRequestId
            ).data

            ApiResult.Success(OpenApiCustomerPaymentRequest(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrievePaymentInstruments(wallet: Wallet): ApiResult<AllPaymentInstruments> {
        val api = createCustomerApi()

        return try {
            val data = api.getCustomerPaymentInstruments(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                wallet == Wallet.EVERYDAY_PAY
            ).data

            ApiResult.Success(OpenApiAllPaymentInstruments(
                data.creditCards,
                data.giftCards,
                data.everydayPay
            ))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun initiatePaymentInstrumentAddition(
        instrument: PaymentInstrumentAddition,
        wallet: Wallet
    ): ApiResult<PaymentInstrumentAdditionResult> {
        val api = createCustomerApi()
        return try {
            val body = InstrumentAdditionDetails()
            body.data = CustomerInstrumentsData().apply {
                clientReference = instrument.clientReference()
            }

            val data = api.initiatePaymentInstrumentAddition(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                body,
                wallet == Wallet.EVERYDAY_PAY
            ).data

            ApiResult.Success(OpenApiPaymentInstrumentAdditionResult(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrievePreferences(): ApiResult<CustomerPreferences> {
        val api = createCustomerApi()
        return try {
            val data = api.getCustomerPreferences(
                getDefaultHeader(api.apiClient, X_WALLET_ID)
            ).data

            ApiResult.Success(data)
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun setPreferences(preferences: CustomerPreferences): ApiResult<Unit> {
        val api = createCustomerApi()

        return try {
            val body = au.com.woolworths.village.sdk.openapi.dto.CustomerPreferences()
            body.data = preferences

            api.setCustomerPreferences(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                body
            )

            ApiResult.Success(Unit)
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrieveCustomerPaymentSession(paymentSessionId: String): ApiResult<PaymentSession> {
        val api = createCustomerApi()

        return try {
            val data = api.getCustomerPaymentSession(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                paymentSessionId
            ).data

            ApiResult.Success(OpenApiPaymentSession(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrieveCustomerPaymentSessionByQR(qrCodeId: String): ApiResult<PaymentSession> {
        val api = createCustomerApi()

        return try {
            val data = api.getCustomerPaymentSessionByQr(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                qrCodeId
            ).data

            ApiResult.Success(OpenApiPaymentSession(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun updateCustomerPaymentSession(
        paymentSessionId: String,
        session: UpdatePaymentSessionRequest
    ): ApiResult<Unit> {
        val api = createCustomerApi()

        return try {
            val body = au.com.woolworths.village.sdk.openapi.dto.UpdatePaymentSessionRequest()
            body.data = CustomerPaymentSessionPaymentSessionIdData()
            body.data.additionalInfo = toDynamicPayload(session.additionalInfo())

            api.updateCustomerPaymentSession(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                paymentSessionId,
                body
            )

            ApiResult.Success(Unit)
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun makePayment(
        paymentRequest: CustomerPaymentRequest,
        instrument: PaymentInstrument
    ): ApiResult<CustomerTransactionSummary> {
        val api = createCustomerApi()

        return try {
            val body = au.com.woolworths.village.sdk.openapi.dto.CustomerPaymentDetails()
            body.data = CustomerPaymentsPaymentRequestIdData().apply {
                primaryInstrumentId = instrument.paymentInstrumentId()
                secondaryInstruments = listOf()
            }

            val data = api.makeCustomerPayment(
                getDefaultHeader(api.apiClient, X_WALLET_ID),
                paymentRequest.paymentRequestId(),
                body,
                instrument.wallet() == Wallet.EVERYDAY_PAY
            ).data

            ApiResult.Success(OpenApiCustomerTransactionSummary(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }
}