package au.com.woolworths.village.sdk.model.walletmanagement

import java.io.Serializable

interface TokenizeGooglePayResponse : Serializable {
    /** The new payment token to be used for payments. The payment token is a unique identifier for the payment instrument.*/
    val paymentToken: String
}
