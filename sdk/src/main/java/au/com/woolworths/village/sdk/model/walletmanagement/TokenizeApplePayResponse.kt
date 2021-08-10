package au.com.woolworths.village.sdk.model.walletmanagement

import java.io.Serializable

interface TokenizeApplePayResponse : Serializable {
    /** The new payment instrument id to be used for payments. */
    val paymentInstrumentId: String

    /** The step-up token to be used for payments. */
    val stepUpToken: String
}
