package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.walletmanagement.merchantProfileResponseDTO
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.walletmanagement.merchantProfileFrom
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MerchantsApiTest : DescribeSpec({
    describe("MerchantsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: MerchantsApi

        beforeEach {
            apiClient = StubApiClient()

            api = MerchantsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("profile") {
            val response = merchantProfileResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.profile()

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/merchants/profile")
                ))
            }

            it("should return profile") {
                val result = api.profile()

                result.toEither().shouldBeRight(merchantProfileFrom(response))
            }
        }
    }
})
