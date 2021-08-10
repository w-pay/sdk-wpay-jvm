package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The result of creating a [PaymentSession]
 */
interface CreatePaymentSessionResult : Serializable {
    /** The ID of the new [PaymentSession] */
    val paymentSessionId: String

    /** A [QRCode] that is associated to the [PaymentSession] */
    val qr: QRCode?
}