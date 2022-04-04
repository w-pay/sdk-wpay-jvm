package au.com.wpay.sdk.api.digitalpay

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.digitalpay.digitalPayChargePaymentAgreementRequest
import au.com.wpay.sdk.data.digitalpay.digitalPayCreatePaymentAgreementRequest
import au.com.wpay.sdk.data.digitalpay.digitalPayPaymentAgreementResponseDTO
import au.com.wpay.sdk.data.digitalpay.digitalPayUpdatePaymentAgreementRequest
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.digitalpay.digitalPayPaymentAgreementFrom
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class PaymentAgreementApiTest : DescribeSpec({
    describe("PaymentAgreementsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: PaymentAgreementApi

        beforeEach {
            apiClient = StubApiClient()

            api = PaymentAgreementApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("create") {
            val request = digitalPayCreatePaymentAgreementRequest()
            val response = digitalPayPaymentAgreementResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.create(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/paymentagreements"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should create payment agreement") {
                val result = api.create(request)

                result.toEither().shouldBeRight(digitalPayPaymentAgreementFrom(response))
            }
        }

        describe("update") {
            val paymentToken = UUID.randomUUID().toString()
            val request = digitalPayUpdatePaymentAgreementRequest()
            val response = digitalPayPaymentAgreementResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.update(paymentToken, request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/paymentagreements/:paymentToken"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentToken" to paymentToken
                    ),
                    queryParams = null,
                    body = request
                ))
            }

            it("should update payment agreement") {
                val result = api.update(paymentToken, request)

                result.toEither().shouldBeRight(digitalPayPaymentAgreementFrom(response))
            }
        }

        describe("charge") {
            val request = digitalPayChargePaymentAgreementRequest()
            val response = digitalPayPaymentAgreementResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.charge(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/paymentagreements/charge"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should charge payment agreement") {
                val result = api.charge(request)

                result.toEither().shouldBeRight(digitalPayPaymentAgreementFrom(response))
            }
        }

        describe("delete") {
            val paymentToken = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.delete(paymentToken)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.DELETE,
                    url = HttpRequestUrl.String("/paymentagreements/:paymentToken"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentToken" to paymentToken
                    ),
                    queryParams = null,
                    body = null
                ))
            }
        }
    }
})
