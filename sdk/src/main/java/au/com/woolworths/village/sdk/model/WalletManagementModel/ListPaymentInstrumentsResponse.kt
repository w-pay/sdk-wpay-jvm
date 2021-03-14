package au.com.woolworths.village.sdk.model

import java.math.BigDecimal

/**
 * The JSON response structure of the List Payment InstrumentsApi endpoint.
 *
 * @category Model
 */
interface ListPaymentInstrumentsResponse {
    val creditCards: List<CreditCardDetails>
    val giftCards: List<GiftCardDetails>
    val payPal: List<PayPalDetails>
    val paymentAgreements: List<PaymentAgreementDetails>

    /* Android Pay has been replaced by Google Pay. This property has been retained for backward compatibility and will always be null. */
    val androidPay: String?
        get() = null;
    val googlePay: GooglePayDetails
    val applePay: ApplePayDetails
}

interface CreditCardDetails {
    /* The credit card payment instrument id. */
    val paymentInstrumentId: String

    /* The credit card payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String

    /* The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus

    /* The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String

    /* The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String

    /* A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean?

    /* A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean?

    /* The year of the expiry date of the credit card. */
    val expiryYear: String

    /* The month of the expiry date of the credit card. */
    val expiryMonth: String

    /* The credit card scheme. */
    val scheme: String

    /* The suffix (last 4 digits) of the credit card number. */
    val cardSuffix: String

    /* A flag to indicate if the CVV of the credit card has been validated. */
    val cvvValidated: Boolean?

    /* The nickname of the credit card instrument in the container. */
    val cardName: String

    /* A flag to indicate if the credit card is expired. */
    val expired: Boolean?

    /* A flag to indicate if payments with this credit card requires a CVV check. */
    val requiresCVV: Boolean?

    /* The URL of an iframe. This iframe is used to capture a credit card expiry and CVV. */
    val updateURL: String

    val stepUp: StepUp
}

interface GiftCardDetails {
    /* The gift card payment instrument id. */
    val paymentInstrumentId: String

    /* The gift card payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String

    /* The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus

    /* The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String

    /* The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String

    /* A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean?

    /* A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean?

    /* The gift card program name. */
    val programName: String

    /* The suffix (last 4 digits) of the gift card number. */
    val cardSuffix: String
}

interface PayPalDetails {
    /* The paypal payment instrument id. */
    val paymentInstrumentId: String

    /* The paypal payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String

    /* The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus

    /* The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String

    /* The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String

    /* A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean?

    /* A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean?

    /* The PayPalApi email id. */
    val payPalId: String

    /* The PayPalApi customer id. */
    val customerId: String
}

interface PaymentAgreementDetails {
    /* The payment token of the payment agreement. The payment token is a unique identifier for the payment agreement. */
    val paymentToken: String

    /* The status of the payment agreement in the container. */
    val status: PaymentInstrumentStatus

    /* The timestamp the payment agreement was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String

    /* The timestamp the payment agreement was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String

    /* A flag to indicate if this payment instrument is the primary instrument in the container. Not used for payment agreements. */
    val primary: Boolean?

    /* A flag to indicate if the merchant profile in the container allows the use of this payment agreement. */
    val allowed: Boolean?

    /* The payment agreement type. */
    val type: PaymentAgreementTypeEnum

    /* The payment agreement payment instrument id that will be used for the charges. */
    val paymentInstrumentId: String

    /* The credit card scheme. */
    val scheme: String?

    /* The suffix (last 4 digits) of the credit card number. */
    val cardSuffix: String?

    /* The month of the expiry date of the credit card. */
    val expiryMonth: String?

    /* The year of the expiry date of the credit card. */
    val expiryYear: String?

    /* The payment agreement start date and time. The timestamp format is ISO8601. */
    val startDate: String

    /* The payment agreement end date and time. The timestamp format is ISO8601. */
    val endDate: String

    /* The payment agreement charge frequency. */
    val chargeFrequency: ChargeFrequencyEnum

    /* The amount that will be charged at the frequency specified in the payment agreement. */
    val chargeAmount: BigDecimal

    /* The current charge cycle number. */
    val chargeCycle: BigDecimal

    /* A flag to indicate if the payment agreement is expired. */
    val expired: String

    /* The URL of the endpoint to use to update the payment agreement. */
    val updateURL: String

    val stepUp: StepUp?
}

interface GooglePayDetails {
    /* The google pay payment instrument id. */
    val paymentInstrumentId: String

    /* The google pay payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String

    /* The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus

    /* The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String

    /* The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String

    /* A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean?

    /* A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean?

    /* A flag to indicate if the Google Pay token is expired. */
    val expired: Boolean?

    /* This object will only be present if the Google Pay token is expired. Check the 'expired' flag for this status. */
    val stepUp: StepUp?
}

interface ApplePayDetails {
    /* The apple pay payment instrument id. */
    val paymentInstrumentId: String

    /* The apple pay payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String

    /* The status of the payment instrument in the container. */
    val status: PaymentInstrumentStatus

    /* The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601. */
    val lastUpdated: String

    /* The timestamp the payment instrument was last used in the container. The timestamp format is ISO8601. Will be null if never used. */
    val lastUsed: String

    /* A flag to indicate if this payment instrument is the primary instrument in the container. */
    val primary: Boolean?

    /* A flag to indicate if the merchant profile in the container allows the use of this payment instrument. */
    val allowed: Boolean?
    val stepUp: StepUp
}

interface StepUp {
    /* The type of the step up action. For apple pay this will be REFRESH_TOKEN. */
    val type: StepUpTypeEnum

    /* A flag to indicate if this step up (action) is mandatory. */
    val mandatory: Boolean?

    /* The URL of the endpoint to use to update the apple pay token. */
    val url: String
}

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