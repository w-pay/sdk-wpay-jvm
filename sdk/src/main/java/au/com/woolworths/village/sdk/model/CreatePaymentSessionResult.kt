package au.com.woolworths.village.sdk.model

/**
 * The result of creating a [PaymentSession]
 */
interface CreatePaymentSessionResult {
    /** The ID of the new [PaymentSession] */
    val paymentSessionId: String

    /** A [QRCode] that is associated to the [PaymentSession] */
    val qr: QRCode?
}