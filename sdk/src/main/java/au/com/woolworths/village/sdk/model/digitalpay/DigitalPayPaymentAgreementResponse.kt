package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.ModelType
import au.com.woolworths.village.sdk.model.PaymentAgreementChargeFrequency
import au.com.woolworths.village.sdk.model.PaymentAgreementType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON response structure of the Create and Update Payment Agreement endpoints.
 */
@Serializable
data class DigitalPayPaymentAgreementResponse(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the transaction in the container.
     */
    val transactionReceipt: String,

    /**
     * The payment token of the payment agreement.
     *
     * The payment token is a unique identifier for the payment agreement.
     */
    val paymentToken: String? = null,

    /** Detail of the payment agreement that has been created or updated */
    val paymentAgreement: DigitalPayResponsePaymentAgreement,

    /** Fraud response */
    val fraudResponse: DigitalPayFraudResponse? = null,

    /** Extended transaction data object */
    val extendedTransactionData: List<DigitalPayExtendedTransactionData>? = null,

    /**
     * The external service code (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String? = null,

    /**
     * The external service message (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String? = null
) : ModelType

@Serializable
data class DigitalPayResponsePaymentAgreement(
    /** The payment agreement type. */
    val type: PaymentAgreementType,

    /** The payment agreement payment instrument id that will be used for the charges. */
    val paymentInstrumentId: String,

    /** The type of the payment instrument used in the payment agreement. */
    val paymentInstrumentType: String,

    /** The credit card scheme */
    val scheme: String,

    /** The suffix (last 4 digits) of the credit card number. */
    val cardSuffix: String,

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String,

    /** The year of the expiry date of the credit card. */
    val expiryYear: String,

    /** The payment agreement start date and time. The timestamp format is ISO8601. */
    val startDate: String,

    /** The payment agreement end date and time. The timestamp format is ISO8601. */
    val endDate: String,

    /** The payment agreement charge frequency. */
    val chargeFrequency: PaymentAgreementChargeFrequency,

    /** The amount that will be charged at the frequency specified in the payment agreement. */
    @Serializable(with = CurrencySerializer::class)
    val chargeAmount: BigDecimal
) : ModelType
