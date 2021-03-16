package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.model.walletmanagement.PaymentInstrumentStatus
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

/**
 * Properties of a Payment Agreement
 *
 * @category Model
 */
interface PaymentAgreement : Serializable {
    /** The payment token of the payment agreement. The payment token is a unique identifier for the payment agreement. */
    val paymentToken: String

    /** The status of the payment agreement in the container. */
    val status: PaymentInstrumentStatus

    /** The timestamp the payment agreement was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: Date?

    /** The timestamp the payment agreement was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: Date?

    /** The timestamp for when the payment instrument was added. The timestamp format is ISO8601. */
    val createdOn: Date?

    /** A flag to indicate if this payment instrument is the primary instrument in the container. Not used for payment agreements. */
    val primary: Boolean?

    /** A flag to indicate if the merchant profile in the container allows the use of this payment agreement. */
    val allowed: Boolean?

    /** The payment agreement type. */
    val type: PaymentAgreementType

    /** The payment agreement payment instrument id that will be used for the charges. */
    val paymentInstrumentId: String

    /** The credit card scheme. */
    val scheme: String?

    /** The suffix (last 4 digits) of the credit card number. */
    val cardSuffix: String?

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String?

    /** The year of the expiry date of the credit card. */
    val expiryYear: String?

    /** The payment agreement start date and time. The timestamp format is ISO8601. */
    val startDate: Date?

    /** The payment agreement end date and time. The timestamp format is ISO8601. */
    val endDate: Date?

    /** The payment agreement charge frequency. */
    val chargeFrequency: PaymentAgreementChargeFrequency

    /** The amount that will be charged at the frequency specified in the payment agreement. */
    val chargeAmount: BigDecimal

    /** The current charge cycle number. */
    val chargeCycle: BigDecimal

    /** A flag to indicate if the payment agreement is expired. */
    val expired: Boolean?

    /** The URL of the endpoint to use to update the payment agreement. */
    val updateURL: String

    val stepUp: PaymentAgreementStepUp?

    /** A description of the payment agreement */
    val description: String?
}

interface PaymentAgreementStepUp {
    /* The type of the step up action. For credit cards this will be CAPTURE_CVV which identifies that the consumer must capture the CVV prior to payment. */
    val type: String

    /* A flag to indicate if this step up (action) is mandatory. */
    val mandatory: Boolean?

    /* The URL of an iframe. This iframe is used to capture a credit card expiry and CVV or CVV only. The URL will automatically switch between expiry and CVV or CVV only endpoints based on the container requirement. */
    val url: String
}

/**
 * List of payments agreements.
 *
 * @category Model
 */
interface PaymentAgreements {
    /** The resulting list of payment agreements. */
    val paymentAgreements: List<PaymentAgreement>
}

/**
 * Frequency with which the payment agreement is charged
 *
 * @category Model
 */
enum class PaymentAgreementChargeFrequency {
    WEEKLY,
    FORTNIGHTLY,
    MONTHLY
}

/**
 * Type of payment agreement
 *
 * @category Model
 */
enum class PaymentAgreementType {
    RECURRING,
    ADHOC,
    INSTALLMENT
}
