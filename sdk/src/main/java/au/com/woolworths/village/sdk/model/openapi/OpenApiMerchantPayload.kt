package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.MerchantPayload

class OpenApiMerchantPayload(
    private val payload: au.com.woolworths.village.sdk.openapi.dto.MerchantPayload
): MerchantPayload {
    override fun schemaId(): String? {
        return payload.schemaId
    }

    override fun payload(): Map<String, Any> {
        return payload.payload as Map<String, Any>
    }
}