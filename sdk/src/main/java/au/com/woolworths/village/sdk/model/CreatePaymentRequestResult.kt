package au.com.woolworths.village.sdk.model

import kotlinx.serialization.Serializable

/**
 * The result from creating a new Payment Request.
 */
@Serializable
data class CreatePaymentRequestResult(
    /** The ID of the new Payment Request */
    val paymentRequestId: String,

    /** A [QRCode] that is associated to the Payment Request */
    val qr: QRCode? = null
) : ModelType