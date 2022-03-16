package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.ApiRequestBody
import au.com.woolworths.village.sdk.Meta
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.*
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.individualPaymentInstrumentFrom
import au.com.woolworths.village.sdk.matchers.paymentInstrumentAddedFrom
import au.com.woolworths.village.sdk.matchers.walletContentsFrom
import au.com.woolworths.village.sdk.model.apiResponse
import au.com.woolworths.village.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

class PaymentInstrumentsApiTest : DescribeSpec({
    describe("PaymentInstrumentsApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: PaymentInstrumentsApi

        beforeEach {
            apiClient = StubApiClient()

            api = PaymentInstrumentsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("getByToken") {
            val instrument = individualPaymentInstrumentDTO()
            val cipherText = "fgjkagjkl;dkls;jsdjfdafjafkadkl;f"
            val response = apiResponse(instrument, JsonObject(mapOf(
                "cipherText" to JsonPrimitive(cipherText)
            )))
            val paymentToken = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(response))
                    .build()
            }

            it("should set request parameters") {
                api.getByToken(paymentToken)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/instruments/:paymentToken"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentToken" to paymentToken
                    ),
                    queryParams = emptyMap(),
                    body = null
                ))
            }

            it("should set optional request parameters") {
                val publicKey = "dkjfgadko;fgjai;gja;ig"

                api.getByToken(paymentToken, publicKey)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/instruments/:paymentToken"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentToken" to paymentToken
                    ),
                    queryParams = mapOf(
                        "publicKey" to publicKey
                    ),
                    body = null
                ))
            }

            it("should get payment instrument") {
                val result = api.getByToken(paymentToken)

                result.toEither().shouldBeRight(individualPaymentInstrumentFrom(response))
            }
        }

        describe("list") {
            val walletContents = walletContentsDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(walletContents)))
                    .build()
            }

            it("should set request params") {
                api.list()

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/instruments")
                ))
            }

            it("should list payment instruments") {
                val result = api.list()

                result.toEither().shouldBeRight(walletContentsFrom(walletContents))
            }
        }

        describe("delete") {
            val paymentInstrumentId = "dfadfdagaeg"

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.delete(paymentInstrumentId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.DELETE,
                    url = HttpRequestUrl.String("/instore/customer/instruments/:paymentInstrumentId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "paymentInstrumentId" to paymentInstrumentId
                    ),
                    queryParams = null,
                    body = null
                ))
            }
        }

        describe("initiateAddition") {
            val newPaymentInstrument = aNewPaymentInstrument()
            val additionResult = paymentInstrumentAdditionResultDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(additionResult)))
                    .build()
            }

            it("should set request params") {
                api.initiateAddition(newPaymentInstrument)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/customer/instruments"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = newPaymentInstrument,
                        meta = Meta()
                    )
                ))
            }

            it("should initiate addon") {
                val result = api.initiateAddition(newPaymentInstrument)

                result.toEither().shouldBeRight(paymentInstrumentAddedFrom(additionResult))
            }
        }
    }
})