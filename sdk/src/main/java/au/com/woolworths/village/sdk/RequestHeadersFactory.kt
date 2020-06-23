package au.com.woolworths.village.sdk

interface RequestHeadersFactory {
    fun createHeaders(): Map<String, String>
}