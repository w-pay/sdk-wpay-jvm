package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.model.CreditCardStepUp
import java.io.Serializable

/**
 * The JSON success response structure of the Payments endpoint.
 *
 * @category Model
 */
interface DigitalPayPaymentResponse : Serializable {
    /**
     *  Container reference in the transaction logs.
     *
     * This number uniquely identifies the whole/grouped transaction in the container.
     */
    val transactionReceipt: String

    /**
     * A flag to indicate if a split payment was only partially successful,
     *
     * ie. at least 1 of the payment instruments had a successful payment result.
     */
    val partialSuccess: Boolean?

    /** DigitalPay fraud response */
    val fraudResponse: DigitalPayFraudResponse

    /** DigitalPay payment credit card payments */
    val creditCards: List<DigitalPayCreditCard>

    /** DigitalPay payment gift card payments */
    val giftCards: List<DigitalPayGiftCard>

    /** DigitalPay PayPal card payments */
    val payPal: List<DigitalPayPayPal>

    /**
     * Android Pay has been replaced by Google Pay.
     *
     * This property has been retained for backward compatibility and will always be an empty array.
     */
    val androidPay: List<Any>

    /** DigitalPay payment Google Pay payments */
    val googlePay: List<DigitalPayGooglePay>

    /** DigitalPay payment Apple Pay payments */
    val applePay: List<DigitalPayApplePay>

    /** DigitalPay payment unknown payments */
    val unknown: List<DigitalPayPaymentInstrument>
}

interface DigitalPayFraudResponse : Serializable {
    /** The fraud check client id. Will be null if the fraud check was skipped. */
    val clientId: String

    /** The fraud check reason code. Will be null if the fraud check was skipped. */
    val reasonCode: String

    /** The fraud check decision. Will be null if the fraud check was skipped. */
    val decision: String
}

interface DigitalPayPaymentInstrument : Serializable {
    /** The credit card payment instrument id. */
    val paymentInstrumentId: String

    /** The credit card payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String

    /** Container reference in the transaction logs. This number uniquely identifies the credit card transaction in the container. */
    val paymentTransactionRef: String

    /** The error code. Only present if an error occurred during payment. */
    val errorCode: String?

    /** The error message. Only present if an error occurred during payment. */
    val errorMessage: String?

    /** The error detail. Only present if an error occurred during payment. */
    val errorDetail: String?
}

interface DigitalPayCreditCard : DigitalPayPaymentInstrument {
    /** Only present if an error occurred during payment. */
    val stepUp: CreditCardStepUp?

    /** This object is only included in the response if it is enabled in the consumers API configuration. */
    val receiptData: DigitalPayCreditCardReceiptData?

    /** This array is only included in the response if it is enabled in the consumers API configuration. */
    val extendedTransactionData: List<DigitalPayExtendedTransactionData>?

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

    /**
     * Special handling instructions that have to be executed after a payment.
     *
     * Only present if no error occurred during payment.
     */
    val handlingInstructions: DigitalPayHandlingInstructions?

    /** This object is only present if the payments response contains 3DS data from Digital Pay. */
    val threeDS: DigitalPayThreeDSResponse?
}

interface DigitalPayHandlingInstructions : Serializable {
    /** The handling instruction code. */
    val instructionCode: DigitalPayInstructionCode

    /** The handling instruction message. */
    val instructionMessage: String
}

interface DigitalPayGiftCard : DigitalPayPaymentInstrument {
    /** Only present if an error occurred during payment. */
    val stepUp: CreditCardStepUp?

    /** This object is only included in the response if it is enabled in the consumers API configuration. */
    val receiptData: DigitalPayGiftCardReceiptData?

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

interface DigitalPayPayPal : DigitalPayPaymentInstrument {
    /** This object is only included in the response if it is enabled in the consumers API configuration. */
    val receiptData: DigitalPayPayPalReceiptData?

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

interface DigitalPayGooglePay : DigitalPayPaymentInstrument {
    /** Only present if an error occurred during payment. */
    val stepUp: CreditCardStepUp?

    /** This array is only included in the response if it is enabled in the consumers API configuration. */
    val extendedTransactionData: List<DigitalPayExtendedTransactionData>?

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

interface DigitalPayApplePay : DigitalPayPaymentInstrument {
    /** Only present if an error occurred during payment. */
    val stepUp: CreditCardStepUp?
}

interface DigitalPayCreditCardReceiptData {
    /** The suffix (last 4 digits) of the credit card number used in the WebPay transaction. */
    val cardSuffix: String

    /** The credit card scheme. */
    val scheme: String

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String

    /** The year of the expiry date of the credit card. */
    val expiryYear: String
}

interface DigitalPayGiftCardReceiptData {
    /** The suffix (last 4 digits) of the gift card number used in the WEX transaction. */
    val cardSuffix: String
}

interface DigitalPayPayPalReceiptData {
    /** The Paypal email id. */
    val payPalId: String

    /** The Paypal customer id. */
    val customerId: String
}

interface DigitalPayExtendedTransactionData : Serializable {
    /**
     * The name of the extended transaction data field.
     *
     * The 'token' field is only included in the response if it is enabled in the consumers API configuration.
     */
    val field: DigitalPayExtendedTransactionDataFieldName

    /** The value of the extended transaction data field. */
    val value: String
}

interface DigitalPayThreeDSResponse {
    /** Received in response to a Visa authenticated Purchase and PreAuth. Only present for Visa. */
    val car: String?

    /** The Directory Server (DS) authentication identification code. A universally unique transaction identifier assigned by the DS to identify a single transaction. The format of the value is defined in IETF RFC 4122. It may utilise any of the specified versions if the output meets specific requirements. */
    val dsTransID: String

    /** The SLI from the the schemes. */
    val sli: String
}

enum class DigitalPayExtendedTransactionDataFieldName {
    BIN,
    STAN,
    RRN,
    TOKEN,
    MID,
    TERMINA_ID
}

enum class DigitalPayInstructionCode {
    INSTRUCTION_100,
    INSTRUCTION_110,
    INSTRUCTION_120
}
