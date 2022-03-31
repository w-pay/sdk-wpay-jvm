package au.com.woolworths.village.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.aJsonResponse
import au.com.woolworths.village.sdk.data.walletmanagement.initiateCardCaptureRequest
import au.com.woolworths.village.sdk.data.walletmanagement.initiateCardCaptureResponseDTO
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.walletmanagement.initCardCaptureResponseFrom
import au.com.woolworths.village.sdk.model.stringData
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
                apiClient.client(),
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
