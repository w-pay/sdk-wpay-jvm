package au.com.woolworths.village.sdk.model

interface CreatePaymentRequestResult {
    fun paymentRequestId(): String

    fun qr(): QRCode?
}