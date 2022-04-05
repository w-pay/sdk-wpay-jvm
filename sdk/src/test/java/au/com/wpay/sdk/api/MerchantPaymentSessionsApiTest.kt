package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.ApiRequestBody
import au.com.wpay.sdk.Meta
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.*
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.paymentSessionCreatedFrom
import au.com.wpay.sdk.matchers.paymentSessionFrom
import au.com.wpay.sdk.model.apiResponse
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class MerchantPaymentSessionsApiTest : DescribeSpec({
    describe("MerchantPaymentSessionsApi") {
        val paymentSessionId = UUID.randomUUID().toString()

        lateinit var apiClient: StubApiClient
        lateinit var api: MerchantPaymentSessionsApi

        beforeEach {
            apiClient = StubApiClient()

            api = MerchantPaymentSessionsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("create") {
            val request = aCreatePaymentSessionRequest()
            val paymentSessionResult = createPaymentSessionResultDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(paymentSessionResult)))
                    .build()
            }

            it("should set request params") {
                api.create(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/merchant/payment/session"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = request,
                        meta = Meta()
                    )
                ))
            }

            it("should create payment session") {
                val result = api.create(request)

                result.toEither().shouldBeRight(paymentSessionCreatedFrom(paymentSessionResult))
            }
        }

        describe("getById") {
            val paymentSession = paymentSessionDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(paymentSession)))
                    .build()
            }

            it("should set request params") {
                api.getById(paymentSessionId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/payment/session/:paymentSessionId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentSessionId" to paymentSessionId
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should get payment session") {
                val result = api.getById(paymentSessionId)

                result.toEither().shouldBeRight(paymentSessionFrom(paymentSession))
            }
        }

        describe("update") {
            val session = aMerchantUpdatePaymentSessionRequest()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.update(paymentSessionId, session)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/merchant/payment/session/:paymentSessionId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentSessionId" to paymentSessionId
                    ),
                    queryParams = null,
                    body = ApiRequestBody(
                        data = session,
                        meta = Meta()
                    )
                ))
            }
        }

        describe("delete") {
            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.delete(paymentSessionId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.DELETE,
                    url = HttpRequestUrl.String("/instore/merchant/payment/session/:paymentSessionId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentSessionId" to paymentSessionId
                    ),
                    queryParams = null,
                    body = null
                ))
            }
        }
    }
})
