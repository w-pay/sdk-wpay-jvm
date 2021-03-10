package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.Wallet
import org.threeten.bp.OffsetDateTime
import java.io.Serializable
import java.math.BigDecimal
import java.net.URL

/**
 * All the possible [PaymentInstrument]s that a customer might have in a wallet.
 */
interface WalletContents: PaymentInstruments {
    /**
     * A list of payment instruments stored in the customers EverydayPay wallet
     *
     * @returns `null` if the wallet is not the customers EverydayPay wallet.
     */
    val everydayPay: PaymentInstruments?
}

/**
 * List of grouped payment instruments.
 */
interface PaymentInstruments: Serializable {
    /** List of added credit cards */
    val creditCards: List<CreditCard>

    /** List of added gift cards */
    val giftCards: List<GiftCard>
}

/**
 * Used to identify a [PaymentInstrument] to the API
 */
interface PaymentInstrumentIdentifier: Serializable {
    /** The payment instrument id. */
    val paymentInstrumentId: String

    /** Which Wallet the instrument is in */
    val wallet: Wallet
}

/**
 * Common properties to all [PaymentInstruments]
 */
interface PaymentInstrument: PaymentInstrumentIdentifier {
    /**
     * Verification state for a [PaymentInstrument]
     */
    enum class InstrumentStatus {
        UNVERIFIED_PERSISTENT,
        VERIFIED
    }

    /** Indicates if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean

    /** The suffix (last 4 digits) of the card number. */
    val cardSuffix: String

    /** The timestamp the payment instrument was last updated in the container. */
    val lastUpdated: OffsetDateTime

    /** The timestamp the payment instrument was last used in the container. */
    val lastUsed: OffsetDateTime?

    /** The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String

    /** Indicates that this payment instrument is the primary instrument in the container. */
    val primary: Boolean

    /** The status of the payment instrument in the container. */
    val status: InstrumentStatus
}

/**
 * An added credit card
 */
interface CreditCard: PaymentInstrument {
    /** The nickname of the credit card instrument in the container. */
    val cardName: String

    /** Indicates if the CVV of the credit card has been validated. */
    val cvvValidated: Boolean

    /** Indicates if the credit card is expired. */
    val expired: Boolean

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String

    /** The year of the expiry date of the credit card. */
    val expiryYear: String

    /** Indicates if payments with this credit card requires a CVV check. */
    val requiresCVV: Boolean

    /** The credit card scheme. */
    val scheme: String

    /** The URL of an iframe. This iframe is used to capture a credit card expiry and CVV. */
    val updateURL: URL

    /** Whether a [ChallengeResponse] is required to make a payment with this card */
    val stepUp: CreditCardStepUp
}

/**
 * An added gift card.
 */
interface GiftCard: PaymentInstrument {
    /** The gift card program name. */
    val programName: String

    /** Whether a [ChallengeResponse] is required to make a payment with this card */
    val stepUp: GiftCardStepUp?
}

/**
 * Details of what step up is required to use a [CreditCard]
 */
interface CreditCardStepUp {
    /** This will be CAPTURE_CVV which identifies that the consumer must capture the CVV prior to payment. */
    val type: String

    /** Indicates if this step up is mandatory. */
    val mandatory: Boolean

    /** The URL of an iframe. This iframe is used to capture a credit card expiry and CVV or CVV only. */
    val url: URL
}

/**
 * Details of what step up is required to use a [GiftCard]
 */
interface GiftCardStepUp {
    /** This will be REQUIRE_PASSCODE which identifies that the consumer must capture the PIN prior to payment. */
    val type: String

    /** Indicates if this step up is mandatory. */
    val mandatory: Boolean
}

/**
 * Used to identify other [PaymentInstrument]s to be used as part of a payment.
 */
interface SecondaryPaymentInstrument {
    /** The ID of the payment instrument */
    val paymentInstrumentId: String

    /** The amount of the payment to be paid using this instrument. */
    val amount: BigDecimal
}
