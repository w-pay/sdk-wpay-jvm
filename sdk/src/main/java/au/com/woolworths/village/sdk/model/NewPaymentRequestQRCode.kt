package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface NewPaymentRequestQRCode: Serializable {
    val referenceId: String

    val referenceType: QRCodePaymentReferenceType

    val timeToLive: Int get() = 0
}