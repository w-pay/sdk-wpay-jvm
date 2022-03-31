package au.com.woolworths.village.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.aJsonResponse
import au.com.woolworths.village.sdk.data.walletmanagement.tokenizeAndroidPayRequest
import au.com.woolworths.village.sdk.data.walletmanagement.tokenizeAndroidPayResponseDTO
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.walletmanagement.tokenizeAndroidPayResponseFrom
import au.com.woolworths.village.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class AndroidPayApiTest : DescribeSpec({
    describe("AndroidPay") {
        lateinit var apiClient: StubApiClient
        lateinit var api: AndroidPayApi

        val request = tokenizeAndroidPayRequest()
        val response = tokenizeAndroidPayResponseDTO()

        beforeEach {
            apiClient = StubApiClient()

            apiClient.response = aJsonResponse<UnstructuredData>()
                .withBody(stringData(response))
                .build()

            api = AndroidPayApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("tokenize") {
            it("should set request params") {
                api.tokenize(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/androidpay/tokenize"),
                    body = request
                ))
            }

            it("should tokenize") {
                val result = api.tokenize(request)

                result.toEither().shouldBeRight(tokenizeAndroidPayResponseFrom(response))
            }
        }

        describe("update") {
            val paymentInstrumentId = UUID.randomUUID().toString()

            it("should set request params") {
                api.update(paymentInstrumentId, request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/androidpay/tokenize/:paymentInstrumentId"),
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

                result.toEither().shouldBeRight(tokenizeAndroidPayResponseFrom(response))
            }
        }
    }
})
