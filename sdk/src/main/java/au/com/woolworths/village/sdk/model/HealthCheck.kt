package au.com.woolworths.village.sdk.model

interface HealthCheck {
    enum class Status {
        SUCCESS
    }

    val result: Status
}