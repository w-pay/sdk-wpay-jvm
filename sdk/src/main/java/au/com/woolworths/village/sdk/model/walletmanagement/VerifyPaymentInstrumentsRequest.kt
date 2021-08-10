package au.com.woolworths.village.sdk.model.walletmanagement

import java.io.Serializable

interface VerifyPaymentInstrumentsRequest : Serializable {
    /* A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system.*/
    val clientReference: String

    /* The step-up token is used to track additional credit card information (eg. CVV and expiry) attached to the payment instrument. It's only valid for a predefined time and if an expired step-up token is used during payment, the payment for that instrument will fail and the user will have to get a new step-up token before retrying the payment. A step-up token is returned in the response of a credit card iframe. This property is currently only required for credit card instruments and only if specific credit card information (eg. CVV and expiry) is required during payment. */
    val paymentInstruments: List<VerifyPaymentInstrumentsRequestInstrument>

    /* Set to null to skip the fraud check. */
    val fraudPayload: FraudPayload?
}

interface FraudPayload : Serializable {
    /* The fraud check provider. */
    val provider: String

    /* The fraud check version. */
    val version: String

    /* The fraud check message format. */
    val format: MessageFormat

    /* The fraud check message format. */
    val responseFormat: MessageFormat

    /* The fraud check message. */
    val message: String
}

interface VerifyPaymentInstrumentsRequestInstrument : Serializable {
    /* The payment token. */
    val paymentToken: String

    /**
     * The step-up token is used to track additional credit card information (eg. CVV and expiry) attached to the payment instrument.
     * It's only valid for a predefined time and if an expired step-up token is used during payment,
     * the payment for that instrument will fail and the user will have to get a new step-up token before retrying the payment.
     * A step-up token is returned in the response of a credit card iframe.
     * This property is currently only required for credit card instruments and only if specific credit card information (eg. CVV and expiry) is required during payment.
     */
    val stepUpToken: String
}

enum class MessageFormat {
    ZIP_BASE_64_ENCODED,
    XML
}