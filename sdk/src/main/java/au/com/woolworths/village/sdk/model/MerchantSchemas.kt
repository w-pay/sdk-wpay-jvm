package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime
import java.io.Serializable

interface MerchantSchemas : Serializable {
}

/**
 * List of merchant schemas
 */
interface MerchantSchemaSummaries : MerchantSchemas {
    /** A list of currently valid schemas for this merchant */
    val schemas: List<MerchantSchemaSummary>
}

/**
 * Summary information about a [MerchantSchema]
 */
interface MerchantSchemaSummary : Serializable {
    /** The unique ID assigned to the schema */
    val schemaId: String

    /** The type of the schema e.g. pos, merchant */
    val type: String

    /** A description for the schema. */
    val description: String?
}

/**
 * Details about a particular schema
 */
interface MerchantSchema : Serializable {
    /** The schema content formatted according to JSON Schema standards */
    val schema: Map<String, Any>

    /** The type of the schema e.g. pos, merchant */
    val type: String?

    /** A description for the schema */
    val description: String?

    /** The timestamp when the schema was created */
    val created: OffsetDateTime?
}