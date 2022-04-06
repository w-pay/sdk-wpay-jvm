package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.*
import au.com.wpay.sdk.data.*
import au.com.wpay.sdk.matchers.merchantPaymentDetailsFrom
import au.com.wpay.sdk.matchers.merchantPaymentSummariesFrom
import au.com.wpay.sdk.matchers.merchantTransactionSummaryFrom
import au.com.wpay.sdk.matchers.paymentRequestCreatedFrom
import au.com.wpay.sdk.model.*
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class MerchantPaymentsApiTest : DescribeSpec({
    describe("MerchantPaymentsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: MerchantPaymentsApi

        beforeEach {
            apiClient = StubApiClient()

            api = MerchantPaymentsApi(
                apiClient.factory(),
                kotlinxSerialisationMarshaller(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("listPayments") {
            val merchantPaymentSummaries = merchantPaymentSummariesDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(merchantPaymentSummaries)))
                    .build()
            }

            it("should set request params") {
                api.listPayments()

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/payments"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = emptyMap(),
                    body = null
                ))
            }

            it("should pass optional params") {
                val type = "pos"
                val page = 1
                val pageSize = 10

                api.listPayments(type, page, pageSize)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/payments"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = mapOf(
                        "type" to type,
                        "page" to page.toString(),
                        "pageSize" to pageSize.toString()
                    ),
                    body = null
                ))
            }

            it("should list payments") {
                val result = api.listPayments()

                result.toEither().shouldBeRight(merchantPaymentSummariesFrom(merchantPaymentSummaries))
            }
        }

        describe("createPaymentRequest") {
            val createPaymentResult = createPaymentRequestResultDTO()
            val request = aNewPaymentRequest()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(createPaymentResult)))
                    .build()
            }

            it("should set request params") {
                api.createPaymentRequest(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/merchant/payments"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = request,
                        meta = Meta()
                    )
                ))
            }

            it("should create payment request") {
                val result = api.createPaymentRequest(request)

                result.toEither().shouldBeRight(paymentRequestCreatedFrom(createPaymentResult))
            }
        }

        describe("getPaymentRequestDetailsBy") {
            val paymentRequestId = UUID.randomUUID().toString()
            val merchantPaymentDetails = merchantPaymentDetailsDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(merchantPaymentDetails)))
                    .build()
            }

            it("should set request params") {
                api.getPaymentRequestDetailsBy(paymentRequestId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/payments/:paymentRequestId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentRequestId" to paymentRequestId
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should get payment request details") {
                val result = api.getPaymentRequestDetailsBy(paymentRequestId)

                result.toEither().shouldBeRight(merchantPaymentDetailsFrom(merchantPaymentDetails))
            }
        }

        describe("deletePaymentRequest") {
            val paymentRequestId = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.deletePaymentRequest(paymentRequestId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.DELETE,
                    url = HttpRequestUrl.String("/instore/merchant/payments/:paymentRequestId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentRequestId" to paymentRequestId
                    ),
                    queryParams = null,
                    body = null
                ))
            }
        }

        describe("refundTransaction") {
            val transactionId = UUID.randomUUID().toString()
            val refundDetails = TransactionRefundDetails(
                reason = "I want a refund"
            )

            val merchantTransactionSummary = merchantTransactionSummaryDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(merchantTransactionSummary)))
                    .build()
            }

            it("should set request params") {
                api.refundTransaction(transactionId, refundDetails)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/merchant/transactions/:transactionId/refund"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "transactionId" to transactionId
                    ),
                    queryParams = null,
                    body = ApiRequestBody(
                        data = refundDetails,
                        meta = Meta()
                    )
                ))
            }

            it("should refund transaction") {
                val result = api.refundTransaction(transactionId, refundDetails)

                result.toEither().shouldBeRight(merchantTransactionSummaryFrom(merchantTransactionSummary))
            }
        }

        describe("completeTransaction") {
            val transactionId = UUID.randomUUID().toString()
            val completionDetails = TransactionCompletionDetails(
                orderNumber = "order-123",
                clientReference = "ref-123"
            )

            val merchantTransactionSummary = merchantTransactionSummaryDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(merchantTransactionSummary)))
                    .build()
            }

            it("should set request params") {
                api.completeTransaction(transactionId, completionDetails)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/merchant/transactions/:transactionId/completion"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "transactionId" to transactionId
                    ),
                    queryParams = null,
                    body = ApiRequestBody(
                        data = completionDetails,
                        meta = Meta()
                    )
                ))
            }

            it("should complete transaction") {
                val result = api.completeTransaction(transactionId, completionDetails)

                result.toEither().shouldBeRight(merchantTransactionSummaryFrom(merchantTransactionSummary))
            }
        }

        describe("voidTransaction") {
            val transactionId = UUID.randomUUID().toString()
            val voidDetails = TransactionVoidDetails(
                orderNumber = "order-123",
                clientReference = "ref-123"
            )

            val merchantTransactionSummary = merchantTransactionSummaryDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(merchantTransactionSummary)))
                    .build()
            }

            it("should set request params") {
                api.voidTransaction(transactionId, voidDetails)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/merchant/transactions/:transactionId/void"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "transactionId" to transactionId
                    ),
                    queryParams = null,
                    body = ApiRequestBody(
                        data = voidDetails,
                        meta = Meta()
                    )
                ))
            }

            it("should complete transaction") {
                val result = api.voidTransaction(transactionId, voidDetails)

                result.toEither().shouldBeRight(merchantTransactionSummaryFrom(merchantTransactionSummary))
            }
        }
    }
})
