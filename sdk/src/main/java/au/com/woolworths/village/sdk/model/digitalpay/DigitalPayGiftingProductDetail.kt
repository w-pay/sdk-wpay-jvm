package au.com.woolworths.village.sdk.model.digitalpay

/**
 * Gift card product detailed data
 */
interface DigitalPayGiftingProductDetail : DigitalPayGiftingProduct {
    enum class RedemptionType {
        INSTORE, ONLINE, BOTH
    }

    enum class Availability {
        DIGITAL, PHYSICAL, BOTH
    }

    /** Display instruction on where and how to redeem the product */
    val redemptionInstructions: String?

    /** The enabled redemption channels */
    val redemptionType: RedemptionType

    /** Terms and conditions text for the gift card product */
    val termsAndConditions: String?

    /** Minimum AUD value gift card able to be purchased for this product */
    val minValue: Int

    /** Maximum AUD value gift card able to be purchased for this product */
    val maxValue: Int

    /** Expiry period for the gift card product once purchased */
    val expiryPeriodInDays: Int?

    /** Display text for the expiry period */
    val expiryPeriodText: String?

    /** Returns true if the gift card product is active, else false. Only active programs are orderable */
    val isActive: Boolean

    /** The stores in which the gift card product is able to be redeemed */
    val redemptionStores: List<String>?

    /** Digital or phyiscal availability of the gift card */
    val availability: Availability

    /** Array of all alternative designs for the gift card product */
    val designs: List<GiftingProductDesign>
}