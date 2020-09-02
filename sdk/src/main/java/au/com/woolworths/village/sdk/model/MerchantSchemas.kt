package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime

interface MerchantSchemas {
}

interface MerchantSchemaSummaries: MerchantSchemas {
    val schemas: List<MerchantSchemaSummary>
}

interface MerchantSchemaSummary {
    val schemaId: String

    val type: String

    val description: String?
}

interface MerchantSchema {
    val schema:  Map<String, Any>

    val type: String?

    val description: String?

    val created: OffsetDateTime?
}