package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime

interface TransactionSummary: Payment {
    enum class PaymentType {
        PAYMENT,
        REFUND
    }

    enum class PaymentStatus {
        PROCESSING,
        APPROVED,
        REJECTED,
    }

    fun transactionId(): String

    fun type(): PaymentType

    fun executionTime(): OffsetDateTime

    fun status(): PaymentStatus

    fun refundReason(): String?
}