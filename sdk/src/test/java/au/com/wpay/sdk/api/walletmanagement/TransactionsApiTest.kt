package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.walletmanagement.transactionHistoryRequestDTO
import au.com.wpay.sdk.data.walletmanagement.transactionHistoryResponseDTO
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.walletmanagement.transactionHistoryResponseFrom
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TransactionsApiTest : DescribeSpec({
    describe("TransactionsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: TransactionsApi

        beforeEach {
            apiClient = StubApiClient()

            api = TransactionsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("history") {
            val request = transactionHistoryRequestDTO()
            val response = transactionHistoryResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.history(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/transactions"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = request
                ))
            }

            it("should return transaction history") {
                val result = api.history(request)

                result.toEither().shouldBeRight(transactionHistoryResponseFrom(response))
            }
        }
    }
})
