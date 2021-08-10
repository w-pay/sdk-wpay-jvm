package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Request to update the customer messages to the merchant in the [PaymentSession]
 */
interface CustomerUpdatePaymentSessionRequest : Serializable {
    /** Payload used to pass customer information back to the merchant */
    val customerInfo: DynamicPayload
}