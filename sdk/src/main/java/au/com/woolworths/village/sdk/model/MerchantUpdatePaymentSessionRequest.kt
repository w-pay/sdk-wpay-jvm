package au.com.woolworths.village.sdk.model

interface MerchantUpdatePaymentSessionRequest {
    fun merchantInfo(): DynamicPayload

    fun paymentRequestId(): String?
}