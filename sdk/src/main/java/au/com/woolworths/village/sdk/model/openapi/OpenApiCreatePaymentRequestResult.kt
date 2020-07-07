package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.CreatePaymentRequestResult
import au.com.woolworths.village.sdk.model.QRCode
import au.com.woolworths.village.sdk.openapi.dto.CreatePaymentRequestResultsData

class OpenApiCreatePaymentRequestResult(
    private val result: CreatePaymentRequestResultsData
) : CreatePaymentRequestResult {
    override fun paymentRequestId(): String {
       return result.paymentRequestId
    }

    override fun qr(): QRCode? {
        return result.qr?.let { OpenApiQRCode(it) }
    }
}