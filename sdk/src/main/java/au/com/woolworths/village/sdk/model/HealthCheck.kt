package au.com.woolworths.village.sdk.model

interface HealthCheck {
    enum class Status {
        SUCCESS
    }

    fun result(): Status
}