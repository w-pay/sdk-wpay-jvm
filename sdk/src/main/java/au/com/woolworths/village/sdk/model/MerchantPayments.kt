package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime
import java.io.Serializable

interface MerchantPayments: Serializable {
}

interface MerchantPaymentSummaries: MerchantPayments {
    val payments: List<MerchantPaymentSummary>
}

interface MerchantPaymentSummary: Payment {
    val usesRemaining: Int?

    val expiryTime: OffsetDateTime?

    val specificWalletId: String?
}

interface MerchantPaymentDetails: MerchantPaymentSummary {
    val basket: Basket?

    val posPayload: PosPayload?

    val merchantPayload: MerchantPayload?
}