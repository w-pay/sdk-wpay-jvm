package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime

interface PaymentSession {
    val paymentSessionId: String

    val paymentRequestId: String?

    val merchantId: String

    val walletId: String?

    val expiryTime: OffsetDateTime

    val location: String

    val merchantInfo: DynamicPayload

    val customerInfo: DynamicPayload?
}