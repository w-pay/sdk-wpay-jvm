package au.com.woolworths.village.sdk.openapi

import au.com.woolworths.village.sdk.Configurable
import au.com.woolworths.village.sdk.RequestHeadersFactory
import au.com.woolworths.village.sdk.openapi.api.AdministrationApi
import au.com.woolworths.village.sdk.openapi.api.CustomerApi
import au.com.woolworths.village.sdk.openapi.api.MerchantApi
import au.com.woolworths.village.sdk.openapi.client.ApiClient

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

    private fun createApiClient(): ApiClient {
        val apiClient = ApiClient()
        apiClient.basePath = "${host}${contextRoot}"

        requestHeadersFactory.createHeaders().forEach { (name, value) ->
            apiClient.addDefaultHeader(name, value)
        }

        return apiClient
    }
}