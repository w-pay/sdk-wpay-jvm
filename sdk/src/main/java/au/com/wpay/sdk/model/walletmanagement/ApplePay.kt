package au.com.wpay.sdk.model.walletmanagement

import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable

@Serializable
data class TokenizeApplePayRequest(
    /** The "data" value from the Apple Pay wallet. */
    val data: String,

    /** The "ephemeralPublicKey" value from the Apple Pay wallet. */
    val ephemeralPublicKey: String,

    /** The "publicKeyHash" value from the Apple Pay wallet. */
    val publicKeyHash: String,

    /** The "transactionId" value from the Apple Pay wallet. */
    val transactionId: String,

    /** The "signature" value from the Apple Pay wallet. */
    val signature: String,

    /** The "version" value from the Apple Pay wallet. */
    val version: String,

    /** The type/schema value from the Apple Pay wallet. */
    val instrumentType: String,

    /** A flag to indicate if this payment instrument has to be set as the primary instrument. */
    val primary: Boolean? = null,

    /** The display text returned by the Apple Pay wallet. */
    val comment: String,

    /** The "applicationData" value from the Apple Pay wallet. */
    val applicationData: String,
) : ModelType

@Serializable
data class TokenizeApplePayResponse(
    /** The new payment instrument id to be used for payments. */
    val paymentInstrumentId: String,

    /** The step-up token to be used for payments. */
    val stepUpToken: String
) : ModelType
