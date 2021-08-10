package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The JSON response structure of the Tokenize PayPalApi endpoint.
 */
interface TokenizePaypalResponse : Serializable {
    val payPal: PaypalDetails
}

interface PaypalDetails : Serializable {
    /* The new payment instrument id to be used for payments.*/
    val paymentInstrumentId: String

    /* The status of the payment instrument in the container. */
    val status: PaypalStatusEnum

    /* The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601.*/
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

enum class PaypalStatusEnum {
    UNVERIFIED_PERSISTENT,
    VERIFIED
}
