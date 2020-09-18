package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime

/**
 * Detail of a QR code
 */
interface QRCode {
    /** The ID of the QR code */
    val qrId: String

    /** The ID of the Payment Request linked to this QR code */
    val referenceId: String

    /** "The type of ID held in [QRCode.referenceId] */
    val referenceType: QRCodePaymentReferenceType

    /** The text content for the QR code. */
    val content: String

    /** Base64 encoded PNG of the QR Code */
    val image: String

    /**
     * Timestamp indicating when the QR code will expire and become ineffective.
     *
     * If absent then the QR code will not expire until it is deleted
     */
    val expiryTime: OffsetDateTime?
}