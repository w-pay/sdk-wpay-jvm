package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.QRCode
import au.com.woolworths.village.sdk.model.QRCodePaymentReferenceType
import au.com.woolworths.village.sdk.openapi.dto.Qr
import org.threeten.bp.OffsetDateTime

class OpenApiQRCode(
    private val code: Qr
): QRCode {
    override fun qrId(): String {
        return code.qrId
    }

    override fun referenceId(): String {
        return code.referenceId
    }

    override fun referenceType(): QRCodePaymentReferenceType {
        return QRCodePaymentReferenceType.valueOf(code.referenceType.value)
    }

    override fun content(): String {
        return code.content
    }

    override fun image(): String {
        return code.image
    }

    override fun expiryTime(): OffsetDateTime? {
        return code.expiryTime
    }
}