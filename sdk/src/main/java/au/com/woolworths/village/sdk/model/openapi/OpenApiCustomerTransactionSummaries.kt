package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.CustomerTransactionSummaries
import au.com.woolworths.village.sdk.model.CustomerTransactions
import au.com.woolworths.village.sdk.model.TransactionSummary
import au.com.woolworths.village.sdk.openapi.dto.CustomerTransactionSummary
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

class OpenApiCustomerTransactionSummaries(
    private val transactions: List<CustomerTransactionSummary>
): CustomerTransactionSummaries {
    override fun transactions(): List<au.com.woolworths.village.sdk.model.CustomerTransactionSummary> {
        return transactions.map { OpenApiCustomerTransactionSummary(it) }
    }
}

class OpenApiCustomerTransactionSummary(
    private val summary: CustomerTransactionSummary
): au.com.woolworths.village.sdk.model.CustomerTransactionSummary {
    override fun merchantId(): String {
        return summary.merchantId
    }

    override fun instruments(): List<CustomerTransactions.UsedPaymentInstrument> {
        return summary.instruments.map { OpenApiUsedPaymentInstrument(it) }
    }

    override fun transactionId(): String {
        return summary.transactionId
    }

    override fun type(): TransactionSummary.PaymentType {
        return TransactionSummary.PaymentType.valueOf(summary.type.value)
    }

    override fun executionTime(): OffsetDateTime {
        return summary.executionTime
    }

    override fun status(): TransactionSummary.PaymentStatus {
        return TransactionSummary.PaymentStatus.valueOf(summary.status.value)
    }

    override fun refundReason(): String? {
        return summary.refundReason
    }

    override fun paymentRequestId(): String {
        return summary.paymentRequestId
    }

    override fun merchantReferenceId(): String {
        return summary.merchantReferenceId
    }

    override fun grossAmount(): BigDecimal {
        return summary.grossAmount
    }
}

