package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.MerchantSchema
import au.com.woolworths.village.sdk.model.MerchantSchemaSummaries
import au.com.woolworths.village.sdk.model.MerchantSchemaSummary
import au.com.woolworths.village.sdk.openapi.dto.MerchantSchemaDetailsResultData

class OpenApiMerchantSchemaSummaries(
    private val schemas: List<au.com.woolworths.village.sdk.openapi.dto.MerchantSchemaSummary>
) : MerchantSchemaSummaries {
    override fun schemas(): List<MerchantSchemaSummary> {
        return schemas.map { OpenApiMerchantSchemaSummary(it) }
    }
}

class OpenApiMerchantSchemaSummary(
    private val summary: au.com.woolworths.village.sdk.openapi.dto.MerchantSchemaSummary
) : MerchantSchemaSummary {
    override fun schemaId(): String {
        return summary.schemaId
    }

    override fun type(): String {
        return summary.type
    }

    override fun description(): String? {
        return summary.description
    }
}

class OpenApiMerchantSchema(
    private val schema: MerchantSchemaDetailsResultData
) : MerchantSchema {
    override fun schema(): Map<String, Any> {
        return schema.schema
    }

    override fun type(): String? {
        return schema.type
    }

    override fun description(): String? {
        return schema.description
    }
}