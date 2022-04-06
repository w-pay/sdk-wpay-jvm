package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.walletmanagement.tokenizeApplePayRequest
import au.com.wpay.sdk.data.walletmanagement.tokenizeApplePayResponseDTO
import au.com.wpay.sdk.kotlinxSerialisationMarshaller
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.walletmanagement.tokenizeApplePayResponseFrom
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class ApplePayApiTest : DescribeSpec({
    describe("ApplePay") {
        lateinit var apiClient: StubApiClient
        lateinit var api: ApplePayApi

        val request = tokenizeApplePayRequest()
        val response = tokenizeApplePayResponseDTO()

        beforeEach {
            apiClient = StubApiClient()

            apiClient.response = aJsonResponse<UnstructuredData>()
                .withBody(stringData(response))
                .build()

            api = ApplePayApi(
                apiClient.factory(),
                kotlinxSerialisationMarshaller(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("tokenize") {
            it("should set request params") {
                api.tokenize(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/applepay/tokenize"),
                    body = request
                ))
            }

            it("should tokenize") {
                val result = api.tokenize(request)

                result.toEither().shouldBeRight(tokenizeApplePayResponseFrom(response))
            }
        }

        describe("guestTokenize") {
            it("should set request params") {
                api.guestTokenize(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/guest/applepay/tokenize"),
                    body = request
                ))
            }

            it("should tokenize") {
                val result = api.tokenize(request)

                result.toEither().shouldBeRight(tokenizeApplePayResponseFrom(response))
            }
        }

        describe("update") {
            val paymentInstrumentId = UUID.randomUUID().toString()

            it("should set request params") {
                api.update(paymentInstrumentId, request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/applepay/tokenize/:paymentInstrumentId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentInstrumentId" to paymentInstrumentId
                    ),
                    queryParams = null,
                    body = request
                ))
            }

            it("should update") {
                val result = api.update(paymentInstrumentId, request)

                result.toEither().shouldBeRight(tokenizeApplePayResponseFrom(response))
            }
        }
    }
})
