package au.com.wpay.sdk.model.digitalpay

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.model.ModelType
import au.com.wpay.sdk.model.PaymentAgreementChargeFrequency
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Update Payment Agreement endpoint.
 */
@Serializable
data class DigitalPayUpdatePaymentAgreementRequest(
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

    /** Customer billing address for this payment agreement */
    val billingAddress: DigitalPayAddress? = null,

    /** Detail of the payment agreement to be created */
    val paymentAgreement: DigitalPayPaymentAgreementUpdate? = null,

    /** Digital pay fraud payload */
    val fraudPayload: DigitalPayFraudPayload? = null
) : ModelType

@Serializable
data class DigitalPayPaymentAgreementUpdate(
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
     * The step-up token is used to track additional credit card information (eg. CVV and expiry) attached to the payment instrument.
     *
     * It's only valid for a predefined time and if an expired step-up token is used during validation, the validation of that instrument will fail and the user will have to get a new step-up token before retrying the API call. A step-up token is returned in the response of a credit card iframe. This property is only required for credit card instruments and only if specific credit card information (eg. CVV and expiry) is required during the API call.
     */
    val stepUpToken: String
) : ModelType

