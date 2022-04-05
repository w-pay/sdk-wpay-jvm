package au.com.wpay.sdk.model.digitalpay

import au.com.wpay.sdk.ISODateSerializer
import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable
import org.threeten.bp.OffsetDateTime

/**
 * Gift card product summary data
 */
interface DigitalPayGiftingProductType : ModelType {
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

/**
 * Gift card product summary data
 */
@Serializable
data class DigitalPayGiftingProduct(
    override val productId: String,
    override val name: String,
    override val barCodeType: DigitalPayGiftingProductType.BarCodeType,

    @Serializable(with = ISODateSerializer::class)
    override val lastUpdateDateTime: OffsetDateTime,
    override val defaultDesign: GiftingProductDesign,
    override val discountOffered: GiftingProductDiscount? = null
) : DigitalPayGiftingProductType

@Serializable
data class GiftingProductDesign(
    /** Unique (within the scope of designType) identifier of the aesthectic design of the gift card */
    val designId: String,

    /** Format of the design (note that different design types have different unique IDs) */
    val designType: DesignType,

    /** URL to the image for the gift card design */
    val imageUrl: String
) : ModelType {
    enum class DesignType {
        DIGITAL, PHYSICAL
    }
}

@Serializable
data class GiftingProductDiscount(
    /** Unique identifier of the discount */
    val discountId: String,

    /** Display description of the discount */
    val description: String,

    /** Percentage discount offered on the gift card */
    val percentageDiscount: Int,

    /** The start date of the offered discount. */
    @Serializable(with = ISODateSerializer::class)
    val startDate: OffsetDateTime,

    /** The end date of the offered discount. */
    @Serializable(with = ISODateSerializer::class)
    val endDate: OffsetDateTime
) : ModelType

@Serializable
data class DigitalPayGiftingProducts(
    val products: List<DigitalPayGiftingProduct>
) : ModelType

/**
 * Gift card product detailed data
 */
@Serializable
data class DigitalPayGiftingProductDetail(
    override val productId: String,
    override val name: String,
    override val barCodeType: DigitalPayGiftingProductType.BarCodeType,

    @Serializable(with = ISODateSerializer::class)
    override val lastUpdateDateTime: OffsetDateTime,
    override val defaultDesign: GiftingProductDesign,
    override val discountOffered: GiftingProductDiscount? = null,

    /** Display instruction on where and how to redeem the product */
    val redemptionInstructions: String? = null,

    /** The enabled redemption channels */
    val redemptionType: RedemptionType,

    /** Terms and conditions text for the gift card product */
    val termsAndConditions: String? = null,

    /** Minimum AUD value gift card able to be purchased for this product */
    val minValue: Int,

    /** Maximum AUD value gift card able to be purchased for this product */
    val maxValue: Int,

    /** Expiry period for the gift card product once purchased */
    val expiryPeriodInDays: Int? = null,

    /** Display text for the expiry period */
    val expiryPeriodText: String? = null,

    /** Returns true if the gift card product is active, else false. Only active programs are orderable */
    val isActive: Boolean,

    /** The stores in which the gift card product is able to be redeemed */
    val redemptionStores: List<String>? = null,

    /** Digital or phyiscal availability of the gift card */
    val availability: Availability,

    /** Array of all alternative designs for the gift card product */
    val designs: List<GiftingProductDesign>
) : DigitalPayGiftingProductType {
    enum class RedemptionType {
        INSTORE, ONLINE, BOTH
    }

    enum class Availability {
        DIGITAL, PHYSICAL, BOTH
    }
}
