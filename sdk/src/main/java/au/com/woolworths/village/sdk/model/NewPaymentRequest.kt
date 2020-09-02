package au.com.woolworths.village.sdk.model

import java.math.BigDecimal

interface NewPaymentRequest {
    val merchantReferenceId: String
    val grossAmount: BigDecimal
    val generateQR: Boolean
    val maxUses: Int?
    val timeToLivePayment: Int?
    val timeToLiveQR: Int?
    val specificWalletId: String?
    val basket: Basket?
    val posPayload: PosPayload?
    val merchantPayload: MerchantPayload?
}