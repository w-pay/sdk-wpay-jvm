package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.walletmanagement.*
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.walletmanagement.importPaymentInstrumentsResponseFrom
import au.com.wpay.sdk.matchers.walletmanagement.listPaymentInstrumentsResponseFrom
import au.com.wpay.sdk.matchers.walletmanagement.verifyPaymentInstrumentsResponseFrom
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class InstrumentsApiTest : DescribeSpec({
    describe("InstrumentsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: InstrumentsApi

        beforeEach {
            apiClient = StubApiClient()

            api = InstrumentsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("import") {
            val request = importPaymentInstrumentsRequest()
            val response = importPaymentInstrumentsResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.import(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instruments/import"),
                    body = request
                ))
            }

            it("should import instruments") {
                val result = api.import(request)

                result.toEither().shouldBeRight(importPaymentInstrumentsResponseFrom(response))
            }
        }

        describe("verify") {
            val request = verifyPaymentInstrumentsRequest()
            val response = verifyPaymentInstrumentsResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.verify(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instruments/verify"),
                    body = request
                ))
            }

            it("should verify instruments") {
                val result = api.verify(request)

                result.toEither().shouldBeRight(verifyPaymentInstrumentsResponseFrom(response))
            }
        }

        describe("getList") {
            val response = listPaymentInstrumentsResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.getList()

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instruments")
                ))
            }

            it("should list instruments") {
                val result = api.getList()

                result.toEither().shouldBeRight(listPaymentInstrumentsResponseFrom(response))
            }
        }

        describe("postList") {
            val request = listPaymentInstrumentsRequest()
            val response = listPaymentInstrumentsResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.postList(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instruments"),
                    body = request
                ))
            }

            it("should list instruments") {
                val result = api.postList(request)

                result.toEither().shouldBeRight(listPaymentInstrumentsResponseFrom(response))
            }
        }

        describe("delete") {
            val paymentInstrumentId = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.delete(paymentInstrumentId)

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.DELETE,
                    url = HttpRequestUrl.String("/instruments/:paymentInstrumentId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentInstrumentId" to paymentInstrumentId
                    ),
                    queryParams = null,
                    body = null
                ))
            }
        }
    }
})
