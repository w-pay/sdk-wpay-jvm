package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime
import java.io.Serializable
import java.net.URL

interface PaymentInstruments: Serializable {
    fun creditCards(): List<CreditCard>
    fun giftCards(): List<GiftCard>
}

interface PaymentInstrument: Serializable {
    enum class InstrumentStatus {
        UNVERIFIED_PERSISTENT,
        VERIFIED
    }

    fun allowed(): Boolean
    fun cardSuffix(): String
    fun lastUpdated(): OffsetDateTime
    fun lastUsed(): OffsetDateTime
    fun paymentInstrumentId(): String
    fun paymentToken(): String
    fun primary(): Boolean
    fun status(): InstrumentStatus
    fun stepUp(): PaymentInstrumentStepUp?
}

interface CreditCard: PaymentInstrument {
    fun cardName(): String
    fun cvvValidated(): Boolean
    fun expired(): Boolean
    fun expiryMonth(): String
    fun expiryYear(): String
    fun requiresCVV(): Boolean
    fun scheme(): String
    fun updateURL(): URL

    // setup is mandatory for credit cards
    override fun stepUp(): PaymentInstrumentStepUp
}

interface GiftCard: PaymentInstrument {
    fun programName(): String
}

interface PaymentInstrumentStepUp {
    fun type(): String
    fun mandatory(): Boolean
    fun url(): URL
}