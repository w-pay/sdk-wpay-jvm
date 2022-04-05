package au.com.wpay.sdk.model

import kotlinx.serialization.Serializable

/**
 * The result of creating a [PaymentSession]
 */
@Serializable
data class CreatePaymentSessionResult(
    /** The ID of the new [PaymentSession] */
    val paymentSessionId: String,

    /** A [QRCode] that is associated to the [PaymentSession] */
    val qr: QRCode? = null
) : ModelType
