package au.com.woolworths.village.sdk.model

interface ChallengeResponse {
    enum class Type {
        STEP_UP,
        PASSCODE
    }

    val instrumentId: String

    val type: Type

    val token: String

    val reference: String?
}