package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime
import java.io.Serializable

interface MerchantPayments: Serializable {
}

interface MerchantPaymentSummaries: MerchantPayments {
    fun payments(): List<MerchantPaymentSummary>
}

interface MerchantPaymentSummary: Payment {
    fun usesRemaining(): Int?

    fun expiryTime(): OffsetDateTime?

    fun specificWalletId(): String?
}

interface MerchantPaymentDetails: MerchantPaymentSummary {
    fun basket(): Basket?

    fun posPayload(): PosPayload?

    fun merchantPayload(): MerchantPayload?
}