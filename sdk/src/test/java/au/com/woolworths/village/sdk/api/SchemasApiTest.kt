package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.ApiRequestBody
import au.com.woolworths.village.sdk.Meta
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.*
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.merchantSchemaFrom
import au.com.woolworths.village.sdk.matchers.merchantSchemaSummariesFrom
import au.com.woolworths.village.sdk.matchers.merchantSchemaSummaryFrom
import au.com.woolworths.village.sdk.model.apiResponse
import au.com.woolworths.village.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SchemasApiTest : DescribeSpec({
    describe("SchemasApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: SchemasApi

        beforeEach {
            apiClient = StubApiClient()

            api = SchemasApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("list") {
            val merchantSummaries = merchantSchemaSummariesDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(merchantSummaries)))
                    .build()
            }

            it("should set request params") {
                api.list()

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/schema")
                ))
            }

            it("should list schemas") {
                val result = api.list()

                result.toEither().shouldBeRight(merchantSchemaSummariesFrom(merchantSummaries))
            }
        }

        describe("getById") {
            val schemaId = "abc234fer"
            val merchantSchema = merchantSchemaDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(merchantSchema)))
                    .build()
            }

            it("should set request params") {
                api.getById(schemaId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/schema/:schemaId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "schemaId" to schemaId
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should return schema details") {
                val result = api.getById(schemaId)

                result.toEither().shouldBeRight(merchantSchemaFrom(merchantSchema))
            }
        }

        describe("create") {
            val schema = aNewMerchantSchema()
            val merchantSummary = merchantSchemaSummaryDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(merchantSummary)))
                    .build()
            }

            it("should set request params") {
                api.create(schema)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/merchant/schema"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = schema,
                        meta = Meta()
                    )
                ))
            }

            it("should create schema") {
                val result = api.create(schema)

                result.toEither().shouldBeRight(merchantSchemaSummaryFrom(merchantSummary))
            }
        }
    }
})