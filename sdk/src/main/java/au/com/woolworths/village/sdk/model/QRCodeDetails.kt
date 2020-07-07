package au.com.woolworths.village.sdk.model

interface QRCodeDetails {
    fun referenceId(): String

    fun referenceType(): QRCodePaymentReferenceType

    fun timeToLive(): Int? {
        return 0
    }
}