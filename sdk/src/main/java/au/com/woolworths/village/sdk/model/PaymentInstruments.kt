package au.com.woolworths.village.sdk.model

import java.io.Serializable

interface PaymentInstruments: Serializable {
    fun creditCards(): List<CreditCard>
    fun giftCards(): List<GiftCard>
}

interface PaymentInstrument: Serializable {
    fun paymentInstrumentId(): String
    fun cardSuffix(): String
}

// TODO: Flesh out to meet API spec.
interface CreditCard: PaymentInstrument {

}

// TODO: Flesh out to meet API spec.
interface GiftCard: PaymentInstrument {

}