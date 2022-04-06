package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.walletmanagement.initiateCardCaptureRequest
import au.com.wpay.sdk.data.walletmanagement.initiateCardCaptureResponseDTO
import au.com.wpay.sdk.kotlinxSerialisationMarshaller
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.walletmanagement.initCardCaptureResponseFrom
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CardCaptureApiTest : DescribeSpec({
    describe("Card Capture") {
        lateinit var apiClient: StubApiClient
        lateinit var api: CardCaptureApi

        val request = initiateCardCaptureRequest()
        val response = initiateCardCaptureResponseDTO()

        beforeEach {
            apiClient = StubApiClient()

            apiClient.response = aJsonResponse<UnstructuredData>()
                .withBody(stringData(response))
                .build()

            api = CardCaptureApi(
                apiClient.factory(),
                kotlinxSerialisationMarshaller(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("initCapture") {
            it("should set request params") {
                api.initCapture(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/cards/initcapture"),
                    body = request
                ))
            }

            it("should init card capture") {
                val result = api.initCapture(request)

                result.toEither().shouldBeRight(initCardCaptureResponseFrom(response))
            }
        }

        describe("guestInitCapture") {
            it("should set request params") {
                api.guestInitCapture(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/guest/cards/initcapture"),
                    body = request
                ))
            }

            it("should init card capture") {
                val result = api.guestInitCapture(request)

                result.toEither().shouldBeRight(initCardCaptureResponseFrom(response))
            }
        }
    }
})
