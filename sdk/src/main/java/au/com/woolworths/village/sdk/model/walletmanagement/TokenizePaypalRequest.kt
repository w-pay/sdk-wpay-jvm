package au.com.woolworths.village.sdk.model.walletmanagement

import java.io.Serializable

interface TokenizePaypalRequest : Serializable {
    /* The PayPalApi nonce that will be used during a PayPalApi payment. */
    val nonce: String

    /* A flag to indicate if this payment instrument has to be set as the primary instrument. */
    val primary: Boolean?
}
