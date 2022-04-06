package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.MerchantSchema
import au.com.wpay.sdk.model.MerchantSchemaSummaries
import au.com.wpay.sdk.model.MerchantSchemaSummary

class SchemasApi(
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve the list of currently usable schemas previously added for the merchant
     */
    suspend fun list(): ApiResult<MerchantSchemaSummaries> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall(unitEncoder),
            unmarshall(::fromData)({ parser, el -> tryDecoding<MerchantSchemaSummaries>(parser, el) })
        )

        return apiResult(client(HttpRequest<Unit>(
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
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall(unitEncoder),
            unmarshall(::fromData)({ parser, el -> tryDecoding<MerchantSchema>(parser, el) })
        )

        return apiResult(client(HttpRequest<Unit>(
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
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: ApiRequestBody<MerchantSchema, Meta> -> tryEncoding(parser, data) }),
            unmarshall(::fromData)({ parser, el -> tryDecoding<MerchantSchemaSummary>(parser, el) })
        )

        return apiResult(client(HttpRequest(
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
