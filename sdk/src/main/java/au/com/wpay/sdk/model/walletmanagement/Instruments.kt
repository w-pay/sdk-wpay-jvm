package au.com.wpay.sdk.model.walletmanagement

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import java.math.BigDecimal

/**
 * The JSON request structure of the Import Payment InstrumentsApi endpoint.
 */
@Serializable
data class ImportPaymentInstrumentsRequest(
    /** The IDM (Gigya) UID or merchant shopper id of the user. Do NOT use an email address! */
    val uid: String,

    /** The merchant shopper id of the user. */
    val shopperId: String,

    val creditCards: List<PaymentInstrumentRequestCreditCard>? = null,
    val payPal: PayPalDetail? = null
) : ModelType

@Serializable
data class PayPalDetail(
    /** The PayPalApi customer id. */
    val customerId: String,

    /** The PayPalApi email id. */
    val payPalId: String,

    /** The PayPalApi payment method token. */
    val paymentMethodToken: String
) : ModelType

@Serializable
data class PaymentInstrumentRequestCreditCard(
    /** WebPay reference in the transaction logs. This number uniquely identifies the transaction in WebPay. */
    val transactionRef: String,

    /** The WebPay transaction timestamp. The timestamp format is ISO8601. */
    val transactionTimestamp: String,

    /** The merchant order number used in the WebPay transaction. */
    val orderNumber: String,

    /** The bin (first 6 digits) of the credit card number used in the WebPay transaction. */
    val bin: String,

    /** The suffix (last 4 digits) of the credit card number used in the WebPay transaction. */
    val cardSuffix: String,

    /** The amount of the WebPay transaction. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal
) : ModelType

/**
 * The JSON response structure of the Import Payment InstrumentsApi endpoint.
 */
@Serializable
data class ImportPaymentInstrumentsResponse(
    /** The IDM (Gigya) UID or merchant shopper id of the user. Do NOT use an email address! */
    val uid: String,

    /** The merchant shopper id of the user. */
    val shopperId: String,

    val creditCards: List<CreditCardResult>? = null,

    val payPal: PayPal? = null
) : ModelType

@Serializable
data class PayPal(
    /** The PayPalApi customer id. */
    val customerId: String,

    /** The PayPalApi email id. */
    val payPalId: String,

    /** The PayPalApi payment method token.*/
    val paymentMethodToken: String,

    /** The import process result for the paypal instrument. */
    val result: ResultEnum,

    /** The import process error message if "result" is "ERROR". Will be null if "result" is not "ERROR". */
    val errorMessage: ErrorMessage?
) : ModelType

@Serializable
data class ErrorMessage(
    val description: String
) : ModelType

@Serializable
data class CreditCardResult(
    /** WebPay reference in the transaction logs. This number uniquely identifies the transaction in WebPay. */
    val transactionRef: String,

    /** The WebPay transaction timestamp. The timestamp format is ISO8601. */
    val transactionTimestamp: String,

    /** The WebPay transaction type. */
    val transactionType: String,

    /** The WebPay transaction response code. */
    val transactionResponseCode: String,

    /** The WebPay transaction response text. */
    val transactionResponseText: String,

    /** The merchant order number used in the WebPay transaction. */
    val orderNumber: String,

    /** The bin (first 6 digits) of the credit card number used in the WebPay transaction. */
    val bin: String,

    /** The suffix (last 4 digits) of the credit card number used in the WebPay transaction. */
    val cardSuffix: String,

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String,

    /** The year of the expiry date of the credit card. */
    val expiryYear: String,

    /** The amount of the WebPay transaction. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** The import process result for the credit card instrument.*/
    val result: ResultEnum,

    val errorMessage: ErrorMessage? = null
) : ModelType

enum class ResultEnum {
    OK, DUP, EXP, ERROR
}

/**
 * The JSON request structure of the List Payment InstrumentsApi endpoint.
 */
@Serializable
data class ListPaymentInstrumentsRequest(
    /** The IDM (Gigya) UID or merchant shopper id of the user. Do NOT use an email address! */
    val uid: String,

    /** The merchant shopper id of the user. */
    val shopperId: String
) : ModelType

/**
 * The JSON response structure of the List Payment InstrumentsApi endpoint.
 */
@Serializable
data class ListPaymentInstrumentsResponse(
    val creditCards: List<CreditCardDetails>,
    val giftCards: List<GiftCardDetails>,
    val payPal: List<PayPalDetails>,
    val paymentAgreements: List<PaymentAgreementDetails>,

    /** Android Pay has been replaced by Google Pay. This property has been retained for backward compatibility and will always be null. */
    val androidPay: JsonObject? = null,
    val googlePay: GooglePayDetails,
    val applePay: ApplePayDetails,
) : ModelType

@Serializable
data class CreditCardDetails(
    /** The credit card payment instrument id. */
    val paymentInstrumentId: String,

    /** The credit card payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String,

    /** The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus,

    /** The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String,

    /** The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String? = null,

    /** A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean? = null,

    /** A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean? = null,

    /** The year of the expiry date of the credit card. */
    val expiryYear: String,

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String,

    /** The credit card scheme. */
    val scheme: String,

    /** The suffix (last 4 digits) of the credit card number. */
    val cardSuffix: String,

    /** A flag to indicate if the CVV of the credit card has been validated. */
    val cvvValidated: Boolean? = null,

    /** The nickname of the credit card instrument in the container. */
    val cardName: String,

    /** A flag to indicate if the credit card is expired. */
    val expired: Boolean? = null,

    /** A flag to indicate if payments with this credit card requires a CVV check. */
    val requiresCVV: Boolean? = null,

    /** The URL of an iframe. This iframe is used to capture a credit card expiry and CVV. */
    val updateURL: String,

    val stepUp: StepUp
) : ModelType

@Serializable
data class GiftCardDetails(
    /** The gift card payment instrument id. */
    val paymentInstrumentId: String,

    /** The gift card payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String,

    /** The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus,

    /** The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String,

    /** The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String? = null,

    /** A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean? = null,

    /** A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean? = null,

    /** The gift card program name. */
    val programName: String,

    /** The suffix (last 4 digits) of the gift card number. */
    val cardSuffix: String,
) : ModelType

@Serializable
data class PayPalDetails(
    /** The paypal payment instrument id. */
    val paymentInstrumentId: String,

    /** The paypal payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String,

    /** The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus,

    /** The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String,

    /** The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String? = null,

    /** A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean? = null,

    /** A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean? = null,

    /** The PayPalApi email id. */
    val payPalId: String,

    /** The PayPalApi customer id. */
    val customerId: String,
) : ModelType

@Serializable
data class PaymentAgreementDetails(
    /** The payment token of the payment agreement. The payment token is a unique identifier for the payment agreement. */
    val paymentToken: String,

    /** The status of the payment agreement in the container. */
    val status: PaymentInstrumentStatus,

    /** The timestamp the payment agreement was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String,

    /** The timestamp the payment agreement was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String? = null,

    /** A flag to indicate if this payment instrument is the primary instrument in the container. Not used for payment agreements. */
    val primary: Boolean? = null,

    /** A flag to indicate if the merchant profile in the container allows the use of this payment agreement. */
    val allowed: Boolean? = null,

    /** The payment agreement type. */
    val type: PaymentAgreementTypeEnum,

    /** The payment agreement payment instrument id that will be used for the charges. */
    val paymentInstrumentId: String,

    /** The credit card scheme. */
    val scheme: String? = null,

    /** The suffix (last 4 digits) of the credit card number. */
    val cardSuffix: String? = null,

    /** The month of the expiry date of the credit card. */
    val expiryMonth: String? = null,

    /** The year of the expiry date of the credit card. */
    val expiryYear: String? = null,

    /** The payment agreement start date and time. The timestamp format is ISO8601. */
    val startDate: String,

    /** The payment agreement end date and time. The timestamp format is ISO8601. */
    val endDate: String,

    /** The payment agreement charge frequency. */
    val chargeFrequency: ChargeFrequencyEnum,

    /** The amount that will be charged at the frequency specified in the payment agreement. */
    @Serializable(with = CurrencySerializer::class)
    val chargeAmount: BigDecimal,

    /** The current charge cycle number. */
    val chargeCycle: Int,

    /** A flag to indicate if the payment agreement is expired. */
    val expired: Boolean,

    /** The URL of the endpoint to use to update the payment agreement. */
    val updateURL: String,

    val stepUp: StepUp? = null
) : ModelType

@Serializable
data class GooglePayDetails(
    /** The google pay payment instrument id. */
    val paymentInstrumentId: String,

    /** The google pay payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String,

    /** The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus,

    /** The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String,

    /** The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String? = null,

    /** A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean? = null,

    /** A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean? = null,

    /** A flag to indicate if the Google Pay token is expired. */
    val expired: Boolean? = null,

    /** This object will only be present if the Google Pay token is expired. Check the 'expired' flag for this status. */
    val stepUp: StepUp? = null
) : ModelType

@Serializable
data class ApplePayDetails(
    /** The apple pay payment instrument id. */
    val paymentInstrumentId: String,

    /** The apple pay payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String,

    /** The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus,

    /** The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String,

    /** The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String? = null,

    /** A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean? = null,

    /** A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean? = null,

    val stepUp: StepUp
) : ModelType

@Serializable
data class StepUp(
    /** The type of the step up action. For apple pay this will be REFRESH_TOKEN. */
    val type: StepUpTypeEnum,

    /** A flag to indicate if this step up (action) is mandatory. */
    val mandatory: Boolean? = null,

    /** The URL of the endpoint to use to update the apple pay token. */
    val url: String
) : ModelType

enum class StepUpTypeEnum {
    REFRESH_TOKEN
}

enum class PaymentInstrumentStatus {
    UNVERIFIED_PERSISTENT,
    VERIFIED
}

enum class ChargeFrequencyEnum {
    WEEKLY,
    FORTNIGHTLY,
    MONTHLY
}

enum class PaymentAgreementTypeEnum {
    RECURRING,
    ADHOC,
    INSTALLMENT
}

@Serializable
data class VerifyPaymentInstrumentsRequest(
    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system.*/
    val clientReference: String,

    /** The step-up token is used to track additional credit card information (eg. CVV and expiry) attached to the payment instrument. It's only valid for a predefined time and if an expired step-up token is used during payment, the payment for that instrument will fail and the user will have to get a new step-up token before retrying the payment. A step-up token is returned in the response of a credit card iframe. This property is currently only required for credit card instruments and only if specific credit card information (eg. CVV and expiry) is required during payment. */
    val paymentInstruments: List<VerifyPaymentInstrumentsRequestInstrument>,

    /** Set to null to skip the fraud check. */
    val fraudPayload: FraudPayload? = null
) : ModelType

@Serializable
data class FraudPayload(
    /** The fraud check provider. */
    val provider: String,

    /** The fraud check version. */
    val version: String,

    /** The fraud check message format. */
    val format: MessageFormat,

    /** The fraud check message format. */
    val responseFormat: MessageFormat,

    /** The fraud check message. */
    val message: String
) : ModelType

@Serializable
data class VerifyPaymentInstrumentsRequestInstrument(
    /** The payment token. */
    val paymentToken: String,

    /**
     * The step-up token is used to track additional credit card information (eg. CVV and expiry) attached to the payment instrument.
     * It's only valid for a predefined time and if an expired step-up token is used during payment,
     * the payment for that instrument will fail and the user will have to get a new step-up token before retrying the payment.
     * A step-up token is returned in the response of a credit card iframe.
     * This property is currently only required for credit card instruments and only if specific credit card information (eg. CVV and expiry) is required during payment.
     */
    val stepUpToken: String
) : ModelType

enum class MessageFormat {
    ZIP_BASE_64_ENCODED,
    XML
}

@Serializable
data class VerifyPaymentInstrumentsSuccessResponse(
    /** Container reference in the transaction logs. This number uniquely identifies the whole/grouped transaction in the container. */
    val transactionReceipt: String,

    /** Not in use. A property that will be used in future for multi-instrument verification.*/
    val partialSuccess: Boolean? = null,

    val fraudResponse: FraudResponse,
    val verifyResponses: List<VerifyResponse>
) : ModelType

@Serializable
data class FraudResponse(
    /** The fraud check client id. Will be null if the fraud check was skipped. */
    val clientId: String,

    /** The fraud check reason code. Will be null if the fraud check was skipped. */
    val reasonCode: String,

    /** The fraud check decision. Will be null if the fraud check was skipped. */
    val decision: String
) : ModelType

@Serializable
data class VerifyResponse(
    /** The payment token. */
    val paymentToken: String,

    /** Container reference in the transaction logs. This number uniquely identifies the transaction in the container. */
    val verifyTransactionRef: String,

    /** The external service code (from eg. Webpay). This property is only included in the response if it is enabled in the consumers API configuration. */
    val externalServiceCode: String,

    /** The external service message (from eg. Webpay). This property is only included in the response if it is enabled in the consumers API configuration. */
    val externalServiceMessage: String
) : ModelType
