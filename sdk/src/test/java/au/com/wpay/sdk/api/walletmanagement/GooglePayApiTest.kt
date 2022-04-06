package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.walletmanagement.tokenizeGooglePayRequest
import au.com.wpay.sdk.data.walletmanagement.tokenizeGooglePayResponseDTO
import au.com.wpay.sdk.kotlinxSerialisationMarshaller
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.walletmanagement.tokenizeGooglePayResponseFrom
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class GooglePayApiTest : DescribeSpec({
    describe("GooglePay") {
        lateinit var apiClient: StubApiClient
        lateinit var api: GooglePayApi

        val request = tokenizeGooglePayRequest()
        val response = tokenizeGooglePayResponseDTO()

        beforeEach {
            apiClient = StubApiClient()

            apiClient.response = aJsonResponse<UnstructuredData>()
                .withBody(stringData(response))
                .build()

            api = GooglePayApi(
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
                    url = HttpRequestUrl.String("/googlepay/tokenize"),
                    body = request
                ))
            }

            it("should tokenise") {
                val result = api.tokenize(request)

                result.toEither().shouldBeRight(tokenizeGooglePayResponseFrom(response))
            }
        }

        describe("guestTokenize") {
            it("should set request params") {
                api.guestTokenize(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/guest/googlepay/tokenize"),
                    body = request
                ))
            }

            it("should tokenise") {
                val result = api.guestTokenize(request)

                result.toEither().shouldBeRight(tokenizeGooglePayResponseFrom(response))
            }
        }

        describe("update") {
            val paymentToken = UUID.randomUUID().toString()

            it("should set request params") {
                api.update(paymentToken, request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/googlepay/tokenize/:paymentToken"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentToken" to paymentToken
                    ),
                    queryParams = null,
                    body = request
                ))
            }

            it("should update") {
                val result = api.update(paymentToken, request)

                result.toEither().shouldBeRight(tokenizeGooglePayResponseFrom(response))
            }
        }
    }
})
