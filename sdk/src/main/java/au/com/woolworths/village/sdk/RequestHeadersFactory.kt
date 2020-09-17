package au.com.woolworths.village.sdk

/**
 * Creates a Map of headers to be added to HTTP requests
 */
interface RequestHeadersFactory {
    /**
     * @return A Map of headers to be added to HTTP requests
     */
    fun createHeaders(): Map<String, String>
}