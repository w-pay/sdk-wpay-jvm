package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.NewPaymentRequestQRCode
import au.com.wpay.sdk.model.QRCode

class QRCodeApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Create a new QR code for an existing payment request
     *
     * @param details The details for the new QR code.
     */
    suspend fun createPaymentRequestQRCode(
        details: NewPaymentRequestQRCode
    ): ApiResult<QRCode> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::fromData)({ parser, el -> tryDecoding<QRCode>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/merchant/qr"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = details,
                meta = Meta()
            )
        )))
    }

    /**
     * Retrieve a [QRCode] that is associated to a Payment Request by its ID
     *
     * @param qrCodeId The ID to use.
     */
    suspend fun getPaymentRequestQRCodeContent(qrCodeId: String): ApiResult<QRCode> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::fromData)({ parser, el -> tryDecoding<QRCode>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/merchant/qr/:qrId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "qrId" to qrCodeId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Cancels a QR code making it unusable
     *
     * @param qrCodeId The ID of the QR code to cancel.
     */
    suspend fun cancelPaymentQRCode(qrCodeId: String): ApiResult<Unit> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<Unit>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.DELETE,
            url = HttpRequestUrl.String("/instore/merchant/qr/:qrId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "qrId" to qrCodeId
            ),
            queryParams = null,
            body = null
        )))
    }
}
