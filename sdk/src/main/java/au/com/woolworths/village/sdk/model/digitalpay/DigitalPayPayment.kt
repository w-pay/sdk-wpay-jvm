package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.CreditCardStepUp
import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import java.math.BigDecimal

/**
 * The JSON request structure of the Payments endpoint
 */
@Serializable
data class DigitalPayPaymentRequest(
    /**
     * Transaction type containers to use for all instruments.
     *
     * This object is only required if the payments request contains apple pay instruments.
     */
    val transactionType: PaymentTransactionType,

    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String,

    /** The merchant order number of the transaction. */
    val orderNumber: String,

    /** This object is only required if the payments request contains paypal instruments. */
    val shippingAddress: DigitalPayAddress,

    /** List of payments */
    val payments: List<DigitalPayPayment>,

    /** Extended merchant data */
    val extendedMerchantData: List<ExtendedMerchantData>? = null,

    /** Set to null to skip the cybersource fraud check. */
    val fraudPayload: DigitalPayFraudPayload? = null,

    /** This object is only required if the payments request is for an in-store payment transaction. */
    val storeData: DigitalPayStoreData? = null,
) : ModelType

@Serializable
data class DigitalPayPayment(
    /**
     * The payment instrument id from the card capture iframe response or the list payment instruments response.
     *
     * This property can be omitted if the payment token property is present.
     */
    val paymentInstrumentId: String,

    /**
     * The payment token from the card capture iframe response or the list payment instruments response.
     *
     * This property can be omitted if the payment instrument id property is present.
     */
    val paymentToken: String,

    /** The amount you want to pay with the payment instrument. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /**
     * The step-up token is used to track additional credit card information (eg. CVV and expiry) attached to the payment instrument.
     *
     * It's only valid for a predefined time and if an expired step-up token is used during payment, the payment for that instrument will fail and the user will have to get a new step-up token before retrying the payment. A step-up token is returned in the response of a credit card iframe.
     *
     * This property is currently only required for credit card instruments and only if specific credit card information (eg. CVV and expiry) is required during payment.
     */
    val stepUpToken: String? = null,

    /**
     * The passcode is used to send additional information (eg. gift card PIN) for the payment instrument.
     *
     * This property is currently only required for gift card instruments and only if the gift card PIN is required during payment.
     *
     * This property should NOT be used with credit card instruments (see stepUpToken).
     */
    val passcode: String? = null,

    /** This object is used to pass additonal control data to Digital Pay */
    val controlData: DigitalPayControlData? = null,

    /** This object is only required if the payments request requires 3DS challenge response data to be sent to Digital Pay. */
    val threeDS: DigitalPayThreeDS? = null,
) : ModelType

@Serializable
data class DigitalPayStoreData(
    /** The payment transaction merchant group id. The group id is defined as a logical grouping of merchants or stores. A default configured group id is set in Apigee if absent in the payload. */
    val groupId: String? = null,

    /** The in-store payment transaction terminal id. This is a 8 character alphanumeric string. If present in the payload the 'storeId' has to be omitted. */
    val terminalId: String? = null,

    /** The in-store payment transaction store id. */
    val storeId: String,

    /** The in-store payment transaction lane id. */
    val laneId: String? = null,

    /** The System Trace Audit Number (STAN) used to identify the transaction. This is a 6 digit numeric string. */
    val stan: String,

    /** The in-store payment transaction store id. This is a 12 digit \"0\" [zero] padded numeric string. */
    val rrn: String,

    /** The in-store payment transaction timestamp. The timestamp format is milliseconds since epoch. */
    val transactionTimestamp: Long
) : ModelType

@Serializable
data class ExtendedMerchantData(
    /** The name of the extended merchant data field. */
    val field: Field,

    /** The value of the extended merchant data field. */
    val value: String
) : ModelType {
    enum class Field(val fieldName: String) {
        CORRELATION_ID("correlationId")
    }
}

@Serializable
data class DigitalPayControlData(
    /** The Digital Pay token type to use for a scheme card instrument during 3DS processing for merchants that have 3DS enabled. Defaults to 'PAN' if absent. **/
    val tokenType: TokenType? = null
) {
    enum class TokenType {
        SCHEME_TOKEN, SCDR, PAN
    }
}

@Serializable
data class DigitalPayThreeDS(
    /** The Protocol Version Number of the specification utilised by the system creating this message. */
    val messageVersion: String,

    /** The transaction identifier. Required for Mastercard and Amex. Not applicable for Visa. */
    val xid: String,

    /** The payment system-specific value provided by the ACS or the Directory Server (DS) using an algorithm defined by Payment System. */
    val authenticationValue: String,

    /** The Directory Server (DS) authentication identification code. A universally unique transaction identifier assigned by the DS to identify a single transaction. The format of the value is defined in IETF RFC 4122. It may utilise any of the specified versions if the output meets specific requirements. */
    val dsTransID: String,

    /** The electronic commerce indicator. Required for calculating the SLI. A Payment System-specific value provided by the ACS or DS to indicate the results of the attempt to authenticate the Cardholder. */
    val eci: String,

    /** The payer authentication response status. Required for Visa.
     *  Y: Customer was successfully authenticated
     *  N: Customer failed or canceled authentication
     *  C: Card challenged
     *  R: Authentication rejected
     *  A: Proof of authentication attempt was generated
     *  U: Authentication not completed regardless of the reason
     */
    val aresStatus: AresStatus,

    /** The verification response enrollment status. Required for Visa.
     *  Y: Card enrolled, must authenticate
     *  N: Card not enrolled, proceed with authorization
     *  U: Unable to authenticate regardless of the reason
     *  B: Indicates that authentication was bypassed
     */
    val veresEnrolled: AresStatus,

    /** Indicates whether a transaction qualifies as an authenticated transaction or account verification.
     *  Y: Authentication Successful
     *  N: Not Authenticated
     *  U: Authentication could not be performed
     *  A: Attempts Processing Performed Not authenticated
     *  C: Challenge Required. Additional authentication is required
     *  D: Challenge Required Decoupled Authentication performed
     *  R: Authentication Rejected. Issuer is rejecting
     *  I: Informational Only
     */
    val transStatus: TransStatus,

    /** The SLI from the merchant */
    val sli: String? = null
) {
    enum class AresStatus(val statusCode: String) {
      AUTHENTICATED("Y"),
      FAILED_OR_CANCELLED_AUTHENTICATION("N"),
      CARD_CHALLENGED("C"),
      AUTHENTICATION_REJECTED("R"),
      PROOF_OF_AUTHENTICATION_GENERATED("A"),
      AUTHENTICATION_NOT_COMPLETE("U");

      companion object {
          private val codes: Map<String, AresStatus> = values()
            .groupingBy { it.statusCode }
            .reduce { _, _, code -> code }

          fun fromCode(code: String): AresStatus? =
              codes[code]
      }
    }

    enum class VeresEnrolled(val statusCode: String) {
        ENROLLED_MUST_AUTHENTICATE("Y"),
        NOT_ENROLLED("N"),
        UNABLE_TO_AUTHENTICATE("U"),
        AUTHENTICATION_BYPASSED("B");

        companion object {
            private val codes: Map<String, VeresEnrolled> = values()
                .groupingBy { it.statusCode }
                .reduce { _, _, code -> code }

            fun fromCode(code: String): VeresEnrolled? =
                codes[code]
        }
    }

    enum class TransStatus(val statusCode: String) {
        AUTHENTICATION_SUCCESSFUL("Y"),
        NOT_AUTHENTICATED("N"),
        AUTHENTICATION_NOT_PERFORMED("U"),
        ATTEMPTS_PROCESSING_PERFORMED("A"),
        ADDITIONAL_AUTHENTICATION_REQUIRED("C"),
        DECOUPLED_AUTHENTICATION_PERFORMED("D"),
        AUTHENTICATION_REJECTED("R"),
        INFORMATIONAL_ONLY("I");

        companion object {
            private val codes: Map<String, TransStatus> = values()
                .groupingBy { it.statusCode }
                .reduce { _, _, code -> code }

            fun fromCode(code: String): TransStatus? =
                codes[code]
        }
    }
}

/**
 * The JSON success response structure of the Payments endpoint.
 */
@Serializable
data class DigitalPayPaymentResponse(
    /**
     *  Container reference in the transaction logs.
     *
     * This number uniquely identifies the whole/grouped transaction in the container.
     */
    val transactionReceipt: String,

    /**
     * A flag to indicate if a split payment was only partially successful,
     *
     * ie. at least 1 of the payment instruments had a successful payment result.
     */
    val partialSuccess: Boolean? = null,

    /** DigitalPay fraud response */
    val fraudResponse: DigitalPayFraudResponse,

    /** DigitalPay payment credit card payments */
    val creditCards: List<DigitalPayCreditCard>,

    /** DigitalPay payment gift card payments */
    val giftCards: List<DigitalPayGiftCard>,

    /** DigitalPay PayPal card payments */
    val payPal: List<DigitalPayPayPal>,

    /**
     * Android Pay has been replaced by Google Pay.
     *
     * This property has been retained for backward compatibility and will always be an empty array.
     */
    val androidPay: List<JsonObject>,

    /** DigitalPay payment Google Pay payments */
    val googlePay: List<DigitalPayGooglePay>,

    /** DigitalPay payment Apple Pay payments */
    val applePay: List<DigitalPayApplePay>,

    /** DigitalPay payment unknown payments */
    val unknown: List<DigitalPayPaymentInstrument>
) : ModelType

@Serializable
data class DigitalPayFraudResponse(
    /** The fraud check client id. Will be null if the fraud check was skipped. */
    val clientId: String,

    /** The fraud check reason code. Will be null if the fraud check was skipped. */
    val reasonCode: String,

    /** The fraud check decision. Will be null if the fraud check was skipped. */
    val decision: String
) : ModelType

interface DigitalPayPaymentInstrumentType : ModelType {
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

@Serializable
data class DigitalPayPaymentInstrument(
    override val paymentInstrumentId: String,
    override val paymentToken: String,
    override val paymentTransactionRef: String,
    override val errorCode: String? = null,
    override val errorMessage: String? = null,
    override val errorDetail: String? = null
) : DigitalPayPaymentInstrumentType

@Serializable
data class DigitalPayCreditCard(
    override val paymentInstrumentId: String,
    override val paymentToken: String,
    override val paymentTransactionRef: String,
    override val errorCode: String? = null,
    override val errorMessage: String? = null,
    override val errorDetail: String? = null,

    /** Only present if an error occurred during payment. */
    val stepUp: CreditCardStepUp? = null,

    /** This object is only included in the response if it is enabled in the consumers API configuration. */
    val receiptData: DigitalPayCreditCardReceiptData? = null,

    /** This array is only included in the response if it is enabled in the consumers API configuration. */
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
    val externalServiceMessage: String? = null,

    /**
     * Special handling instructions that have to be executed after a payment.
     *
     * Only present if no error occurred during payment.
     */
    val handlingInstructions: DigitalPayHandlingInstructions? = null,

    /** This object is only present if the payments response contains 3DS data from Digital Pay. */
    val threeDS: DigitalPayThreeDSResponse? = null
) : DigitalPayPaymentInstrumentType

@Serializable
data class DigitalPayHandlingInstructions(
    /** The handling instruction code. */
    val instructionCode: DigitalPayInstructionCode,

    /** The handling instruction message. */
    val instructionMessage: String
) : ModelType

@Serializable
data class DigitalPayGiftCard(
    override val paymentInstrumentId: String,
    override val paymentToken: String,
    override val paymentTransactionRef: String,
    override val errorCode: String? = null,
    override val errorMessage: String? = null,
    override val errorDetail: String? = null,

    /** Only present if an error occurred during payment. */
    val stepUp: CreditCardStepUp? = null,

    /** This object is only included in the response if it is enabled in the consumers API configuration. */
    val receiptData: DigitalPayGiftCardReceiptData? = null,

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
    val externalServiceMessage: String? = null,
) : DigitalPayPaymentInstrumentType

@Serializable
data class DigitalPayPayPal(
    override val paymentInstrumentId: String,
    override val paymentToken: String,
    override val paymentTransactionRef: String,
    override val errorCode: String? = null,
    override val errorMessage: String? = null,
    override val errorDetail: String? = null,

    /** This object is only included in the response if it is enabled in the consumers API configuration. */
    val receiptData: DigitalPayPayPalReceiptData? = null,

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
    val externalServiceMessage: String? = null,
) : DigitalPayPaymentInstrumentType

@Serializable
data class DigitalPayGooglePay(
    override val paymentInstrumentId: String,
    override val paymentToken: String,
    override val paymentTransactionRef: String,
    override val errorCode: String? = null,
    override val errorMessage: String? = null,
    override val errorDetail: String? = null,

    /** Only present if an error occurred during payment. */
    val stepUp: CreditCardStepUp? = null,

    /** This array is only included in the response if it is enabled in the consumers API configuration. */
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
) : DigitalPayPaymentInstrumentType

@Serializable
data class DigitalPayApplePay(
    override val paymentInstrumentId: String,
    override val paymentToken: String,
    override val paymentTransactionRef: String,
    override val errorCode: String? = null,
    override val errorMessage: String? = null,
    override val errorDetail: String? = null,

    /** Only present if an error occurred during payment. */
    val stepUp: CreditCardStepUp?
) : DigitalPayPaymentInstrumentType

@Serializable
data class DigitalPayCreditCardReceiptData(
    /** The suffix (last 4 digits) of the credit card number used in the WebPay transaction. */
    val cardSuffix: String,

    /** The credit card scheme. */
    val scheme: String,

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String,

    /** The year of the expiry date of the credit card. */
    val expiryYear: String
) : ModelType

@Serializable
data class DigitalPayGiftCardReceiptData(
    /** The suffix (last 4 digits) of the gift card number used in the WEX transaction. */
    val cardSuffix: String
) : ModelType

@Serializable
data class DigitalPayPayPalReceiptData(
    /** The Paypal email id. */
    val payPalId: String,

    /** The Paypal customer id. */
    val customerId: String
) : ModelType

@Serializable
data class DigitalPayExtendedTransactionData(
    /**
     * The name of the extended transaction data field.
     *
     * The 'token' field is only included in the response if it is enabled in the consumers API configuration.
     */
    val field: DigitalPayExtendedTransactionDataFieldName,

    /** The value of the extended transaction data field. */
    val value: String
) : ModelType

@Serializable
data class DigitalPayThreeDSResponse(
    /** Received in response to a Visa authenticated Purchase and PreAuth. Only present for Visa. */
    val car: String? = null,

    /** The Directory Server (DS) authentication identification code. A universally unique transaction identifier assigned by the DS to identify a single transaction. The format of the value is defined in IETF RFC 4122. It may utilise any of the specified versions if the output meets specific requirements. */
    val dsTransID: String,

    /** The SLI from the schemes. */
    val sli: String
) : ModelType

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
