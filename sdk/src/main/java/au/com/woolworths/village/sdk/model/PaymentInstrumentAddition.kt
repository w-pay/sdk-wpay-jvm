package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface PaymentInstrumentAddition: Serializable {
    fun clientReference(): String
}