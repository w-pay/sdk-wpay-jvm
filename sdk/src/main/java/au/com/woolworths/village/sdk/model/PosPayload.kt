package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface PosPayload: Serializable {
    val schemaId: String?

    val payload: Map<String, Any>
}