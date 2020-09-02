package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.Wallet
import org.threeten.bp.OffsetDateTime
import java.io.Serializable
import java.math.BigDecimal
import java.net.URL

interface AllPaymentInstruments: PaymentInstruments {
    val everydayPay: PaymentInstruments?
}

interface PaymentInstruments: Serializable {
    val creditCards: List<CreditCard>
    val giftCards: List<GiftCard>
}

interface PaymentInstrumentIdentifier: Serializable {
    val paymentInstrumentId: String

    /** what Wallet the instrument is from */
    val wallet: Wallet
}

interface PaymentInstrument: PaymentInstrumentIdentifier {
    enum class InstrumentStatus {
        UNVERIFIED_PERSISTENT,
        VERIFIED
    }

    val allowed: Boolean
    val cardSuffix: String
    val lastUpdated: OffsetDateTime
    val lastUsed: OffsetDateTime?
    val paymentToken: String
    val primary: Boolean
    val status: InstrumentStatus
}

interface CreditCard: PaymentInstrument {
    val cardName: String
    val cvvValidated: Boolean
    val expired: Boolean
    val expiryMonth: String
    val expiryYear: String
    val requiresCVV: Boolean
    val scheme: String
    val updateURL: URL
    val stepUp: CreditCardStepUp
}

interface GiftCard: PaymentInstrument {
    val programName: String
    val stepUp: GiftCardStepUp?
}

interface CreditCardStepUp {
    val type: String
    val mandatory: Boolean
    val url: URL
}

interface GiftCardStepUp {
    val type: String
    val mandatory: Boolean
}

interface SecondaryPaymentInstrument {
    val paymentInstrumentId: String

    val amount: BigDecimal
}
