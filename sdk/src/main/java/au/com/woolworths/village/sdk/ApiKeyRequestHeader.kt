package au.com.woolworths.village.sdk

/**
 * Adds the API key to the request
 *
 * @constructor
 * @param options What api key to add to the request
 */
class ApiKeyRequestHeader(
    private val options: VillageOptions
): RequestHeaderFactory {
    override fun addHeaders(headers: MutableMap<String, String>) {
        headers[X_API_KEY] = options.apiKey
    }
}