package au.com.woolworths.village.sdk.model

interface MerchantUpdatePaymentSessionRequest {
    val merchantInfo: DynamicPayload

    val paymentRequestId: String?
}