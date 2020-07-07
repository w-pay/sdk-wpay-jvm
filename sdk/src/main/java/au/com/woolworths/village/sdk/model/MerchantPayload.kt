package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface MerchantPayload: Serializable {
    fun schemaId(): String?

    fun payload(): Map<String, Any>
}