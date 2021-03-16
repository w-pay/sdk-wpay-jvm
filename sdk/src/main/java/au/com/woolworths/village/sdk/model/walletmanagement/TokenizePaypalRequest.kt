package au.com.woolworths.village.sdk.model.walletmanagement

/**
 * The JSON request structure of the Tokenize PayPalApi endpoint.
 *
 * @category Model
 */
interface TokenizePaypalRequest {
    /* The PayPalApi nonce that will be used during a PayPalApi payment. */
    val nonce: String

    /* A flag to indicate if this payment instrument has to be set as the primary instrument. */
    val primary: Boolean?
}
