package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Payload provided by the merchant to support other types of messaging.
 */
interface MerchantPayload: Serializable {
    /** The ID of the previously configured schema that will be used to validate the contents of the payload */
    val schemaId: String?

    /** The contents of the message */
    val payload: Map<String, Any>
}