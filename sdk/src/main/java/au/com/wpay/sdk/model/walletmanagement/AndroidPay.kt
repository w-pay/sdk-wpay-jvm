package au.com.wpay.sdk.model.walletmanagement

import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable

@Serializable
data class TokenizeAndroidPayRequest(
    /** The "encryptedMessage" value from the Android Pay wallet.*/
    val encryptedMessage: String,

    /** The "ephemeralPublicKey" value from the Android Pay wallet. */
    val ephemeralPublicKey: String,

    /** The "tag" value from the Android Pay wallet. */
    val tag: String,

    /** The "publicKeyHash" value from the merchant profile response. */
    val publicKeyHash: String,

    /** The type/schema value from the Android Pay wallet. */
    val instrumentType: String,

    /** A flag to indicate if this payment instrument has to be set as the primary instrument. */
    val primary: Boolean? = null,

    /** The display text returned by the Android Pay wallet. */
    val comment: String
) : ModelType

@Serializable
data class TokenizeAndroidPayResponse(
    /** The new payment instrument id to be used for payments. */
    val paymentInstrumentId: String,

    /** The step-up token to be used for payments. */
    val stepUpToken: String
) : ModelType
