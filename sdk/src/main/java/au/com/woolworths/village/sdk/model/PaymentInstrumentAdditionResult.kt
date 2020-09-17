package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The result of trying to initiate the addition of a new [PaymentInstrument]
 */
interface PaymentInstrumentAdditionResult: Serializable {
    /** The URL of an iframe. This iframe is used to capture a credit card number, expiry and CVV */
    val cardCaptureURL: String

    /** Container reference in the transaction logs. This number uniquely identifies the transaction in the container */
    val transactionRef: String?
}