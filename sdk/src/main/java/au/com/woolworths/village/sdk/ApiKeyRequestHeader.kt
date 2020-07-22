package au.com.woolworths.village.sdk

class ApiKeyRequestHeader(
    private val options: VillageOptions
): RequestHeaderFactory {
    override fun addHeaders(headers: MutableMap<String, String>) {
        headers[X_API_KEY] = options.apiKey
    }
}