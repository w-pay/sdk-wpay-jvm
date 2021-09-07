package au.com.woolworths.village.sdk.model.digitalpay

/**
 * Request payload containing details of the order to quote on
 */
interface DigitalPayGiftingQuoteRequest {
    /** Gift cards to be included in the order. */
    val orderItems: List<GiftingProductOrderItem>
}