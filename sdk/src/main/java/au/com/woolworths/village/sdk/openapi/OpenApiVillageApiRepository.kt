package au.com.woolworths.village.sdk.openapi

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.RequestHeadersFactory
import au.com.woolworths.village.sdk.VillageApiRepository
import au.com.woolworths.village.sdk.openapi.api.CustomerApi
import au.com.woolworths.village.sdk.openapi.client.ApiClient
import au.com.woolworths.village.sdk.openapi.client.ApiException
import au.com.woolworths.village.sdk.openapi.dto.CustomerPaymentsPaymentRequestIdData
import au.com.woolworths.village.sdk.openapi.dto.MakeCustomerPaymentResults
import au.com.woolworths.village.sdk.model.CustomerPaymentRequest
import au.com.woolworths.village.sdk.model.PaymentInstrument
import au.com.woolworths.village.sdk.model.PaymentInstruments
import au.com.woolworths.village.sdk.model.PaymentResult
import au.com.woolworths.village.sdk.model.openapi.OpenApiCustomerPaymentRequest
import au.com.woolworths.village.sdk.model.openapi.OpenApiPaymentInstruments
import au.com.woolworths.village.sdk.model.openapi.OpenApiPaymentResult

/**
 * Uses an Open API generator client to interact with the Village API
 */
class OpenApiVillageApiRepository(
    private val requestHeadersFactory: RequestHeadersFactory,
    private var contextRoot: String
): VillageApiRepository {
    private var host: String = "localhost:3000"

    override fun retrievePaymentRequestDetails(qrCodeId: String): ApiResult<CustomerPaymentRequest> {
        val api = createCustomerApi()
        return try {
            val data = api.getCustomerPaymentDetailsByQRCodeId(qrCodeId).data

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

    override fun makePayment(
        paymentRequest: CustomerPaymentRequest,
        instrument: PaymentInstrument
    ): ApiResult<PaymentResult> {
        val api = createCustomerApi()

        return try {
            val body = au.com.woolworths.village.sdk.openapi.dto.CustomerPaymentDetails()
            body.data = CustomerPaymentsPaymentRequestIdData()
            body.data.primaryInstrumentId = instrument.paymentInstrumentId()
            body.data.secondaryInstruments = listOf()

            val result: MakeCustomerPaymentResults = api.makeCustomerPayment(
                paymentRequest.paymentRequestId(),
                body
            )

            ApiResult.Success(OpenApiPaymentResult())
        }
        catch (e: ApiException) {
            ApiResult.Error(e)
        }
    }

    override fun setHost(host: String) {
        this.host = host
    }

    private fun createCustomerApi(): CustomerApi {
        val apiClient = ApiClient()
        apiClient.basePath = "${host}${contextRoot}"

        requestHeadersFactory.createHeaders().forEach { (name, value) ->
            apiClient.addDefaultHeader(name, value)
        }

        return CustomerApi(apiClient)
    }
}