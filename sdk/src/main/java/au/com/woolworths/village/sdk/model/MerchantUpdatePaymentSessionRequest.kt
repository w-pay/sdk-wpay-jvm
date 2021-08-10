package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * Request to update a [PaymentSession] for a merchant.
 */
interface MerchantUpdatePaymentSessionRequest : Serializable {
    /** Payload used to pass merchant information to the customer */
    val merchantInfo: DynamicPayload

    /** The ID of the associated Payment Request */
    val paymentRequestId: String?
}