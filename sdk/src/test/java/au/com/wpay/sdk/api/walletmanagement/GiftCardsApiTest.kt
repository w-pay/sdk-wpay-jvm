package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.walletmanagement.giftCardsBalanceRequest
import au.com.wpay.sdk.data.walletmanagement.giftCardsBalanceResponseDTO
import au.com.wpay.sdk.data.walletmanagement.tokenizeGiftCardRequest
import au.com.wpay.sdk.data.walletmanagement.tokenizeGiftCardResponseDTO
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.walletmanagement.giftCardsBalanceFrom
import au.com.wpay.sdk.matchers.walletmanagement.tokenizeGiftCardResponseFrom
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GiftCardsApiTest : DescribeSpec({
    describe("GiftCards") {
        lateinit var apiClient: StubApiClient
        lateinit var api: GiftCardsApi

        beforeEach {
            apiClient = StubApiClient()

            api = GiftCardsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("tokenize") {
            val request = tokenizeGiftCardRequest()
            val response = tokenizeGiftCardResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.tokenize(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/giftcards/tokenize"),
                    body = request
                ))
            }

            it("should tokenise card") {
                val result = api.tokenize(request)

                result.toEither().shouldBeRight(tokenizeGiftCardResponseFrom(response))
            }
        }

        describe("guestTokenize") {
            val request = tokenizeGiftCardRequest()
            val response = tokenizeGiftCardResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.guestTokenize(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/guest/giftcards/tokenize"),
                    body = request
                ))
            }

            it("should tokenise card") {
                val result = api.guestTokenize(request)

                result.toEither().shouldBeRight(tokenizeGiftCardResponseFrom(response))
            }
        }

        describe("balance") {
            val request = giftCardsBalanceRequest()
            val response = giftCardsBalanceResponseDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request params") {
                api.balance(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/giftcards/balance"),
                    body = request
                ))
            }

            it("should return balance") {
                val result = api.balance(request)

                result.toEither().shouldBeRight(giftCardsBalanceFrom(response))
            }
        }
    }
})
