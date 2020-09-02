package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.Wallet
import java.io.Serializable

interface PaymentInstrumentAddition: Serializable {
    val clientReference: String

    val wallet: Wallet
}