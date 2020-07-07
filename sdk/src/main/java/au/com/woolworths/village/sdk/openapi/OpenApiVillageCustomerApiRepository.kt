package au.com.woolworths.village.sdk.openapi

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.CustomerPreferences
import au.com.woolworths.village.sdk.RequestHeadersFactory
import au.com.woolworths.village.sdk.VillageCustomerApiRepository
import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.model.openapi.*
import au.com.woolworths.village.sdk.openapi.client.ApiException
import au.com.woolworths.village.sdk.openapi.dto.CustomerInstrumentsData
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
            val data = api.getCustomerTransactions(paymentRequestId, startTime, endTime, pageSize, page).data

            ApiResult.Success(OpenApiCustomerTransactionSummaries(data.transactions))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrieveTransactionDetails(transactionId: String): ApiResult<CustomerTransactionDetails> {
        val api = createCustomerApi()
        return try {
            val data = api.getCustomerTransactionDetails(transactionId).data

            ApiResult.Success(OpenApiCustomerTransactionDetails(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrievePaymentRequestDetailsByQRCode(qrCodeId: String): ApiResult<CustomerPaymentRequest> {
        val api = createCustomerApi()
        return try {
            val data = api.getCustomerPaymentDetailsByQRCodeId(qrCodeId).data

            ApiResult.Success(OpenApiCustomerPaymentRequest(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrievePaymentRequestDetailsByRequestId(paymentRequestId: String): ApiResult<CustomerPaymentRequest> {
        val api = createCustomerApi()
        return try {
            val data = api.getCustomerPaymentDetailsByPaymentId(paymentRequestId).data

            ApiResult.Success(OpenApiCustomerPaymentRequest(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrievePaymentInstruments(): ApiResult<PaymentInstruments> {
        val api = createCustomerApi()

        return try {
            val data = api.customerPaymentInstruments.data

            ApiResult.Success(OpenApiPaymentInstruments(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun initiatePaymentInstrumentAddition(instrument: PaymentInstrumentAddition): ApiResult<PaymentInstrumentAdditionResult> {
        val api = createCustomerApi()
        return try {
            val body = InstrumentAdditionDetails()
            body.data = CustomerInstrumentsData().apply {
                clientReference = instrument.clientReference()
            }

            val data = api.initiatePaymentInstrumentAddition(body).data

            ApiResult.Success(OpenApiPaymentInstrumentAdditionResult(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun retrievePreferences(): ApiResult<CustomerPreferences> {
        val api = createCustomerApi()
        return try {
            val data = api.customerPreferences.data

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

            api.setCustomerPreferences(body)

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
                paymentRequest.paymentRequestId(),
                body
            ).data

            ApiResult.Success(OpenApiCustomerTransactionSummary(data))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }
}