package au.com.woolworths.village.sdk.model.walletmanagement

import java.io.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Import Payment InstrumentsApi endpoint.
 *
 * @category Model
 */
interface ImportPaymentInstrumentsRequest : Serializable {
    /* The IDM (Gigya) UID or merchant shopper id of the user. Do NOT use an email address! */
    val uid: String

    /* The merchant shopper id of the user. */
    val shopperId: String

    val creditCards: List<PaymentInstrumentRequestCreditCard>?
    val payPal: PayPalDetail?
}

interface PayPalDetail : Serializable {
    /* The PayPalApi customer id. */
    val customerId: String

    /* The PayPalApi email id. */
    val payPalId: String

    /* The PayPalApi payment method token. */
    val paymentMethodToken: String
}

interface PaymentInstrumentRequestCreditCard : Serializable {
    /* WebPay reference in the transaction logs. This number uniquely identifies the transaction in WebPay. */
    val transactionRef: String

    /* The WebPay transaction timestamp. The timestamp format is ISO8601. */
    val transactionTimestamp: String

    /* The merchant order number used in the WebPay transaction. */
    val orderNumber: String

    /* The bin (first 6 digits) of the credit card number used in the WebPay transaction. */
    val bin: String

    /* The suffix (last 4 digits) of the credit card number used in the WebPay transaction. */
    val cardSuffix: String

    /* The amount of the WebPay transaction. */
    val amount: BigDecimal
}
