package au.com.wpay.sdk.api.digitalpay

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.*
import au.com.wpay.sdk.data.*
import au.com.wpay.sdk.matchers.giftingOrderResponseFrom
import au.com.wpay.sdk.matchers.giftingProductDetailFrom
import au.com.wpay.sdk.matchers.giftingProductListFrom
import au.com.wpay.sdk.matchers.giftingQuoteFrom
import au.com.wpay.sdk.model.apiResponse
import au.com.wpay.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.threeten.bp.OffsetDateTime

class GiftingApiTest : DescribeSpec({
    describe("GiftingApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: GiftingApi

        beforeEach {
            apiClient = StubApiClient()

            api = GiftingApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("getProductById") {
            val productId = "product-1"
            val productDetails = giftingProductDetail()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(productDetails)))
                    .build()
            }

            it("should set request params") {
                api.getProductById(productId)

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/gifting/products/:productId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "productId" to productId
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should get product details") {
                val result = api.getProductById(productId)

                result.toEither().shouldBeRight(giftingProductDetailFrom(productDetails))
            }
        }

        describe("listProducts") {
            val productList = giftingProductList()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(productList)))
                    .build()
            }

            it("should set request params") {
                api.listProducts()

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/gifting/products"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = emptyMap(),
                    body = null
                ))
            }

            it("should set optional query params") {
                val page = 1
                val pageSize = 20
                val lastUpdateDateTime = OffsetDateTime.now()

                api.listProducts(page, pageSize, lastUpdateDateTime)

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/gifting/products"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = mapOf(
                        "page" to page.toString(),
                        "page-size" to pageSize.toString(),
                        "last-updated-date-time" to lastUpdateDateTime.toIsoDateTime()
                    ),
                    body = null
                ))
            }

            it("should get product list") {
                val result = api.listProducts()

                result.toEither().shouldBeRight(giftingProductListFrom(productList))
            }
        }

        describe("getQuote") {
            val request = giftingQuoteRequest()
            val quote = giftingQuoteResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(quote)))
                    .build()
            }

            it("should set request params") {
                api.getQuote(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/gifting/products/quote"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = request,
                        meta = Meta()
                    )
                ))
            }

            it("should obtain gifting quote") {
                val result = api.getQuote(request)

                result.toEither().shouldBeRight(giftingQuoteFrom(quote))
            }
        }

        describe("order") {
            val request = giftingOrderRequest()
            val order = giftingOrderResponse()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(order)))
                    .build()
            }

            it("should set request params") {
                api.order(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/gifting/products/order"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = request,
                        meta = Meta()
                    )
                ))
            }

            it("should set optional request params") {
                val challengeResponses = listOf(aChallengeResponse())
                val fraudPayload = fraudPayload()

                api.order(request, challengeResponses, fraudPayload)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/gifting/products/order"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = request,
                        meta = Meta(fraudPayload, challengeResponses)
                    )
                ))
            }

            it("should complete gifting order") {
                val result = api.order(request)

                result.toEither().shouldBeRight(giftingOrderResponseFrom(order))
            }
        }
    }
})
