package au.com.wpay.sdk.model.walletmanagement

import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable

@Serializable
data class TokenizePaypalRequest(
    /** The PayPalApi nonce that will be used during a PayPalApi payment. */
    val nonce: String,

    /** A flag to indicate if this payment instrument has to be set as the primary instrument. */
    val primary: Boolean? = null
) : ModelType

/**
 * The JSON response structure of the Tokenize PayPalApi endpoint.
 */
@Serializable
data class TokenizePaypalResponse(
    val payPal: TokenizedPaypalDetails
) : ModelType

@Serializable
data class TokenizedPaypalDetails(
    /** The new payment instrument id to be used for payments.*/
    val paymentInstrumentId: String,

    /** The status of the payment instrument in the container. */
    val status: PaypalStatusEnum,

    /** The timestamp the payment instrument was last updated in the container. The timestamp format is ISO8601.*/
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

enum class PaypalStatusEnum {
    UNVERIFIED_PERSISTENT,
    VERIFIED
}
