package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Common properties of the [PaymentAgreement] request
 */
@kotlinx.serialization.Serializable
sealed class CommonPaymentAgreementRequest : Serializable {
    /** A merchant application specific reference number for the transaction. */
    abstract val clientReference: String

    /** A merchant application specific reference number for the customer. */
    abstract val customerRef: String?

    /** Merchant order number of the transaction. */
    abstract val orderNumber: String?

    /** Description of the payment agreement. Used to distinguish payment agreements from one another. */
    abstract val description: String?
}

/**
 * Request containing the details of the [PaymentAgreement] to create
 */
@kotlinx.serialization.Serializable
class CreatePaymentAgreementRequest(
    override val clientReference: String,
    override val customerRef: String?,
    override val orderNumber: String?,
    override val description: String?,

    /** Billing address for the customer. */
    val billingAddress: PaymentAgreementBillingAddress,

    /** Details of the payment agreement */
    val paymentAgreement: PaymentAgreement,
) : CommonPaymentAgreementRequest()

/**
 * Request containing the details of the [PaymentAgreement] to update
 */
@kotlinx.serialization.Serializable
class UpdatePaymentAgreementRequest(
    override val clientReference: String,
    override val customerRef: String?,
    override val orderNumber: String?,
    override val description: String?,

    /** Billing address for the customer. */
    val billingAddress: PaymentAgreementBillingAddress?,

    /** Details of the payment agreement */
    val paymentAgreement: PaymentAgreement?,

) : CommonPaymentAgreementRequest()

/**
 * The customer's billing address
 */
@kotlinx.serialization.Serializable
class PaymentAgreementBillingAddress(
    /** The customer's first name. */
    val firstName: String,

    /** The customer's last name. */
    val lastName: String,

    /** The customer’s email address. */
    val email: String,

    /** The customer's company name. */
    val company: String?,

    /** The customer's extended address line. */
    val extendedAddress: String?,

    /** The customer's street address line. */
    val streetAddress: String,

    /** The customer's suburb. */
    val suburb: String,

    /** The customer's abbreviated state or territory. */
    val stateOrTerritory: String,

    /** The customer's postal code. */
    val postalCode: String,

    /** The customer's Alpha-2 (2-character) ISO-3166-1 country code. */
    val countryCode: String
) : Serializable
