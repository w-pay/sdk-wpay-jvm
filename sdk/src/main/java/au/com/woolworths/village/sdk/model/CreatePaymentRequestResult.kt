package au.com.woolworths.village.sdk.model

/**
 * The result from creating a new Payment Request.
 */
interface CreatePaymentRequestResult {
    /** The ID of the new Payment Request */
    val paymentRequestId: String

    /** A [QRCode] that is associated to the Payment Request */
    val qr: QRCode?
}