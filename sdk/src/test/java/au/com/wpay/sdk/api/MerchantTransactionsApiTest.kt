package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.merchantTransactionDetailsDTO
import au.com.wpay.sdk.data.merchantTransactionSummariesDTO
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.merchantTransactionDetailsFrom
import au.com.wpay.sdk.matchers.merchantTransactionSummariesFrom
import au.com.wpay.sdk.model.apiResponse
import au.com.wpay.sdk.model.stringData
import au.com.wpay.sdk.toIsoDateTime
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.threeten.bp.OffsetDateTime

class MerchantTransactionsApiTest : DescribeSpec({
    describe("MerchantTransactionsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: MerchantTransactionsApi

        beforeEach {
            apiClient = StubApiClient()

            api = MerchantTransactionsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("list") {
            val transactionSummaries = merchantTransactionSummariesDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(transactionSummaries)))
                    .build()
            }

            it("should set request params") {
                api.list()

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/transactions"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = emptyMap(),
                    body = null
                ))
            }

            it("should set optional request params") {
                val page = 10
                val pageSize = 50
                val endTime = OffsetDateTime.now()
                val startTime = OffsetDateTime.now()

                api.list(page, pageSize, endTime, startTime)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/transactions"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = mapOf(
                        "page" to page.toString(),
                        "pageSize" to pageSize.toString(),
                        "endTime" to endTime.toIsoDateTime(),
                        "startTime" to startTime.toIsoDateTime()
                    ),
                    body = null
                ))
            }

            it("should return merchant transactions") {
                val result = api.list()

                result.toEither().shouldBeRight(merchantTransactionSummariesFrom(transactionSummaries))
            }
        }

        describe("getById") {
            val transactionId = "gjkghdfjlsghjg"
            val transactionDetails = merchantTransactionDetailsDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(transactionDetails)))
                    .build()
            }

            it("should set request params") {
                api.getById(transactionId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/transactions/:transactionId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "transactionId" to transactionId
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should get merchant transaction details") {
                val result = api.getById(transactionId)

                result.toEither().shouldBeRight(merchantTransactionDetailsFrom(transactionDetails))
            }
        }
    }
})
