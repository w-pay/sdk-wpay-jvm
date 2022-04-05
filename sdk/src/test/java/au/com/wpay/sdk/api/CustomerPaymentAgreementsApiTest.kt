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
import au.com.wpay.sdk.matchers.paymentAgreementFrom
import au.com.wpay.sdk.matchers.paymentAgreementsFrom
import au.com.wpay.sdk.model.apiResponse
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class CustomerPaymentAgreementsApiTest: DescribeSpec({
    describe("CustomerPaymentAgreementsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: CustomerPaymentAgreementsApi

        beforeEach {
            apiClient = StubApiClient()

            api = CustomerPaymentAgreementsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("list") {
            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(paymentAgreementsDTO())))
                    .build()
            }

            it("should set request params") {
                api.list()

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/payments/agreements")
                ))
            }

            it("should get payment agreements") {
                api.list().toEither().shouldBeRight(paymentAgreementsFrom(paymentAgreementsDTO()))
            }
        }

        describe("getById") {
            val paymentToken = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(paymentAgreementDTO())))
                    .build()
            }

            it("should set request params") {
                api.getById(paymentToken)

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/payments/agreements/:paymentToken"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentToken" to paymentToken
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should get payment agreement") {
                api.getById(paymentToken).toEither().shouldBeRight(
                    paymentAgreementFrom(paymentAgreementDTO())
                )
            }
        }

        describe("create") {
            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(paymentAgreementDTO())))
                    .build()
            }

            it("should set request params") {
                val request = createPaymentAgreementRequest()
                val challengeResponses = listOf(aChallengeResponse())
                val fraudPayload = fraudPayload()

                api.create(request, challengeResponses, fraudPayload)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/customer/payments/agreements"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(request, Meta(fraudPayload, challengeResponses))
                ))
            }

            it("should create payment agreement") {
                api.create(createPaymentAgreementRequest())
                    .toEither()
                    .shouldBeRight(paymentAgreementFrom(paymentAgreementDTO()))
            }
        }

        describe("update") {
            val paymentToken = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(paymentAgreementDTO())))
                    .build()
            }

            it("should set request params") {
                val request = updatePaymentAgreementRequest()
                val challengeResponses = listOf(aChallengeResponse())
                val fraudPayload = fraudPayload()

                api.update(paymentToken, request, challengeResponses, fraudPayload)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/customer/payments/agreements/:paymentToken"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentToken" to paymentToken
                    ),
                    queryParams = null,
                    body = ApiRequestBody(request, Meta(fraudPayload, challengeResponses))
                ))
            }

            it("should update payment agreement") {
                api.update(paymentToken, updatePaymentAgreementRequest())
                    .toEither()
                    .shouldBeRight(paymentAgreementFrom(paymentAgreementDTO()))
            }
        }
    }
})
