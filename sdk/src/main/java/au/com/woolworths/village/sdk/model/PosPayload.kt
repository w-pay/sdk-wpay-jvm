package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Payload describing the specific POS system.
 */
interface PosPayload: Serializable {
    /** The ID of the previously configured schema that will be used to validate the contents of the payload */
    val schemaId: String?

    /** The contents of the message */
    val payload: Map<String, Any>
}