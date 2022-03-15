package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.ISODateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.threeten.bp.OffsetDateTime

interface MerchantSchemas : ModelType

/**
 * List of merchant schemas
 */
@Serializable
data class MerchantSchemaSummaries(
    /** A list of currently valid schemas for this merchant */
    val schemas: List<MerchantSchemaSummary>
) : ModelType

/**
 * Summary information about a [MerchantSchema]
 */
@Serializable
data class MerchantSchemaSummary(
    /** The unique ID assigned to the schema */
    val schemaId: String,

    /** The type of the schema e.g. pos, merchant */
    val type: String,

    /** A description for the schema. */
    val description: String? = null
) : ModelType

/**
 * Details about a particular schema
 */
@Serializable
data class MerchantSchema(
    /** The schema content formatted according to JSON Schema standards */
    val schema: JsonObject,

    /** The type of the schema e.g. pos, merchant */
    val type: String? = null,

    /** A description for the schema */
    val description: String? = null,

    /** The timestamp when the schema was created */
    @Serializable(with = ISODateSerializer::class)
    val created: OffsetDateTime? = null
) : ModelType