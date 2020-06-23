package au.com.woolworths.village.sdk

class RequestHeaderChain(
    private val factories: Array<RequestHeaderFactory>
) : RequestHeadersFactory {
    override fun createHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()

        factories.forEach { it.addHeaders(headers) }

        return headers
    }
}