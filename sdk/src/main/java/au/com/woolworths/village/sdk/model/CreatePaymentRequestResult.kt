package au.com.woolworths.village.sdk.model

interface CreatePaymentRequestResult {
    val paymentRequestId: String

    val qr: QRCode?
}