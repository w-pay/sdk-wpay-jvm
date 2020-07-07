package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.PosPayload

class OpenApiPosPayload(
    private val payload: au.com.woolworths.village.sdk.openapi.dto.PosPayload
): PosPayload {
    override fun schemaId(): String? {
        return payload.schemaId
    }

    override fun payload(): Map<String, Any> {
        return payload.payload as Map<String, Any>
    }
}