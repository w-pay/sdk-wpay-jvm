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

    val transactionId: String

    val clientReference: String?

    val type: PaymentType

    val executionTime: OffsetDateTime

    val status: PaymentStatus

    /*
     * The return type has yet to be defined in the spec.
     */
    val statusDetail: Any

    val revaldReason: String?
}