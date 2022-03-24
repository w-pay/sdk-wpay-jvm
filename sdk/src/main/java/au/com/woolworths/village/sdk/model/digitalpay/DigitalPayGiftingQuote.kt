package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * Request payload containing details of the order to quote on
 */
@Serializable
data class DigitalPayGiftingQuoteRequest(
    /** Gift cards to be included in the order. */
    val orderItems: List<GiftingProductOrderItem>
) : ModelType

/**
 * Results of the gifting quote
 */
@Serializable
data class DigitalPayGiftingQuoteResponse(
    /** Quote reference. Can be used as a reference when placing the actual order */
    val quoteId: String,

    /** Face value of the gift card */
    @Serializable(with = CurrencySerializer::class)
    val subTotalAmount: BigDecimal,

    /** Eligible discount amount. In case of no discounts, value will be 0 */
    @Serializable(with = CurrencySerializer::class)
    val discountAmount: BigDecimal,

    /** Net amount payable */
    @Serializable(with = CurrencySerializer::class)
    val totalOrderAmount: BigDecimal,

    /** Results of the gifting quote */
    val orderItems: GiftingProductQuoteResponseItem
) : ModelType

@Serializable
data class GiftingProductQuoteResponseItem(
    /** Unique identifier of the design selected (assumed to be DIGITAL only currently) */
    val designId: String,

    /** Face value of the gift card */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** Sale price of the gift card */
    @Serializable(with = CurrencySerializer::class)
    val unitPrice: BigDecimal,

    /** Total order price */
    @Serializable(with = CurrencySerializer::class)
    val totalPrice: BigDecimal,

    /** For self use card, this can be any value between 1 and 10. For a gifting card, it must be 1 */
    val quantity: Int,

    /** If true its a gifting card, if false it is a self use card */
    val isGifting: Boolean,

    /** Australian mobile number of recipient. Only SMS delivery method is supported for gifting card */
    val mobileNumber: String? = null
) : ModelType
