package au.com.woolworths.village.sdk.model

/**
 * The response to a step up challenge
 */
interface ChallengeResponse {
    /** Possible types of challenges */
    enum class Type {
        STEP_UP,
        PASSCODE
    }

    /** The [PaymentInstrument] id related to the step up challenge. */
    val instrumentId: String

    /** The type of [ChallengeResponse.token] that has been provided */
    val type: Type

    /** The value requested by the challenge. */
    val token: String

    /** An optional reference that could be used for audit tracing */
    val reference: String?
}