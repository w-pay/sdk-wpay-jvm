package au.com.woolworths.village.sdk

/**
 * Abstracts how HTTP headers are added to a Map.
 */
interface RequestHeaderFactory {
    /**
     * Adds a name and value to a Map of HTTP request headers
     *
     * @param headers A Map of HTTP request headers
     */
    fun addHeaders(headers: MutableMap<String, String>)
}