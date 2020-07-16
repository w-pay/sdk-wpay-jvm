package au.com.woolworths.village.sdk.model

interface DynamicPayload {
    fun schemaId(): String?

    fun payload(): Map<String, Any>
}