package au.com.woolworths.village.sdk.model

interface CreatePaymentSessionRequest {
    val location: String

    val merchantInfo: DynamicPayload

    val generateQR: Boolean
        get() = false

    val timeToLivePaymentSession: Int
        get() = 0

    val timeToLiveQR: Int
        get() = 0
}