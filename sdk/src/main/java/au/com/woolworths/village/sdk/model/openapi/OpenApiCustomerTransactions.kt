package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.openapi.dto.CustomerTransactionDetail
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

class OpenApiCustomerTransactionDetails(
    private val details: CustomerTransactionDetail
): CustomerTransactionDetails {
    override fun basket(): Basket? {
        return details.basket?.let { OpenApiBasket(it) }
    }

    override fun merchantId(): String {
        return details.merchantId
    }

    override fun instruments(): List<CustomerTransactions.UsedPaymentInstrument> {
        return details.instruments.map { OpenApiUsedPaymentInstrument(it) }
    }

    override fun transactionId(): String {
        return details.transactionId
    }

    override fun type(): TransactionSummary.PaymentType {
        return TransactionSummary.PaymentType.valueOf(details.type.value)
    }

    override fun executionTime(): OffsetDateTime {
        return details.executionTime
    }

    override fun status(): TransactionSummary.PaymentStatus {
        return TransactionSummary.PaymentStatus.valueOf(details.status.value)
    }

    override fun refundReason(): String? {
        return details.refundReason
    }

    override fun paymentRequestId(): String {
        return details.paymentRequestId
    }

    override fun merchantReferenceId(): String {
        return details.merchantReferenceId
    }

    override fun grossAmount(): BigDecimal {
        return details.grossAmount
    }
}