package au.com.woolworths.village.sdk.model

/**
 * Common properties of the {@link PaymentAgreement} request
 *
 * @category Model
 */
interface CommonPaymentAgreementRequest {
    /** A merchant application specific reference number for the transaction. */
    val clientReference: String

    /** A merchant application specific reference number for the customer. */
    val customerRef: String?

    /** Merchant order number of the transaction. */
    val orderNumber: String?

    /** Description of the payment agreement. Used to distinguish payment agreements from one another. */
    val description: String?
}

/**
 * Request containing the details of the {@link PaymentAgreement} to create
 *
 * @category Model
 */
interface CreatePaymentAgreementRequest : CommonPaymentAgreementRequest {
    /** Billing address for the customer. */
    val billingAddress: PaymentAgreementBillingAddress

    /** Details of the payment agreement */
    val paymentAgreement: PaymentAgreement
}

/**
 * Request containing the details of the {@link PaymentAgreement} to update
 *
 * @category Model
 */
interface UpdatePaymentAgreementRequest : CommonPaymentAgreementRequest {
    /** Billing address for the customer. */
    val billingAddress: PaymentAgreementBillingAddress?

    /** Details of the payment agreement */
    val paymentAgreement: PaymentAgreement?
}

/**
 * The customer's billing address
 *
 * @category Model
 */
interface PaymentAgreementBillingAddress {
    /** The customer's first name. */
    val firstName: String

    /** The customer's last name. */
    val lastName: String

    /** The customer’s email address. */
    val email: String

    /** The customer's company name. */
    val company: String?

    /** The customer's extended address line. */
    val extendedAddress: String?

    /** The customer's street address line. */
    val streetAddress: String

    /** The customer's suburb. */
    val suburb: String

    /** The customer's abbreviated state or territory. */
    val stateOrTerritory: String

    /** The customer's postal code. */
    val postalCode: String

    /** The customer's Alpha-2 (2-character) ISO-3166-1 country code. */
    val countryCode: String
}
