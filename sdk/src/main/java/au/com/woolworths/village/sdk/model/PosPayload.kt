package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface PosPayload: Serializable {
    fun schemaId(): String?

    fun payload(): Map<String, Any>
}