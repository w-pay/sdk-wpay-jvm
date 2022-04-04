package au.com.wpay.sdk.model

import kotlinx.serialization.Serializable

/**
 * Request to update the customer messages to the merchant in the [PaymentSession]
 */
@Serializable
data class CustomerUpdatePaymentSessionRequest(
    /** Payload used to pass customer information back to the merchant */
    val customerInfo: DynamicPayload
) : ModelType
