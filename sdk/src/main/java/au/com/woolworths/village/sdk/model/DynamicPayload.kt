package au.com.woolworths.village.sdk.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * A payload of key/value pairs that is can be validated by a schema.
 */
@Serializable
data class DynamicPayload(
    /** The ID of the previously configured schema that will be used to validate the contents of the payload. */
    val schemaId: String? = null,

    /**
     * The payload aligned to the supplied schema.
     *
     * Due the payload being any JSON compliant object, it is "dynamically" typed.
     */
    val payload: JsonObject
) : ModelType