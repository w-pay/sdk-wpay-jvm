package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface PaymentInstrumentAdditionResult: Serializable {
    val cardCaptureURL: String

    val transactionRef: String?
}