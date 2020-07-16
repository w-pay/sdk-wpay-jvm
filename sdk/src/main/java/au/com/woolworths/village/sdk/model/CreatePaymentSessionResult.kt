package au.com.woolworths.village.sdk.model

interface CreatePaymentSessionResult {
    fun paymentSessionId(): String

    fun qr(): QRCode?
}