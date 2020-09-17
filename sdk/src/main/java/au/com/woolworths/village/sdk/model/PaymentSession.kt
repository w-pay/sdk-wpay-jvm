package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime

/**
 * A session between a customer and a merchant
 */
interface PaymentSession {
    /** The ID of the [PaymentSession] */
    val paymentSessionId: String

    /** The ID of the associated Payment Request */
    val paymentRequestId: String?

    /** The ID of the merchant initiating the [PaymentSession] */
    val merchantId: String

    /** The ID of the customers wallet */
    val walletId: String?

    /** The timestamp of when the payment session will expire and become unusable */
    val expiryTime: OffsetDateTime

    /** The location of the [PaymentSession] (used to group payment sessions) */
    val location: String

    /** Payload used to pass merchant information to the customer */
    val merchantInfo: DynamicPayload

    /** Payload used to pass customer information back to the merchant */
    val customerInfo: DynamicPayload?
}