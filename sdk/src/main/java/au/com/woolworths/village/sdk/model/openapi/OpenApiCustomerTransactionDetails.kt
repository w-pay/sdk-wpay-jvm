package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.Basket
import au.com.woolworths.village.sdk.model.CustomerTransactionDetails
import au.com.woolworths.village.sdk.model.CustomerTransactions
import au.com.woolworths.village.sdk.model.TransactionSummary
import au.com.woolworths.village.sdk.openapi.dto.CustomerTransactionDetail
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

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