package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The JSON request structure of the GiftcardsApi Balance endpoint.
 *
 * @category Model
 */
interface GiftcardsBalanceRequest : Serializable {
    /* Use this array if the endpoint is being called with "cardNumber" and "pinCode" request properties. */
    val giftCards: List<GiftCardBalenceGiftCard>

    /* Use this array if the endpoint is being called with "paymentInstrumentId" request properties. */
    val giftCardInstruments: List<GiftCardInstrument>
}

interface GiftCardBalenceGiftCard : Serializable {
    /* The gift card number. */
    val cardNumber: String

    /* The gift card pin code. */
    val pinCode: String
}

interface GiftCardInstrument : Serializable {
    /* The gift card payment instrument id. */
    val paymentInstrumentId: String
}
