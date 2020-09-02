package au.com.woolworths.village.sdk.model

interface CreatePaymentSessionResult {
    val paymentSessionId: String

    val qr: QRCode?
}