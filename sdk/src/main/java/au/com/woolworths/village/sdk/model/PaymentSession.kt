package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime

interface PaymentSession {
    fun paymentSessionId(): String

    fun merchantId(): String

    fun walletId(): String?

    fun expiryTime(): OffsetDateTime

    fun location(): String

    fun merchantInfo(): DynamicPayload

    fun customerInfo(): DynamicPayload?
}