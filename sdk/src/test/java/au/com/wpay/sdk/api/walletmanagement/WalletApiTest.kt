package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.walletmanagement.aWalletDeleteRequest
import au.com.wpay.sdk.kotlinxSerialisationMarshaller
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class WalletApiTest : DescribeSpec({
    describe("WalletApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: WalletApi

        beforeEach {
            apiClient = StubApiClient()

            api = WalletApi(
                apiClient.factory(),
                kotlinxSerialisationMarshaller(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("delete") {
            val request = aWalletDeleteRequest()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.delete(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/wallet/delete"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }
        }
    }
})
