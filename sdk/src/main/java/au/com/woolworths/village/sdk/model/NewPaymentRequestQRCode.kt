package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface NewPaymentRequestQRCode: Serializable {
    fun referenceId(): String

    fun referenceType(): QRCodePaymentReferenceType

    fun timeToLive(): Int {
        return 0
    }
}