package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.ModelType
import au.com.woolworths.village.sdk.model.PaymentAgreementChargeFrequency
import au.com.woolworths.village.sdk.model.PaymentAgreementType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Create Payment Agreement endpoint.
 */
@Serializable
data class DigitalPayCreatePaymentAgreementRequest(
    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String,

    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the customer in the merchant’s system.
     */
    val customerRef: String? = null,

    /**
     * The merchant order number of the transaction.
     *
     * This property is only required if the 'immediateCharge' property is true.
     */
    val orderNumber: String? = null,

    /** Customer billing address for this payment agreement */
    val billingAddress: DigitalPayAddress,

    /** Detail of the payment agreement to be created */
    val paymentAgreement: DigitalPayRequestPaymentAgreement,

    /** Digital pay fraud payload */
    val fraudPayload: DigitalPayFraudPayload? = null
) : ModelType

@Serializable
data class DigitalPayRequestPaymentAgreement(
    /** The payment agreement type. */
    val type: PaymentAgreementType,

    /** The payment agreement payment instrument id that will be used for the charges. */
    val paymentInstrumentId: String,

    /** The payment agreement charge frequency. */
    val chargeFrequency: PaymentAgreementChargeFrequency,

    /** The amount that will be charged at the frequency specified in the payment agreement. */
    @Serializable(with = CurrencySerializer::class)
    val chargeAmount: BigDecimal,

    /** The payment agreement start date and time. The timestamp format is ISO8601. */
    val startDate: String? = null,

    /** The payment agreement end date and time. The timestamp format is ISO8601. */
    val endDate: String? = null,

    /**
     * A flag to indicate if a charge transaction must be performed at the time of payment agreement creation.
     *
     * This in convenient in the cases where a customer wants to process a first charge transaction immediately at payment agreement creation.
     */
    val immediateCharge: Boolean,

    /**
     * The step-up token is used to track additional credit card information (eg. CVV and expiry) attached to the payment instrument.
     *
     * It's only valid for a predefined time and if an expired step-up token is used during validation, the validation of that instrument will fail and the user will have to get a new step-up token before retrying the API call. A step-up token is returned in the response of a credit card iframe. This property is only required for credit card instruments and only if specific credit card information (eg. CVV and expiry) is required during the API call.
     */
    val stepUpToken: String,
) : ModelType
