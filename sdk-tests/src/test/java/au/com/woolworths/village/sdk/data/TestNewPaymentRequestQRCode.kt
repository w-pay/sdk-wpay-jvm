package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.NewPaymentRequestQRCode
import au.com.woolworths.village.sdk.model.QRCodePaymentReferenceType

fun aNewPaymentRequestQRCode(): NewPaymentRequestQRCode {
    return TestNewPaymentRequestQRCode()
}

class TestNewPaymentRequestQRCode: NewPaymentRequestQRCode {
    override fun referenceId(): String {
        return "abc123def"
    }

    override fun referenceType(): QRCodePaymentReferenceType {
        return QRCodePaymentReferenceType.PAYMENT_SESSION
    }
}