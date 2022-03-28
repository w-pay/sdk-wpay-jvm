package au.com.woolworths.village.sdk.api.digitalpay

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.aJsonResponse
import au.com.woolworths.village.sdk.data.digitalpay.*
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.digitalpay.openPayCompletionResponseFrom
import au.com.woolworths.village.sdk.matchers.digitalpay.openPayRefundResponseFrom
import au.com.woolworths.village.sdk.matchers.digitalpay.openPayTransactionResponseFrom
import au.com.woolworths.village.sdk.matchers.digitalpay.openPayVoidResponseFrom
import au.com.woolworths.village.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class OpenPayApiTest : DescribeSpec({
    describe("OpenApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: OpenPayApi

        beforeEach {
            apiClient = StubApiClient()

            api = OpenPayApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("pay") {
            val request = openPayPaymentRequest()
            val response = openPayTransactionResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.pay(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/openpay/payments"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should create payment request") {
                val result = api.pay(request)

                result.toEither().shouldBeRight(openPayTransactionResponseFrom(response))
            }
        }

        describe("complete") {
            val request = openPayCompletionRequest()
            val response = openPayCompletionResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.complete(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/openpay/completions"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should complete payment request") {
                val result = api.complete(request)

                result.toEither().shouldBeRight(openPayCompletionResponseFrom(response))
            }
        }

        describe("voidPayment") {
            val request = openPayVoidRequest()
            val response = openPayVoidResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.voidPayment(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/openpay/voids"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should void payment request") {
                val result = api.voidPayment(request)

                result.toEither().shouldBeRight(openPayVoidResponseFrom(response))
            }
        }

        describe("refund") {
            val request = openPayRefundRequest()
            val response = openPayRefundResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.refund(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/openpay/refunds"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should refund payment request") {
                val result = api.refund(request)

                result.toEither().shouldBeRight(openPayRefundResponseFrom(response))
            }
        }
    }
})
