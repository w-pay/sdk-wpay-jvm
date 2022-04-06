package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.*
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.chargePaymentAgreementRequest
import au.com.wpay.sdk.data.digitalpay.digitalPayPaymentAgreementResponseDTO
import au.com.wpay.sdk.data.fraudPayload
import au.com.wpay.sdk.matchers.digitalpay.digitalPayPaymentAgreementFrom
import au.com.wpay.sdk.model.apiResponse
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class MerchantPaymentAgreementsApiTest : DescribeSpec({
    describe("MerchantPaymentAgreementsApi") {
        val paymentToken = UUID.randomUUID().toString()

        lateinit var apiClient: StubApiClient
        lateinit var api: MerchantPaymentAgreementsApi

        beforeEach {
            apiClient = StubApiClient()

            api = MerchantPaymentAgreementsApi(
                apiClient.factory(),
                kotlinxSerialisationMarshaller(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("charge") {
            val paymentAgreement = digitalPayPaymentAgreementResponseDTO()
            val request = chargePaymentAgreementRequest()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(paymentAgreement)))
                    .build()
            }

            it("should set request params") {
                api.charge(paymentToken, request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.PUT,
                    url = HttpRequestUrl.String("/instore/merchant/payments/agreements/:paymentToken"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentToken" to paymentToken
                    ),
                    queryParams = null,
                    body = ApiRequestBody(
                        data = request,
                        meta = Meta()
                    )
                ))
            }

            it("should set optional params") {
                val fraud = fraudPayload()

                api.charge(paymentToken, request, fraud)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.PUT,
                    url = HttpRequestUrl.String("/instore/merchant/payments/agreements/:paymentToken"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentToken" to paymentToken
                    ),
                    queryParams = null,
                    body = ApiRequestBody(
                        data = request,
                        meta = Meta(fraud)
                    )
                ))
            }

            it("should charge payment agreement") {
                val result = api.charge(paymentToken, request)

                result.toEither().shouldBeRight(digitalPayPaymentAgreementFrom(paymentAgreement))
            }
        }

        describe("delete") {
            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.delete(paymentToken)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.DELETE,
                    url = HttpRequestUrl.String("/instore/merchant/payments/agreements/:paymentToken"),
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
