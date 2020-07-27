package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.Wallet
import java.io.Serializable

interface PaymentInstrumentAddition: Serializable {
    fun clientReference(): String

    fun wallet(): Wallet
}