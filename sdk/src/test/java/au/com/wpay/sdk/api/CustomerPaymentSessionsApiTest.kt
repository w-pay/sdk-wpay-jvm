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
import au.com.wpay.sdk.matchers.paymentSessionFrom
import au.com.wpay.sdk.model.PaymentDetailsDTO
import au.com.wpay.sdk.model.apiResponse
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class CustomerPaymentSessionsApiTest : DescribeSpec({
    val paymentSessionId = UUID.randomUUID().toString()

    describe("CustomerPaymentSessionsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: CustomerPaymentSessionsApi

        beforeEach {
            apiClient = StubApiClient()

            api = CustomerPaymentSessionsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("getById") {
            val paymentSessionDTO = paymentSessionDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(paymentSessionDTO)))
                    .build()
            }

            it("should set request params") {
                api.getById(paymentSessionId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/payment/session/:paymentSessionId"),
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

                result.toEither().shouldBeRight(paymentSessionFrom(paymentSessionDTO))
            }
        }

        describe("getByQRCodeId") {
            val paymentSessionDTO = paymentSessionDTO()
            val qrCodeId = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(paymentSessionDTO)))
                    .build()
            }

            it("should set request params") {
                api.getByQRCodeId(qrCodeId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/payment/session/qr/:qrId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "qrId" to qrCodeId
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should get payment session") {
                val result = api.getByQRCodeId(qrCodeId)

                result.toEither().shouldBeRight(paymentSessionFrom(paymentSessionDTO))
            }
        }

        describe("update") {
            val session = aCustomerUpdatePaymentSessionRequest()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.update(paymentSessionId, session)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/customer/payment/session/:paymentSessionId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentSessionId" to paymentSessionId
                    ),
                    queryParams = null,
                    body = ApiRequestBody(session, Meta())
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
                    url = HttpRequestUrl.String("/instore/customer/payment/session/:paymentSessionId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentSessionId" to paymentSessionId
                    ),
                    queryParams = null,
                    body = null
                ))
            }
        }

        describe("preApprove") {
            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.preApprove(paymentSessionId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.PUT,
                    url = HttpRequestUrl.String("/instore/customer/payment/session/:paymentSessionId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentSessionId" to paymentSessionId
                    ),
                    queryParams = null,
                    body = ApiRequestBody(
                        data = PaymentDetailsDTO(),
                        meta = Meta()
                    )
                ))
            }

            it("should set optional request params") {
                val primaryInstrument = UUID.randomUUID().toString()
                val secondaryPaymentInstruments = listOf(aSecondaryPaymentInstrument())
                // TODO Investigate API spec
                val skipRollback = true
                val clientReference = "this is a reference"
                val prefs = paymentPreferences()
                val challengeResponses = listOf(aChallengeResponse())

                api.preApprove(
                    paymentSessionId,
                    primaryInstrument,
                    secondaryPaymentInstruments,
                    clientReference,
                    prefs,
                    challengeResponses
                )

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.PUT,
                    url = HttpRequestUrl.String("/instore/customer/payment/session/:paymentSessionId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentSessionId" to paymentSessionId
                    ),
                    queryParams = null,
                    body = ApiRequestBody(
                        data = PaymentDetailsDTO(
                            primaryInstrumentId = primaryInstrument,
                            secondaryInstruments = secondaryPaymentInstruments,
                            skipRollback = null,
                            allowPartialSuccess = null,
                            clientReference = clientReference,
                            preferences = prefs,
                            transactionType = null
                        ),
                        meta = Meta(null, challengeResponses)
                    )
                ))
            }
        }
    }
})
