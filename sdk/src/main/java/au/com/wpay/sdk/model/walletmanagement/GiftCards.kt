package au.com.wpay.sdk.model.walletmanagement

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the GiftCardsApi Balance endpoint.
 */
@Serializable
data class GiftCardsBalanceRequest(
    /** Use this array if the endpoint is being called with "cardNumber" and "pinCode" request properties. */
    val giftCards: List<GiftCardBalanceGiftCard>,

    /** Use this array if the endpoint is being called with "paymentInstrumentId" request properties. */
    val giftCardInstruments: List<GiftCardInstrument>
) : ModelType

@Serializable
data class GiftCardBalanceGiftCard(
    /** The gift card number. */
    val cardNumber: String,

    /** The gift card pin code. */
    val pinCode: String
) : ModelType

@Serializable
data class GiftCardInstrument(
    /** The gift card payment instrument id. */
    val paymentInstrumentId: String
) : ModelType

/**
 * The JSON response structure of the GiftCardsApi Balance endpoint.
 */
@Serializable
data class GiftCardsBalanceResponse(
    val giftCardBalances: List<GiftCardBalance>
) : ModelType

@Serializable
data class GiftCardBalance(
    /** The gift card number. This property will only be returned if the endpoint was called with the "cardNumber" and "pinCode" request properties. */
    val cardNumber: String,

    /** The gift card payment instrument id. This property will only be returned if the endpoint was called with the "paymentInstrumentId" request property. */
    val paymentInstrumentId: String,

    /** The current available balance of the gift card. */
    @Serializable(with = CurrencySerializer::class)
    val balance: BigDecimal,

    /**  The day of the expiry date of the gift card. */
    val expiryDay: String,

    /** The month of the expiry date of the gift card. */
    val expiryMonth: String,

    /** The year of the expiry date of the gift card. */
    val expiryYear: String,

    /** A flag to indicate if the gift card is expired. */
    val expired: Boolean? = null
) : ModelType

@Serializable
data class TokenizeGiftCardRequest(
    /** The gift card number. */
    val cardNumber: String,

    /** The gift card pin code. */
    val pinCode: String,

    /** A flag to indicate if this payment instrument has to be set as the primary instrument. */
    val primary: Boolean? = null,

    /** A flag to indicate if this payment instrument has to be saved in the container or tokenized for one-off use. */
    val save: Boolean? = null
) : ModelType

@Serializable
data class TokenizeGiftCardResponse(
    /** The current available balance of the gift card. */
    val giftCard: TokenizedGiftCard,

    @Serializable(with = CurrencySerializer::class)
    val balance: BigDecimal,

    /** The day of the expiry date of the gift card. */
    val expiryDay: String,

    /** The month of the expiry date of the gift card. */
    val expiryMonth: String,

    /** The year of the expiry date of the gift card. */
    val expiryYear: String,

    /** A flag to indicate if the gift card is expired. */
    val expired: Boolean? = null
) : ModelType

@Serializable
data class TokenizedGiftCard(
    /** The new payment instrument id to be used for payments. */
    val paymentInstrumentId: String,

    /** The status of the payment instrument in the container. */
    val status: Status,

    /** The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String,

    /** The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String? = null,

    /** A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean? = null,

    /** A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean? = null,

    /** The gift card program name. */
    val programName: String,

    /** The suffix (last 4 digits) of the gift card number. */
    val cardSuffix: String
) : ModelType

enum class Status {
    UNVERIFIED_PERSISTENT,
    VERIFIED
}
