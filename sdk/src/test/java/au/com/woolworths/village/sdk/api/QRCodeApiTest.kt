package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.ApiRequestBody
import au.com.woolworths.village.sdk.Meta
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.aJsonResponse
import au.com.woolworths.village.sdk.data.qrCodeDTO
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.qrCodeFrom
import au.com.woolworths.village.sdk.model.*
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.*

class QRCodeApiTest: DescribeSpec({
    describe("QRCodeApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: QRCodeApi

        beforeEach {
            apiClient = StubApiClient()

            api = QRCodeApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("createPaymentRequestQRCode") {
            val qrCode = qrCodeDTO()

            val details = NewPaymentRequestQRCode(
                referenceId = UUID.randomUUID().toString(),
                referenceType = QRCodePaymentReferenceType.PAYMENT_REQUEST
            )

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(qrCode)))
                    .build()
            }

            it("should set request params") {
                api.createPaymentRequestQRCode(details)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/merchant/qr"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = details,
                        meta = Meta()
                    )
                ))
            }

            it("should create payment request QR code") {
                val result = api.createPaymentRequestQRCode(details)

                result.toEither().shouldBeRight(qrCodeFrom(qrCode))
            }
        }

        describe("getPaymentRequestQRCodeContent") {
            val qrCodeId = UUID.randomUUID().toString()
            val qrCode = qrCodeDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(qrCode)))
                    .build()
            }

            it("should set request params") {
                api.getPaymentRequestQRCodeContent(qrCodeId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/qr/:qrId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "qrId" to qrCodeId
                    ),
                    queryParams = null,
                    body = null
                ))
            }

            it("should get payment request QR code") {
                val result = api.getPaymentRequestQRCodeContent(qrCodeId)

                result.toEither().shouldBeRight(qrCodeFrom(qrCode))
            }
        }

        describe("cancelPaymentQRCode") {
            val qrCodeId = UUID.randomUUID().toString()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.cancelPaymentQRCode(qrCodeId)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.DELETE,
                    url = HttpRequestUrl.String("/instore/merchant/qr/:qrId"),
                    headers = emptyMap(),
                    pathParams = mapOf(
                        "qrId" to qrCodeId
                    ),
                    queryParams = null,
                    body = null
                ))
            }
        }
    }
})