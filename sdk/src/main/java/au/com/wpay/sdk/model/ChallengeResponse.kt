package au.com.wpay.sdk.model

import kotlinx.serialization.Serializable

/**
 * The response to a step up challenge
 */
@Serializable
data class ChallengeResponse(
    /** The [PaymentInstrument] id related to the step up challenge. */
    val instrumentId: String,

    /** The type of [ChallengeResponse.token] that has been provided */
    val type: Type,

    /** The value requested by the challenge. */
    val token: String,

    /** An optional reference that could be used for audit tracing */
    val reference: String? = null
) : ModelType {
    /** Possible types of challenges */
    enum class Type(val value: String) {
        STEP_UP("STEP_UP"),
        PASSCODE("PASSCODE"),
        THREEDS("3DS"),
        THREEDS_FRICTIONLESS("3DS-frictionless")
    }
}
