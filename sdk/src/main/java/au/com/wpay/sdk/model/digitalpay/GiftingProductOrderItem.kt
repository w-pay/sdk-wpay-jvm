package au.com.wpay.sdk.model.digitalpay

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class GiftingProductOrderItem(
    /** Unique identifier of the design selected */
    val designId: String,

    /** Face value of the gift card */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** For self use card, this can be any value between 1 and 10. For a gifting card, it must be 1 */
    val quantity: Int,

    /** If true its a gifting card, if false it is a self use card */
    val isGifting: Boolean,

    /** For a gifting card, contains the recipient details */
    val recipientDetails: RecipientDetail? = null
) : ModelType

@Serializable
data class RecipientDetail(
    /** Name of sender */
    val toName: String,

    /** Name of recipient */
    val fromName: String,

    /** Optional message to be sent as part of gift card delivery */
    val message: String? = null,

    /** Optional image URL for personalisation purposes */
    val imageUrl: String? = null,

    /** Australian mobile number of recipient. Only SMS delivery method is supported for gifting card */
    val mobileNumber: String
) : ModelType
