package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.CreatePaymentSessionResult
import au.com.woolworths.village.sdk.model.QRCode
import au.com.woolworths.village.sdk.openapi.dto.CreateMerchantPaymentSessionResponseData

class OpenApiCreatePaymentSessionResult(
    private val result: CreateMerchantPaymentSessionResponseData
): CreatePaymentSessionResult {
    override fun paymentSessionId(): String {
        return result.paymentSessionId
    }

    override fun qr(): QRCode? {
        return result.qr?.let { OpenApiQRCode(it) }
    }
}