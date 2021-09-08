package au.com.woolworths.village.sdk.model.digitalpay

import java.time.OffsetDateTime

/**
 * Gift card product summary data
 */
interface DigitalPayGiftingProduct {
    enum class BarCodeType {
        PAN, GTIN
    }

    /** Unique identifier assigned to gift card program */
    val productId: String

    /** Display name of the gift card program */
    val name: String

    /** The manner in which the barcode is displayed for optical recognition (can be used to drive CX experience flows) */
    val barCodeType: BarCodeType

    /** The timestamp the gift card program was last updated. */
    val lastUpdateDateTime: OffsetDateTime

    /** The aesthectic design of a gift card product */
    val defaultDesign: GiftingProductDesign

    /** A discount offered for a gift card product */
    val discountOffered: GiftingProductDiscount?
}

interface GiftingProductDesign {
    enum class DesignType {
        DIGITAL, PHYSICAL
    }

    /** Unique (within the scope of designType) identifier of the aesthectic design of the gift card */
    val designId: String

    /** Format of the design (note that different design types have different unique IDs) */
    val designType: DesignType

    /** URL to the image for the gift card design */
    val imageUrl: String
}

interface GiftingProductDiscount {
    /** Unique identifier of the discount */
    val discountId: String

    /** Display description of the discount */
    val description: String

    /** Percentage discount offered on the gift card */
    val percentageDiscount: Int

    /** The start date of the offered discount. */
    val startDate: OffsetDateTime

    /** The end date of the offered discount. */
    val endDate: OffsetDateTime
}