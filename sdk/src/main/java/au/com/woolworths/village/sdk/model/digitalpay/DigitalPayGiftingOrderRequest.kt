package au.com.woolworths.village.sdk.model.digitalpay

import java.math.BigDecimal

/**
 * Request payload containing details of the order
 */
interface DigitalPayGiftingOrderRequest {
    /** The instrumentId to be used for the order. Must not be a stored gift card */
    val instrumentId: String

    /** Email of the ordering customer */
    val deliveryEmail: String

    /** Unique reference for the order supplied by the client */
    val referenceId: String

    /** Face value of the gift card */
    val subTotalAmount: BigDecimal

    /** Eligible discount amount. In case of no discounts, value will be 0 */
    val discountAmount: BigDecimal

    /** Net amount payable */
    val totalOrderAmount: BigDecimal

    /** Billing address for the order */
    val billingContact: GiftingBillingContact

    /** Gift cards to be included in the order.  Currently only supports a single entry.  Array is for future roadmap */
    val orderItems: List<GiftingProductOrderItem>
}

interface GiftingBillingContact {
    /** The customer's first name. */
    val firstName: String

    /** The customer's last name. */
    val lastName: String

    /** The email of the ordering customer. */
    val email: String

    /** The mobile number of the ordering customer */
    val mobileNumber: String

    /** The customer's street address line. */
    val streetAddress: String

    /** The customer's extended address line. */
    val extendedAddress: String?

    /** The customer's suburb. */
    val suburb: String

    /** The customer's abbreviated state or territory. */
    val stateOrTerritory: String

    /** The customer's postal code. */
    val postalCode: String

    /** The customer's Alpha-2 (2-character) ISO-3166-1 country code. */
    val countryCode: String
}
