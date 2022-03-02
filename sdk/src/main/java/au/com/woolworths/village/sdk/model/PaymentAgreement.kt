package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.ISODateSerializer
import au.com.woolworths.village.sdk.model.walletmanagement.PaymentInstrumentStatus
import org.threeten.bp.OffsetDateTime
import java.io.Serializable
import java.math.BigDecimal

/**
 * Properties of a Payment Agreement
 *
 * @category Model
 */
@kotlinx.serialization.Serializable
data class PaymentAgreement(
    /** The payment token of the payment agreement. The payment token is a unique identifier for the payment agreement. */
    val paymentToken: String,

    /** The status of the payment agreement in the container. */
    val status: PaymentInstrumentStatus,

    /** The timestamp the payment agreement was last updated in the container. The timestamp format is ISO8601. */
    @kotlinx.serialization.Serializable(with = ISODateSerializer::class)
    val lastUpdated: OffsetDateTime?,

    /** The timestamp the payment agreement was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    @kotlinx.serialization.Serializable(with = ISODateSerializer::class)
    val lastUsed: OffsetDateTime?,

    /** The timestamp for when the payment instrument was added. The timestamp format is ISO8601. */
    @kotlinx.serialization.Serializable(with = ISODateSerializer::class)
    val createdOn: OffsetDateTime?,

    /** A flag to indicate if this payment instrument is the primary instrument in the container. Not used for payment agreements. */
    val primary: Boolean?,

    /** A flag to indicate if the merchant profile in the container allows the use of this payment agreement. */
    val allowed: Boolean?,

    /** The payment agreement type. */
    val type: PaymentAgreementType,

    /** The payment agreement payment instrument id that will be used for the charges. */
    val paymentInstrumentId: String,

    /** The credit card scheme. */
    val scheme: String?,

    /** The suffix (last 4 digits) of the credit card number. */
    val cardSuffix: String?,

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String?,

    /** The year of the expiry date of the credit card. */
    val expiryYear: String?,

    /** The payment agreement start date and time. The timestamp format is ISO8601. */
    @kotlinx.serialization.Serializable(with = ISODateSerializer::class)
    val startDate: OffsetDateTime?,

    /** The payment agreement end date and time. The timestamp format is ISO8601. */
    @kotlinx.serialization.Serializable(with = ISODateSerializer::class)
    val endDate: OffsetDateTime?,

    /** The payment agreement charge frequency. */
    val chargeFrequency: PaymentAgreementChargeFrequency,

    /** The amount that will be charged at the frequency specified in the payment agreement. */
    @kotlinx.serialization.Serializable(with = CurrencySerializer::class)
    val chargeAmount: BigDecimal,

    /** The current charge cycle number. */
    val chargeCycle: Int,

    /** A flag to indicate if the payment agreement is expired. */
    val expired: Boolean?,

    /** The URL of the endpoint to use to update the payment agreement. */
    val updateURL: String,

    val stepUp: PaymentAgreementStepUp?,

    /** A description of the payment agreement */
    val description: String?
) : Serializable {
    constructor(
        paymentToken: String,
        status: PaymentInstrumentStatus,
        type: PaymentAgreementType,
        paymentInstrumentId: String,
        chargeFrequency: PaymentAgreementChargeFrequency,
        chargeAmount: BigDecimal,
    ) : this(
        paymentToken = paymentToken,
        status = status,
        lastUpdated = null,
        lastUsed = null,
        createdOn = null,
        primary = null,
        allowed = null,
        type = type,
        paymentInstrumentId = paymentInstrumentId,
        scheme = null,
        cardSuffix = null,
        expiryMonth = null,
        expiryYear = null,
        startDate = null,
        endDate = null,
        chargeFrequency = chargeFrequency,
        chargeAmount = chargeAmount,
        chargeCycle = 0,
        expired = null,
        updateURL = "",
        stepUp = null,
        description = null
    )
}

@kotlinx.serialization.Serializable
data class PaymentAgreementStepUp(
    /* The type of the step up action. For credit cards this will be CAPTURE_CVV which identifies that the consumer must capture the CVV prior to payment. */
    val type: String,

    /* A flag to indicate if this step up (action) is mandatory. */
    val mandatory: Boolean?,

    /* The URL of an iframe. This iframe is used to capture a credit card expiry and CVV or CVV only. The URL will automatically switch between expiry and CVV or CVV only endpoints based on the container requirement. */
    val url: String,
) : Serializable

/**
 * List of payments agreements.
 */
@kotlinx.serialization.Serializable
data class PaymentAgreements(
    /** The resulting list of payment agreements. */
    val paymentAgreements: List<PaymentAgreement>
) : Serializable

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
