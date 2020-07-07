package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime

interface QRCode {
    fun qrId(): String

    fun referenceId(): String

    fun referenceType(): QRCodePaymentReferenceType

    fun content(): String

    fun image(): String

    fun expiryTime(): OffsetDateTime?
}