package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime

interface QRCode {
    val qrId: String

    val referenceId: String

    val referenceType: QRCodePaymentReferenceType

    val content: String

    val image: String

    val expiryTime: OffsetDateTime?
}