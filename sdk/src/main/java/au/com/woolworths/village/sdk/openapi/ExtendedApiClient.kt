package au.com.woolworths.village.sdk.openapi

import au.com.woolworths.village.sdk.openapi.client.ApiClient

class ExtendedApiClient: ApiClient() {
    private var defaultHeaders: MutableMap<String, String>? = null

    override fun addDefaultHeader(key: String, value: String): ApiClient {
        return when (defaultHeaders) {
            null -> {
                defaultHeaders = HashMap()

                addDefaultHeader(key, value)
            }
            else -> {
                defaultHeaders?.let { it[key] = value }

                super.addDefaultHeader(key, value)
            }
        }

    }

    fun getDefaultHeader(name: String): String? {
        return defaultHeaders?.let { it[name] }
    }
}