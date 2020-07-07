package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.openapi.dto.MerchantPaymentDetail
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

class OpenApiMerchantPaymentSummaries(
    private val payments: List<au.com.woolworths.village.sdk.openapi.dto.MerchantPaymentSummary>
): MerchantPaymentSummaries {
    override fun payments(): List<MerchantPaymentSummary> {
        return payments.map { OpenApiMerchantPaymentSummary(it) }
    }
}

class OpenApiMerchantPaymentSummary(
    private val payment: au.com.woolworths.village.sdk.openapi.dto.MerchantPaymentSummary
) : MerchantPaymentSummary {
    override fun usesRemaining(): Int? {
        return payment.usesRemaining
    }

    override fun expiryTime(): OffsetDateTime? {
        return payment.expiryTime
    }

    override fun specificWalletId(): String? {
        return payment.specificWalletId
    }

    override fun paymentRequestId(): String {
        return payment.paymentRequestId
    }

    override fun merchantReferenceId(): String {
        return payment.merchantReferenceId
    }

    override fun grossAmount(): BigDecimal {
        return payment.grossAmount
    }
}

class OpenApiMerchantPaymentDetails(
    private val details: MerchantPaymentDetail
) : MerchantPaymentDetails {
    override fun basket(): Basket? {
        return details.basket?.let { OpenApiBasket(it) }
    }

    override fun posPayload(): PosPayload? {
        return details.posPayload?.let { OpenApiPosPayload(it) }
    }

    override fun merchantPayload(): MerchantPayload? {
        return details.merchantPayload?.let { OpenApiMerchantPayload(it) }
    }

    override fun usesRemaining(): Int? {
        return details.usesRemaining
    }

    override fun expiryTime(): OffsetDateTime? {
        return details.expiryTime
    }

    override fun specificWalletId(): String? {
        return details.specificWalletId
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