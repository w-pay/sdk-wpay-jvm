package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface MerchantPayload: Serializable {
    val schemaId: String?

    val payload: Map<String, Any>
}