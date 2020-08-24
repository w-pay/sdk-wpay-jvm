package au.com.woolworths.village.sdk.model

interface ChallengeResponse {
    enum class Type {
        STEP_UP,
        PASSCODE
    }

    fun instrumentId(): String

    fun type(): Type

    fun token(): String

    fun reference(): String?
}