package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface PaymentInstrumentAdditionResult: Serializable {
    fun cardCaptureURL(): String

    fun transactionRef(): String?
}