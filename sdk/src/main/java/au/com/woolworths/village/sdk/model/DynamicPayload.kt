package au.com.woolworths.village.sdk.model

interface DynamicPayload {
    val schemaId: String?

    val payload: Map<String, Any>
}