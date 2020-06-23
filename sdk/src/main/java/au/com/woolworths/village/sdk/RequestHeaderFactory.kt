package au.com.woolworths.village.sdk

interface RequestHeaderFactory {
    fun addHeaders(headers: MutableMap<String, String>)
}