package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.model.MerchantSchema
import au.com.woolworths.village.sdk.model.MerchantSchemaSummaries
import au.com.woolworths.village.sdk.model.MerchantSchemaSummary
import au.com.woolworths.village.sdk.model.PaymentAgreements

class SchemasApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve the list of currently usable schemas previously added for the merchant
     */
    suspend fun list(): ApiResult<MerchantSchemaSummaries> {
        val unmarshaller = unmarshall(::fromData)(MerchantSchemaSummaries::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/merchant/schema")
        )))
    }

    /**
     * Retrieve details for a specific schema
     *
     * @param schemaId The schema to retrieve
     */
    suspend fun getById(schemaId: String): ApiResult<MerchantSchema>{
        val unmarshaller = unmarshall(::fromData)(MerchantSchema::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/merchant/schema/:schemaId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "schemaId" to schemaId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Create a new schema for the merchant
     *
     * @param schema The schema definition
     */
    suspend fun create(schema: MerchantSchema): ApiResult<MerchantSchemaSummary> {
        val unmarshaller = unmarshall(::fromData)(MerchantSchemaSummary::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/merchant/schema"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = schema,
                meta = Meta()
            )
        )))
    }
}