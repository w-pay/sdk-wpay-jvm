package au.com.woolworths.village.sdk.model.digitalpay

import java.math.BigDecimal

/**
 * Results of the gifting quote
 */
interface DigitalPayGiftingQuoteResponse {
    /** Quote reference. Can be used as a reference when placing the actual order */
    val quoteId: String

    /** Face value of the gift card */
    val subTotalAmount: BigDecimal

    /** Eligible discount amount. In case of no discounts, value will be 0 */
    val discountAmount: BigDecimal

    /** Net amount payable */
    val totalOrderAmount: BigDecimal

    /** Results of the gifting quote */
    val orderItems: GiftingProductQuoteResponseItem
}

interface GiftingProductQuoteResponseItem {
    /** Unique identifier of the design selected (assumed to be DIGITAL only currently) */
    val designId: String

    /** Face value of the gift card */
    val amount: BigDecimal

    /** Sale price of the gift card */
    val unitPrice: BigDecimal

    /** Total order price */
    val totalPrice: BigDecimal

    /** For self use card, this can be any value between 1 and 10. For a gifting card, it must be 1 */
    val quantity: Int

    /** If true its a gifting card, if false it is a self use card */
    val isGifting: Boolean

    /** Australian mobile number of recipient. Only SMS delivery method is supported for gifting card */
    val mobileNumber: String?
}