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
import au.com.wpay.sdk.matchers.customerPaymentRequestFrom
import au.com.wpay.sdk.matchers.customerTransactionSummaryFrom
import au.com.wpay.sdk.model.PaymentDetailsDTO
import au.com.wpay.sdk.model.apiResponse
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class CustomerPaymentRequestsApiTest : DescribeSpec({
    describe("CustomerPaymentRequestsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: CustomerPaymentRequestsApi

        beforeEach {
            apiClient = StubApiClient()

            api = CustomerPaymentRequestsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("getById") {
            val customerPaymentRequest = customerPaymentRequestDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(customerPaymentRequest)))
                    .build()
            }

            it("should set request params") {
                val paymentRequestId = UUID.randomUUID().toString()

                api.getById(paymentRequestId)

                apiClient.request.shouldBe(HttpRequest<Unit>(
                  method = HttpRequestMethod.GET,
                  url = HttpRequestUrl.String("/instore/customer/payments/:paymentRequestId"),
                  headers = emptyMap(),
                  pathParams = mapOf(
                      "paymentRequestId" to paymentRequestId
                  ),
                  queryParams = null,
                  body = null
                ))
            }

            it("should return CustomerPaymentRequest") {
                val result = api.getById(UUID.randomUUID().toString())

                result.toEither().shouldBeRight(customerPaymentRequestFrom(customerPaymentRequest))
            }
        }

        describe("getByQRCodeId") {
            val customerPaymentRequest = customerPaymentRequestDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(customerPaymentRequest)))
                    .build()
            }

            it("should set request params") {
                val qrCodeId = UUID.randomUUID().toString()

                api.getByQRCodeId(qrCodeId)

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/qr/:qrCodeId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "qrCodeId" to qrCodeId
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should return CustomerPaymentRequest") {
                val result = api.getByQRCodeId(UUID.randomUUID().toString())

                result.toEither().shouldBeRight(customerPaymentRequestFrom(customerPaymentRequest))
            }
        }

        describe("makePayment") {
            val transactionSummary = customerTransactionSummaryDTO()
            val paymentRequestId = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(transactionSummary)))
                    .build()
            }

            it("should set request params") {
                api.makePayment(paymentRequestId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.PUT,
                    url = HttpRequestUrl.String("/instore/customer/payments/:paymentRequestId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentRequestId" to paymentRequestId
                    ),
                    queryParams = null,
                    body = ApiRequestBody(PaymentDetailsDTO(), Meta())
                ))
            }

            it("should set optional parameters") {
                val primaryPaymentInstrument = UUID.randomUUID().toString()
                val secondaryPaymentInstruments = listOf(aSecondaryPaymentInstrument())
                // TODO: Investigate API spec.
                val skipRollback = true
                val allowPartialSuccess = true
                val clientReference = "this is a reference"
                val prefs = paymentPreferences()
                val challengeResponses = listOf(aChallengeResponse())
                val fraudPayload = fraudPayload()
                val transactionType = paymentTransactionType()

                api.makePayment(
                    paymentRequestId,
                    primaryPaymentInstrument,
                    secondaryPaymentInstruments,
                    clientReference,
                    prefs,
                    challengeResponses,
                    fraudPayload,
                    transactionType,
                    allowPartialSuccess
                )

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.PUT,
                    url = HttpRequestUrl.String("/instore/customer/payments/:paymentRequestId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentRequestId" to paymentRequestId
                    ),
                    queryParams = null,
                    body = ApiRequestBody(
                        PaymentDetailsDTO(
                            primaryInstrumentId = primaryPaymentInstrument,
                            secondaryInstruments = secondaryPaymentInstruments,
                            skipRollback = null,
                            allowPartialSuccess = allowPartialSuccess,
                            clientReference = clientReference,
                            preferences = prefs,
                            transactionType = transactionType
                        ),
                        Meta(fraudPayload, challengeResponses)
                    )
                ))
            }

            it("should make a payment") {
                val result = api.makePayment(UUID.randomUUID().toString())

                result.toEither().shouldBeRight(customerTransactionSummaryFrom(transactionSummary))
            }
        }

        describe("makeImmediatePayment") {
            val transactionSummary = customerTransactionSummaryDTO()
            val paymentRequestId = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(transactionSummary)))
                    .build()
            }

            it("should set request params") {
                val request = immediatePaymentRequest()

                api.makeImmediatePayment(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/customer/payments"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(request, Meta())
                ))
            }

            it("should set optional parameters") {
                val request = immediatePaymentRequest()
                val challengeResponses = listOf(aChallengeResponse())
                val fraudPayload = fraudPayload()

                api.makeImmediatePayment(request, challengeResponses, fraudPayload)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/customer/payments"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(request, Meta(fraudPayload, challengeResponses))
                ))
            }

            it("should make an immediate payment") {
                val request = immediatePaymentRequest()

                val result = api.makeImmediatePayment(request)

                result.toEither().shouldBeRight(customerTransactionSummaryFrom(transactionSummary))
            }
        }
    }
})
