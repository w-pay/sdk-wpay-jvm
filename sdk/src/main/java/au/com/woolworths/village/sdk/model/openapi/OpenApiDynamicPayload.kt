package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.DynamicPayload

class OpenApiDynamicPayload(
    private val payload: au.com.woolworths.village.sdk.openapi.dto.DynamicPayload
): DynamicPayload {
    override fun schemaId(): String? {
        return payload.schemaId
    }

    override fun payload(): Map<String, Any> {
        return payload.payload as Map<String, Any>
    }
}