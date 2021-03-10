package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.MerchantSchema
import au.com.woolworths.village.sdk.model.MerchantSchemaSummaries
import au.com.woolworths.village.sdk.model.MerchantSchemaSummary

interface SchemasRepository {
    /**
     * Retrieve the list of currently usable schemas previously added for the merchant
     */
    fun list(): ApiResult<MerchantSchemaSummaries>

    /**
     * Retrieve details for a specific schema
     *
     * @param schemaId The schema to retrieve
     */
    fun getById(schemaId: String): ApiResult<MerchantSchema>

    /**
     * Create a new schema for the merchant
     *
     * @param schema The schema definition
     */
    fun create(schema: MerchantSchema): ApiResult<MerchantSchemaSummary>
}