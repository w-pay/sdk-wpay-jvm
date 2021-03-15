package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON response structure of the Create and Update Payment Agreement endpoints.
 *
 * @category Model
 */
interface DigitalPayPaymentAgreementResponse : Serializable {
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the transaction in the container.
     */
    val transactionReceipt: String

    /**
     * The payment token of the payment agreement.
     *
     * The payment token is a unique identifier for the payment agreement.
     */
    val paymentToken: String?

    /** Detail of the payment agreement that has been created or updated */
    val paymentAgreement: DigitalPayResponsePaymentAgreement

    /** Fraud response */
    val fraudResponse: DigitalPayFraudResponse?

    /** Extended transaction data object */
    val extendedTransactionData: DigitalPayExtendedTransactionData?

    /**
     * The external service code (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String?

    /**
     * The external service message (from eg. Webpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String?
}

interface DigitalPayResponsePaymentAgreement : Serializable {
    /** The payment agreement type. */
    val type: PaymentAgreementType

    /** The payment agreement payment instrument id that will be used for the charges. */
    val paymentInstrumentId: String

    /** The type of the payment instrument used in the payment agreement. */
    val paymentInstrumentType: String

    /** The credit card scheme */
    val scheme: String

    /** The suffix (last 4 digits) of the credit card number. */
    val cardSuffix: String

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String

    /** The year of the expiry date of the credit card. */
    val expiryYear: String

    /** The payment agreement start date and time. The timestamp format is ISO8601. */
    val startDate: String

    /** The payment agreement end date and time. The timestamp format is ISO8601. */
    val endDate: String

    /** The payment agreement charge frequency. */
    val chargeFrequency: PaymentAgreementChargeFrequency

    /** The amount that will be charged at the frequency specified in the payment agreement. */
    val chargeAmount: BigDecimal
}
