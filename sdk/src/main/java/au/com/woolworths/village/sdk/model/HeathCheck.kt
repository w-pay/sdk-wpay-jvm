package au.com.woolworths.village.sdk.model

interface HeathCheck {
    enum class Status {
        SUCCESS
    }

    fun result(): Status
}