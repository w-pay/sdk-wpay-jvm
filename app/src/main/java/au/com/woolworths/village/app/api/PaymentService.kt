package au.com.woolworths.village.app.api

import au.com.woolworths.village.sdk.api.CustomerApi
import au.com.woolworths.village.sdk.client.ApiException
import au.com.woolworths.village.sdk.client.Configuration
import au.com.woolworths.village.sdk.client.auth.HttpBearerAuth
import au.com.woolworths.village.sdk.dto.*

// TODO: This may need to be refactored to use a domain model rather than DTOs
class PaymentService {
    private val api = CustomerApi()

    init {
        // TODO: Update with a real way to set bearer tokens
        val auth: HttpBearerAuth = Configuration.getDefaultApiClient().getAuthentication("bearerAuth") as HttpBearerAuth
        auth.bearerToken = "ODA4NTYyNDktNjg0Ny00OWY4LWFmMDItOTU1MWEwMzliMjg5OlZJTExBR0VfQ1VTVE9NRVI="
    }

    fun setHost(host: String) {
        Configuration.getDefaultApiClient().basePath = host
    }

    suspend fun retrievePaymentRequestDetails(qrCodeId: String): ApiResult<CustomerPaymentDetail> {
        return try {
            val data = api.getCustomerPaymentDetailsByQRCodeId(qrCodeId).data

            ApiResult.Success(data)
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    suspend fun retrievePaymentInstruments(): ApiResult<GetCustomerPaymentInstrumentsResultsData> {
        return try {
            val data = api.customerPaymentInstruments.data

            ApiResult.Success(data)
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    suspend fun makePayment(paymentRequestId: String, instrumentId: String): ApiResult<MakeCustomerPaymentResults> {
        return try {
            val body = CustomerPaymentDetails()
            body.data = CustomerPaymentsPaymentRequestIdData()
            body.data.primaryInstrumentId = instrumentId
            body.data.secondaryInstruments = listOf()

            ApiResult.Success(api.makeCustomerPayment(paymentRequestId, body))
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }
}