package au.com.wpay.sdk.model

import au.com.wpay.sdk.ISODateSerializer
import kotlinx.serialization.Serializable
import org.threeten.bp.OffsetDateTime

/**
 * A session between a customer and a merchant
 */
@Serializable
data class PaymentSession(
    /** The ID of the [PaymentSession] */
    val paymentSessionId: String,

    /** The ID of the associated Payment Request */
    val paymentRequestId: String? = null,

    /** The ID of the merchant initiating the [PaymentSession] */
    val merchantId: String,

    /** The ID of the customers wallet */
    val walletId: String? = null,

    /** The timestamp of when the payment session will expire and become unusable */
    @Serializable(with = ISODateSerializer::class)
    val expiryTime: OffsetDateTime,

    /** The location of the [PaymentSession] (used to group payment sessions) */
    val location: String,

    /** Payload used to pass merchant information to the customer */
    val merchantInfo: DynamicPayload,

    /** Payload used to pass customer information back to the merchant */
    val customerInfo: DynamicPayload? = null
) : ModelType
