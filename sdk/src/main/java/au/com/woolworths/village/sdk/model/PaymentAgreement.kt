package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.ISODateSerializer
import au.com.woolworths.village.sdk.model.walletmanagement.PaymentInstrumentStatus
import kotlinx.serialization.Serializable
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

/**
 * Properties of a Payment Agreement
 *
 * @category Model
 */
@Serializable
data class PaymentAgreement(
    /** The payment token of the payment agreement. The payment token is a unique identifier for the payment agreement. */
    val paymentToken: String,

    /** The status of the payment agreement in the container. */
    val status: PaymentInstrumentStatus,

    /** The timestamp the payment agreement was last updated in the container. The timestamp format is ISO8601. */
    @Serializable(with = ISODateSerializer::class)
    val lastUpdated: OffsetDateTime? = null,

    /** The timestamp the payment agreement was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    @Serializable(with = ISODateSerializer::class)
    val lastUsed: OffsetDateTime? = null,

    /** The timestamp for when the payment instrument was added. The timestamp format is ISO8601. */
    @Serializable(with = ISODateSerializer::class)
    val createdOn: OffsetDateTime? = null,

    /** A flag to indicate if this payment instrument is the primary instrument in the container. Not used for payment agreements. */
    val primary: Boolean? = null,

    /** A flag to indicate if the merchant profile in the container allows the use of this payment agreement. */
    val allowed: Boolean? = null,

    /** The payment agreement type. */
    val type: PaymentAgreementType,

    /** The payment agreement payment instrument id that will be used for the charges. */
    val paymentInstrumentId: String,

    /** The credit card scheme. */
    val scheme: String? = null,

    /** The suffix (last 4 digits) of the credit card number. */
    val cardSuffix: String? = null,

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String? = null,

    /** The year of the expiry date of the credit card. */
    val expiryYear: String? = null,

    /** The payment agreement start date and time. The timestamp format is ISO8601. */
    @Serializable(with = ISODateSerializer::class)
    val startDate: OffsetDateTime? = null,

    /** The payment agreement end date and time. The timestamp format is ISO8601. */
    @Serializable(with = ISODateSerializer::class)
    val endDate: OffsetDateTime? = null,

    /** The payment agreement charge frequency. */
    val chargeFrequency: PaymentAgreementChargeFrequency,

    /** The amount that will be charged at the frequency specified in the payment agreement. */
    @Serializable(with = CurrencySerializer::class)
    val chargeAmount: BigDecimal,

    /** The current charge cycle number. */
    val chargeCycle: Int,

    /** A flag to indicate if the payment agreement is expired. */
    val expired: Boolean? = null,

    /** The URL of the endpoint to use to update the payment agreement. */
    val updateURL: String,

    val stepUp: PaymentAgreementStepUp? = null,

    /** A description of the payment agreement */
    val description: String? = null
) : ModelType

@Serializable
data class PaymentAgreementStepUp(
    /* The type of the step up action. For credit cards this will be CAPTURE_CVV which identifies that the consumer must capture the CVV prior to payment. */
    val type: String,

    /* A flag to indicate if this step up (action) is mandatory. */
    val mandatory: Boolean? = null,

    /* The URL of an iframe. This iframe is used to capture a credit card expiry and CVV or CVV only. The URL will automatically switch between expiry and CVV or CVV only endpoints based on the container requirement. */
    val url: String,
) : ModelType

/**
 * List of payments agreements.
 */
@Serializable
data class PaymentAgreements(
    /** The resulting list of payment agreements. */
    val paymentAgreements: List<PaymentAgreement>
) : ModelType

/**
 * Frequency with which the payment agreement is charged
 */
enum class PaymentAgreementChargeFrequency {
    WEEKLY,
    FORTNIGHTLY,
    MONTHLY
}

/**
 * Type of payment agreement
 */
enum class PaymentAgreementType {
    RECURRING,
    ADHOC,
    INSTALLMENT
}

/**
 * Common properties of the [PaymentAgreement] request
 */
interface CommonPaymentAgreementRequestType : ModelType {
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
 * Request containing the details of the [PaymentAgreement] to create
 */
@Serializable
data class CreatePaymentAgreementRequest(
    override val clientReference: String,
    override val customerRef: String? = null,
    override val orderNumber: String? = null,
    override val description: String? = null,

    /** Billing address for the customer. */
    val billingAddress: PaymentAgreementBillingAddress,

    /** Details of the payment agreement */
    val paymentAgreement: PaymentAgreement,
) : CommonPaymentAgreementRequestType

/**
 * Request containing the details of the [PaymentAgreement] to update
 */
@Serializable
data class UpdatePaymentAgreementRequest(
    override val clientReference: String,
    override val customerRef: String? = null,
    override val orderNumber: String? = null,
    override val description: String? = null,

    /** Billing address for the customer. */
    val billingAddress: PaymentAgreementBillingAddress? = null,

    /** Details of the payment agreement */
    val paymentAgreement: PaymentAgreement? = null
) : CommonPaymentAgreementRequestType

/**
 * The customer's billing address
 */
@Serializable
data class PaymentAgreementBillingAddress(
    /** The customer's first name. */
    val firstName: String,

    /** The customer's last name. */
    val lastName: String,

    /** The customer’s email address. */
    val email: String,

    /** The customer's company name. */
    val company: String? = null,

    /** The customer's extended address line. */
    val extendedAddress: String? = null,

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
) : ModelType

/**
 * Data required to charge against a [PaymentAgreement]
 */
@Serializable
data class ChargePaymentAgreementRequest(
    /**
     * The payment token of the payment agreement.
     *
     * The payment token is a unique identifier for the payment agreement.
     */
    val paymentToken: String,

    /** The amount that will be charged against the payment instrument linked to the payment agreement. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** A merchant application specific reference number for the transaction. */
    val clientReference: String,

    /** A merchant order number for the transaction. */
    val orderNumber: String,

    /** Transaction type containers to use for all instruments. */
    val transactionType: TransactionType,

    /** A merchant application specific reference number for the customer. */
    val customerRef: String? = null
) : ModelType