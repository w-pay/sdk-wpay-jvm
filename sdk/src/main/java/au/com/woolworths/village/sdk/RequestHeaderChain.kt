package au.com.woolworths.village.sdk

/**
 * A [RequestHeadersFactory] that populates the HTTP request headers Map using a list of [RequestHeaderFactory]s
 *
 * @constructor
 * @param factories The list of [RequestHeaderFactory]s to use.
 */
class RequestHeaderChain(
    private val factories: List<RequestHeaderFactory>
) : RequestHeadersFactory {
    override fun createHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()

        factories.forEach { it.addHeaders(headers) }

        return headers
    }
}