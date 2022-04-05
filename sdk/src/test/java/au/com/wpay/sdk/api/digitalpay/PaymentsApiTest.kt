package au.com.wpay.sdk.api.digitalpay

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.digitalpay.*
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.digitalpay.digitalPayCompletionResponseFrom
import au.com.wpay.sdk.matchers.digitalpay.digitalPayPaymentResponseFrom
import au.com.wpay.sdk.matchers.digitalpay.digitalPayRefundResponseFrom
import au.com.wpay.sdk.matchers.digitalpay.digitalPayVoidResponseFrom
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PaymentsApiTest : DescribeSpec({
    describe("PaymentsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: PaymentsApi

        beforeEach {
            apiClient = StubApiClient()

            api = PaymentsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("pay") {
            val request = digitalPayPaymentRequest()
            val response = digitalPayPaymentResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.pay(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/payments"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should create payment request") {
                val result = api.pay(request)

                result.toEither().shouldBeRight(digitalPayPaymentResponseFrom(response))
            }
        }

        describe("guestPayment") {
            val request = digitalPayPaymentRequest()
            val response = digitalPayPaymentResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.guestPayment(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/guest/payments"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should create payment request") {
                val result = api.guestPayment(request)

                result.toEither().shouldBeRight(digitalPayPaymentResponseFrom(response))
            }
        }

        describe("complete") {
            val request = digitalPayCompletionRequest()
            val response = digitalPayCompletionResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.complete(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/completions"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should complete payment request") {
                val result = api.complete(request)

                result.toEither().shouldBeRight(digitalPayCompletionResponseFrom(response))
            }
        }

        describe("voidPayment") {
            val request = digitalPayVoidRequest()
            val response = digitalPayVoidResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.voidPayment(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/voids"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should void payment request") {
                val result = api.voidPayment(request)

                result.toEither().shouldBeRight(digitalPayVoidResponseFrom(response))
            }
        }

        describe("refund") {
            val request = digitalPayRefundRequest()
            val response = digitalPayRefundResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.refund(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/refunds"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should refund payment request") {
                val result = api.refund(request)

                result.toEither().shouldBeRight(digitalPayRefundResponseFrom(response))
            }
        }
    }
})
