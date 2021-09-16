package au.com.woolworths.village.sdk.model.digitalpay

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Payments endpoint
 *
 * @category Model
 */
interface DigitalPayPaymentRequest : Serializable {
    /**
     * Transaction type containers to use for all instruments.
     *
     * This object is only required if the payments request contains apple pay instruments.
     */
    val transactionType: PaymentTransactionType

    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String

    /** The merchant order number of the transaction. */
    val orderNumber: String

    /** This object is only required if the payments request contains paypal instruments. */
    val shippingAddress: DigitalPayAddress

    /** List of payments */
    val payments: List<DigitalPayPayment>

    /** Extended merchant data */
    val extendedMerchantData: List<ExtendedMerchantData>?

    /** Set to null to skip the cybersource fraud check. */
    val fraudPayload: DigitalPayFraudPayload?

    /** This object is only required if the payments request is for an in-store payment transaction. */
    val storeData: DigitalPayStoreData?
}

interface DigitalPayPayment : Serializable {
    /**
     * The payment instrument id from the card capture iframe response or the list payment instruments response.
     *
     * This property can be omitted if the payment token property is present.
     */
    val paymentInstrumentId: String

    /**
     * The payment token from the card capture iframe response or the list payment instruments response.
     *
     * This property can be omitted if the payment instrument id property is present.
     */
    val paymentToken: String

    /** The amount you want to pay with the payment instrument. */
    val amount: BigDecimal

    /**
     * The step-up token is used to track additional credit card information (eg. CVV and expiry) attached to the payment instrument.
     *
     * It's only valid for a predefined time and if an expired step-up token is used during payment, the payment for that instrument will fail and the user will have to get a new step-up token before retrying the payment. A step-up token is returned in the response of a credit card iframe.
     *
     * This property is currently only required for credit card instruments and only if specific credit card information (eg. CVV and expiry) is required during payment.
     */
    val stepUpToken: String?

    /**
     * The passcode is used to send additional information (eg. gift card PIN) for the payment instrument.
     *
     * This property is currently only required for gift card instruments and only if the gift card PIN is required during payment.
     *
     * This property should NOT be used with credit card instruments (see stepUpToken).
     */
    val passcode: String?

    /** This object is used to pass additonal control data to Digital Pay */
    val controlData: DigitalPayControlData?

    /** This object is only required if the payments request requires 3DS challenge response data to be sent to Digital Pay. */
    val threeDS: DigitalPayThreeDS?
}

interface DigitalPayStoreData : Serializable {
    /** The payment transaction merchant group id. The group id is defined as a logical grouping of merchants or stores. A default configured group id is set in Apigee if absent in the payload. */
    val groupId: String?

    /** The in-store payment transaction terminal id. This is a 8 character alphanumeric string. If present in the payload the 'storeId' has to be omitted. */
    val terminalId: String?

    /** The in-store payment transaction store id. */
    val storeId: String

    /** The in-store payment transaction lane id. */
    val laneId: String?

    /** The System Trace Audit Number (STAN) used to identify the transaction. This is a 6 digit numeric string. */
    val stan: String
    
    /** The in-store payment transaction store id. This is a 12 digit \"0\" [zero] padded numeric string. */
    val rrn: String

    /** The in-store payment transaction timestamp. The timestamp format is milliseconds since epoch. */
    val transactionTimestamp: BigDecimal
}

interface ExtendedMerchantData : Serializable {
    /** The name of the extended merchant data field. */
    val field: String
        get() = "correlationId"

    /** The value of the extended merchant data field. */
    val value: String
}

interface DigitalPayControlData {
    enum class TokenType {
        SCHEME_TOKEN, SCDR, PAN
    }
    
    /** The Digital Pay token type to use for a scheme card instrument during 3DS processing for merchants that have 3DS enabled. Defaults to 'PAN' if absent. **/
    val tokenType: TokenType? 
}

interface DigitalPayThreeDS {
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

    /** The Protocol Version Number of the specification utilised by the system creating this message. */
    val messageVersion: String

    /** The transaction identifier. Required for Mastercard and Amex. Not applicable for Visa. */
    val xid: String

    /** The payment system-specific value provided by the ACS or the Directory Server (DS) using an algorithm defined by Payment System. */
    val authenticationValue: String

    /** The Directory Server (DS) authentication identification code. A universally unique transaction identifier assigned by the DS to identify a single transaction. The format of the value is defined in IETF RFC 4122. It may utilise any of the specified versions if the output meets specific requirements. */
    val dsTransID: String

    /** The electronic commerce indicator. Required for calculating the SLI. A Payment System-specific value provided by the ACS or DS to indicate the results of the attempt to authenticate the Cardholder. */
    val eci: String

    /** The payer authentication response status. Required for Visa.
     *  Y: Customer was successfully authenticated
     *  N: Customer failed or canceled authentication
     *  C: Card challenged
     *  R: Authentication rejected
     *  A: Proof of authentication attempt was generated
     *  U: Authentication not completed regardless of the reason
     */
    val aresStatus: AresStatus

    /** The verification response enrollment status. Required for Visa.
     *  Y: Card enrolled, must authenticate
     *  N: Card not enrolled, proceed with authorization
     *  U: Unable to authenticate regardless of the reason
     *  B: Indicates that authentication was bypassed
     */
    val veresEnrolled: AresStatus

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
    val transStatus: TransStatus

    /** The SLI from the merchant */
    val sli: String?
}
