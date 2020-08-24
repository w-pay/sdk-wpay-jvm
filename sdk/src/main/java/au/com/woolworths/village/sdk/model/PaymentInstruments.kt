package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.Wallet
import org.threeten.bp.OffsetDateTime
import java.io.Serializable
import java.math.BigDecimal
import java.net.URL

interface AllPaymentInstruments: PaymentInstruments {
    fun everydayPay(): PaymentInstruments?
}

interface PaymentInstruments: Serializable {
    fun creditCards(): List<CreditCard>
    fun giftCards(): List<GiftCard>
}

interface PaymentInstrumentIdentifier: Serializable {
    fun paymentInstrumentId(): String

    /** what Wallet the instrument is from */
    fun wallet(): Wallet
}

interface PaymentInstrument: PaymentInstrumentIdentifier {
    enum class InstrumentStatus {
        UNVERIFIED_PERSISTENT,
        VERIFIED
    }

    fun allowed(): Boolean
    fun cardSuffix(): String
    fun lastUpdated(): OffsetDateTime
    fun lastUsed(): OffsetDateTime?
    fun paymentToken(): String
    fun primary(): Boolean
    fun status(): InstrumentStatus
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
    fun stepUp(): CreditCardStepUp
}

interface GiftCard: PaymentInstrument {
    fun programName(): String
    fun stepUp(): GiftCardStepUp?
}

interface CreditCardStepUp {
    fun type(): String
    fun mandatory(): Boolean
    fun url(): URL
}

interface GiftCardStepUp {
    fun type(): String
    fun mandatory(): Boolean
}

interface SecondaryPaymentInstrument {
    fun paymentInstrumentId(): String

    fun amount(): BigDecimal
}
