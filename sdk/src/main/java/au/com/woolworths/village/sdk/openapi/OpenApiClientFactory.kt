package au.com.woolworths.village.sdk.openapi

import au.com.woolworths.village.sdk.ApiException
import au.com.woolworths.village.sdk.Configurable
import au.com.woolworths.village.sdk.RequestHeadersFactory
import au.com.woolworths.village.sdk.openapi.api.AdministrationApi
import au.com.woolworths.village.sdk.openapi.api.CustomerApi
import au.com.woolworths.village.sdk.openapi.api.MerchantApi
import au.com.woolworths.village.sdk.openapi.client.ApiClient
import au.com.woolworths.village.sdk.openapi.dto.DynamicPayload

open class OpenApiClientFactory(
    private val requestHeadersFactory: RequestHeadersFactory,
    private var contextRoot: String
): Configurable {
    private var host: String = "localhost:3000"

    override fun setHost(host: String) {
        this.host = host
    }

    protected fun createAdministrationApi(): AdministrationApi {
        return AdministrationApi(createApiClient())
    }

    protected fun createCustomerApi(): CustomerApi {
        return CustomerApi(createApiClient())
    }

    protected fun createMerchantApi(): MerchantApi {
        return MerchantApi(createApiClient())
    }

    protected fun getDefaultHeader(client: ApiClient, name: String): String? {
        return (client as ExtendedApiClient).getDefaultHeader(name)
    }

    protected fun toDynamicPayload(payload: au.com.woolworths.village.sdk.model.DynamicPayload): DynamicPayload {
        val dto = DynamicPayload()

        dto.schemaId = payload.schemaId()
        dto.payload = payload.payload()

        return dto
    }

    protected fun toApiException(e: au.com.woolworths.village.sdk.openapi.client.ApiException): ApiException {
        return ApiException(e.code, e.responseHeaders, e.responseBody)
    }

    private fun createApiClient(): ApiClient {
        val apiClient = ExtendedApiClient()
        apiClient.basePath = "${host}${contextRoot}"

        requestHeadersFactory.createHeaders().forEach { (name, value) ->
            apiClient.addDefaultHeader(name, value)
        }

        return apiClient
    }
}