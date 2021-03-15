package au.com.woolworths.village.sdk.model

import java.math.BigDecimal

/**
 * The JSON response structure of the Tokenize Giftcard endpoint.
 *
 * @category Model
 */
interface TokenizeGiftcardResponse {
    /* The current available balance of the gift card. */
    val giftCard: TokenizedGiftCard

    val balance: BigDecimal

    /* The day of the expiry date of the gift card. */
    val expiryDay: String

    /* The month of the expiry date of the gift card. */
    val expiryMonth: String

    /* The year of the expiry date of the gift card. */
    val expiryYear: String

    /* A flag to indicate if the gift card is expired. */
    val expired: Boolean?
}

interface TokenizedGiftCard {
    /* The new payment instrument id to be used for payments. */
    val paymentInstrumentId: String

    /* The status of the payment instrument in the container. */
    val status: Status

    /* The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String

    /* The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String

    /* A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean?

    /* A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean?

    /* The gift card program name. */
    val programName: String

    /* The suffix (last 4 digits) of the gift card number. */
    val cardSuffix: String
}

enum class Status {
    UNVERIFIED_PERSISTENT,
    VERIFIED
}