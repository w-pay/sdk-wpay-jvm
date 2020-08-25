package au.com.woolworths.village.sdk.model

interface CreatePaymentSessionRequest {
    fun location(): String

    fun merchantInfo(): DynamicPayload

    fun generateQR(): Boolean {
        return false
    }

    fun timeToLivePaymentSession(): Int {
        return 0
    }

    fun timeToLiveQR(): Int {
        return 0
    }
}