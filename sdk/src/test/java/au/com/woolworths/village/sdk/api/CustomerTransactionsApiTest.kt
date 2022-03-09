package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.aJsonResponse
import au.com.woolworths.village.sdk.data.customerTransactionDetailsDTO
import au.com.woolworths.village.sdk.data.customerTransactionSummariesDTO
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.customerTransactionDetailsFrom
import au.com.woolworths.village.sdk.matchers.customerTransactionSummariesFrom
import au.com.woolworths.village.sdk.model.apiResponse
import au.com.woolworths.village.sdk.model.stringData
import au.com.woolworths.village.sdk.toIsoDateTime
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.threeten.bp.OffsetDateTime
import java.util.*

class CustomerTransactionsApiTest : DescribeSpec({
    describe("CustomerTransactionsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: CustomerTransactionsApi

        beforeEach {
            apiClient = StubApiClient()

            api = CustomerTransactionsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("list") {
            val transactionSummaries = customerTransactionSummariesDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(transactionSummaries)))
                    .build()
            }

            it("should set request params") {
                api.list()

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/transactions"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = emptyMap(),
                    body = null
                ))
            }

            it("should set optional request params") {
                val paymentRequestId = UUID.randomUUID().toString()
                val page = 10
                val pageSize = 50
                val endTime = OffsetDateTime.now()
                val startTime = OffsetDateTime.now()

                api.list(paymentRequestId, page, pageSize, endTime, startTime)

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/transactions"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = mapOf(
                        "paymentRequestId" to paymentRequestId,
                        "page" to page.toString(),
                        "pageSize" to pageSize.toString(),
                        "endTime" to endTime.toIsoDateTime(),
                        "startTime" to startTime.toIsoDateTime()
                    ),
                    body = null
                ))
            }

            it("should return customer transactions") {
                val result = api.list()

                result.toEither().shouldBeRight(customerTransactionSummariesFrom(transactionSummaries))
            }
        }

        describe("getById") {
            val transactionId = UUID.randomUUID().toString()
            val transactionDetails = customerTransactionDetailsDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(transactionDetails)))
                    .build()
            }

            it("should set request params") {
                api.getById(transactionId)

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/transactions/:transactionId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "transactionId" to transactionId
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should get customer transaction details") {
                val result = api.getById(transactionId)

                result.toEither().shouldBeRight(customerTransactionDetailsFrom(transactionDetails))
            }
        }
    }
})