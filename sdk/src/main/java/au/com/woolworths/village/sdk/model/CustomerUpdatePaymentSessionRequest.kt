package au.com.woolworths.village.sdk.model

/**
 * Request to update the customer messages to the merchant in the [PaymentSession]
 */
interface CustomerUpdatePaymentSessionRequest {
    /** Payload used to pass customer information back to the merchant */
    val customerInfo: DynamicPayload
}