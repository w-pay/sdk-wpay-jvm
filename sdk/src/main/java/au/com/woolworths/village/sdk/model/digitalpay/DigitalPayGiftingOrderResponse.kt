package au.com.woolworths.village.sdk.model.digitalpay

/**
 * Results of the gifting order
 */
interface DigitalPayGiftingOrderResponse {
    /** Current order status */
    val status: String

    /** Order reference */
    val orderId: String

    /** Quote reference */
    val quoteNo: String
}