package au.com.wpay.sdk.model

import kotlinx.serialization.Serializable

/**
 * Request to update a [PaymentSession] for a merchant.
 */
@Serializable
data class MerchantUpdatePaymentSessionRequest(
    /** Payload used to pass merchant information to the customer */
    val merchantInfo: DynamicPayload,

    /** The ID of the associated Payment Request */
    val paymentRequestId: String? = null
) : ModelType
