package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.ISODateSerializer
import au.com.woolworths.village.sdk.Wallet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

/**
 * All the possible [PaymentInstrument]s that a customer might have in a wallet.
 */
@Serializable
data class WalletContents(
    override val creditCards: List<CreditCard>,
    override val giftCards: List<GiftCard>,

    /**
     * A list of payment instruments stored in the customers EverydayPay wallet
     *
     * @returns `null` if the wallet is not the customers EverydayPay wallet.
     */
    val everydayPay: EverydayPayPaymentInstruments? = null
) : PaymentInstruments

@Serializable
data class EverydayPayPaymentInstruments(
    override val creditCards: List<CreditCard>,
    override val giftCards: List<GiftCard>
) : PaymentInstruments

/**
 * List of grouped payment instruments.
 */
interface PaymentInstruments : ModelType {
    /** List of added credit cards */
    val creditCards: List<CreditCard>

    /** List of added gift cards */
    val giftCards: List<GiftCard>
}

/**
 * Common properties to all [PaymentInstruments]
 */
@Suppress("EXPERIMENTAL_API_USAGE")
interface PaymentInstrument : ModelType {
    /**
     * Verification state for a [PaymentInstrument]
     */
    @Serializable
    enum class InstrumentStatus {
        @SerialName("UNVERIFIED_PERSISTENT")
        @JsonNames("unverified_persistent")
        UNVERIFIED_PERSISTENT,

        @SerialName("VERIFIED")
        @JsonNames("verified")
        VERIFIED
    }

    /** The payment instrument id. */
    val paymentInstrumentId: String

    /** Indicates if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean

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

interface CardPaymentInstrument : PaymentInstrument {
    /** The suffix (last 4 digits) of the card number. */
    val cardSuffix: String
}

/**
 * An added credit card
 */
@Serializable
data class CreditCard(
    override val paymentInstrumentId: String,
    override val allowed: Boolean,

    @Serializable(with = ISODateSerializer::class)
    override val lastUpdated: OffsetDateTime,

    @Serializable(with = ISODateSerializer::class)
    override val lastUsed: OffsetDateTime? = null,
    override val paymentToken: String,
    override val primary: Boolean,
    override val status: PaymentInstrument.InstrumentStatus,
    override val cardSuffix: String,

    /** The nickname of the credit card instrument in the container. */
    val cardName: String,

    /** Indicates if the CVV of the credit card has been validated. */
    val cvvValidated: Boolean,

    /** Indicates if the credit card is expired. */
    val expired: Boolean,

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String,

    /** The year of the expiry date of the credit card. */
    val expiryYear: String,

    /** Indicates if payments with this credit card requires a CVV check. */
    val requiresCVV: Boolean,

    /** The credit card scheme. */
    val scheme: String,

    /** The URL of an iframe. This iframe is used to capture a credit card expiry and CVV. */
    val updateURL: String,

    /** Whether a [ChallengeResponse] is required to make a payment with this card */
    val stepUp: CreditCardStepUp,
) : CardPaymentInstrument

/**
 * An added gift card.
 */
@Serializable
data class GiftCard(
    override val paymentInstrumentId: String,
    override val allowed: Boolean,

    @Serializable(with = ISODateSerializer::class)
    override val lastUpdated: OffsetDateTime,

    @Serializable(with = ISODateSerializer::class)
    override val lastUsed: OffsetDateTime? = null,
    override val paymentToken: String,
    override val primary: Boolean,
    override val status: PaymentInstrument.InstrumentStatus,
    override val cardSuffix: String,

    /** The gift card program name. */
    val programName: String,

    /** Whether a [ChallengeResponse] is required to make a payment with this card */
    val stepUp: GiftCardStepUp? = null,
) : CardPaymentInstrument

/**
 * Details of what step up is required to use a [CreditCard]
 */
@Serializable
data class CreditCardStepUp(
    /** This will be CAPTURE_CVV which identifies that the consumer must capture the CVV prior to payment. */
    val type: String,

    /** Indicates if this step up is mandatory. */
    val mandatory: Boolean,

    /** The URL of an iframe. This iframe is used to capture a credit card expiry and CVV or CVV only. */
    val url: String
) : ModelType

/**
 * Details of what step up is required to use a [GiftCard]
 */
@Serializable
data class GiftCardStepUp(
    /** This will be REQUIRE_PASSCODE which identifies that the consumer must capture the PIN prior to payment. */
    val type: String,

    /** Indicates if this step up is mandatory. */
    val mandatory: Boolean
) : ModelType

/**
 * Used to identify other [PaymentInstrument]s to be used as part of a payment.
 */
@Serializable
data class SecondaryPaymentInstrument(
    /** The ID of the payment instrument */
    val paymentInstrumentId: String,

    /** The amount of the payment to be paid using this instrument. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal
) : ModelType

@Serializable
data class IndividualPaymentInstrument(
    override val paymentInstrumentId: String,
    override val allowed: Boolean,

    @Serializable(with = ISODateSerializer::class)
    override val lastUpdated: OffsetDateTime,

    @Serializable(with = ISODateSerializer::class)
    override val lastUsed: OffsetDateTime? = null,

    override val paymentToken: String,
    override val primary: Boolean,
    override val status: PaymentInstrument.InstrumentStatus,

    /** The type of the payment instrument. */
    val paymentInstrumentType: String,

    val paymentInstrumentDetail: InstrumentDetail,

    /** An encrypted JSON object containing sensitive data */
    val cipherText: String? = null,
) : PaymentInstrument {
    @Serializable
    data class InstrumentDetail(
        /** The gift card program name. */
        val programName: String,

        /** What [ChallengeResponse] is required to make a payment with this instrument */
        val stepUp: GiftCardStepUp
    )
}

/**
 * Initiate the addition of a new payment instrument for this customer.
 *
 * This API returns a URL to be used to access the DigiPay IFrame based interface to request the customer to enter a payment instrument details.
 */
@Serializable
data class PaymentInstrumentAddition(
    /** The unique reference for this interaction as defined by the client application */
    val clientReference: String,

    /** Which wallet to store the new instrument in */
    val wallet: Wallet
) : ModelType

/**
 * The result of trying to initiate the addition of a new [PaymentInstrument]
 */
@Serializable
data class PaymentInstrumentAdditionResult(
    /** The URL of an iframe. This iframe is used to capture a credit card number, expiry and CVV */
    val cardCaptureURL: String,

    /** Container reference in the transaction logs. This number uniquely identifies the transaction in the container */
    val transactionRef: String? = null
) : ModelType