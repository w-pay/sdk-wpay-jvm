package au.com.woolworths.village.sdk.model.digitalpay

import java.math.BigDecimal

interface GiftingProductOrderItem {
    /** Unique identifier of the design selected */
    val designId: String

    /** Face value of the gift card */
    val amount: BigDecimal

    /** For self use card, this can be any value between 1 and 10. For a gifting card, it must be 1 */
    val quantity: Int

    /** If true its a gifting card, if false it is a self use card */
    val isGifting: Boolean

    /** For a gifting card, contains the recipient details */
    val recipientDetails: RecipientDetail?
}

interface RecipientDetail {
    /** Name of sender */
    val toName: String

    /** Name of recipient */
    val fromName: String

    /** Optional message to be sent as part of gift card delivery */
    val message: String?

    /** Optional image URL for personalisation purposes */
    val imageUrl: String?

    /** Australian mobile number of recipient. Only SMS delivery method is supported for gifting card */
    val mobileNumber: String
}