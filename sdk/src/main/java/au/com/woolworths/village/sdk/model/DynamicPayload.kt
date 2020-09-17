package au.com.woolworths.village.sdk.model

/**
 * A payload of key/value pairs that is can be validated by a schema.
 */
interface DynamicPayload {
    /** The ID of the previously configured schema that will be used to validate the contents of the payload. */
    val schemaId: String?

    /** The payload aligned to the supplied schema. */
    val payload: Map<String, Any>
}