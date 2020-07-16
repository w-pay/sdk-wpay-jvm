package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.*
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal
import java.util.*

class OpenApiMerchantTransactionSummaries(
    private val transactions: List<au.com.woolworths.village.sdk.openapi.dto.MerchantTransactionSummary>
): MerchantTransactionSummaries {
    override fun transactions(): List<MerchantTransactionSummary> {
        return transactions.map { OpenApiMerchantTransactionSummary(it) }
    }
}

class OpenApiMerchantTransactionSummary(
    private val summary: au.com.woolworths.village.sdk.openapi.dto.MerchantTransactionSummary
): MerchantTransactionSummary {
    override fun walletId(): String {
        return summary.walletId
    }

    override fun transactionId(): String {
        return summary.transactionId
    }

    override fun type(): TransactionSummary.PaymentType {
        return TransactionSummary.PaymentType.valueOf(summary.type.value.toUpperCase(Locale.ROOT))
    }

    override fun executionTime(): OffsetDateTime {
        return summary.executionTime
    }

    override fun status(): TransactionSummary.PaymentStatus {
        return TransactionSummary.PaymentStatus.valueOf(summary.status.value.toUpperCase(Locale.ROOT))
    }

    override fun statusDetail(): Any {
        return Any()
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

class OpenApiMerchantTransactionDetails(
    private val details: au.com.woolworths.village.sdk.openapi.dto.MerchantTransactionDetail
): MerchantTransactionDetails {
    override fun basket(): Basket? {
        return details.basket?.let { OpenApiBasket(it) }
    }

    override fun posPayload(): PosPayload? {
        return details.posPayload?.let { OpenApiPosPayload(it) }
    }

    override fun merchantPayload(): MerchantPayload? {
        return details.merchantPayload?.let { OpenApiMerchantPayload(it) }
    }

    override fun walletId(): String {
        return details.walletId
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

    override fun statusDetail(): Any {
        return Any()
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