package au.com.woolworths.village.sdk.model

import kotlinx.serialization.Serializable

/**
 * Request to create a new [QRCode] for a Payment Request
 */
@Serializable
data class NewPaymentRequestQRCode(
    /** The ID of the payment request linked to this [QRCode] */
    val referenceId: String,

    /** The type of ID held in [NewPaymentRequestQRCode.referenceId] */
    val referenceType: QRCodePaymentReferenceType,

    /**
     * The time in seconds that the QR code should remain valid.
     *
     * After this time any use of the QR code by a customer will fail to return any data.
     *
     * If absent, the API will default value to 0 which indicates that the code will not expire until it is deleted.
     */
    val timeToLive: Int = 0
) : ModelType