package au.com.wpay.sdk.model.walletmanagement

import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable

@Serializable
data class TokenizeGooglePayRequest(
    /** The type/schema value from the Google Pay wallet. */
    val instrumentType: String,

    /** The display text returned by the Google Pay wallet. */
    val comment: String,

    /** The "tokenData" payload from the Google Pay wallet. */
    val tokenData: String
) : ModelType

@Serializable
data class TokenizeGooglePayResponse(
    /** The new payment token to be used for payments. The payment token is a unique identifier for the payment instrument.*/
    val paymentToken: String
) : ModelType
