package au.com.woolworths.village.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.aJsonResponse
import au.com.woolworths.village.sdk.data.walletmanagement.tokenizePaypalRequest
import au.com.woolworths.village.sdk.data.walletmanagement.tokenizePaypalResponseDTO
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.walletmanagement.tokenizePayPalResponseFrom
import au.com.woolworths.village.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PayPalApiTest : DescribeSpec({
    describe("PayPal") {
        lateinit var apiClient: StubApiClient
        lateinit var api: PayPalApi

        val request = tokenizePaypalRequest()
        val response = tokenizePaypalResponseDTO()

        beforeEach {
            apiClient = StubApiClient()

            api = PayPalApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )

            apiClient.response = aJsonResponse<UnstructuredData>()
                .withBody(stringData(response))
                .build()
        }

        describe("tokenize") {
            it("should set request params") {
                api.tokenize(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/paypal/tokenize"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should tokenize instrument") {
                val result = api.tokenize(request)

                result.toEither().shouldBeRight(tokenizePayPalResponseFrom(response))
            }
        }

        describe("guestTokenize") {
            it("should set request params") {
                api.guestTokenize(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/guest/paypal/tokenize"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should tokenize instrument") {
                val result = api.tokenize(request)

                result.toEither().shouldBeRight(tokenizePayPalResponseFrom(response))
            }
        }
    }
})
