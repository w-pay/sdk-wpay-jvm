package au.com.woolworths.village.sdk.model

interface NewPaymentRequest {
    fun merchantReferenceId(): String
    fun generateQR(): Boolean
    fun maxUses(): Int?
    fun timeToLivePayment(): Int?
    fun timeToLiveQR(): Int?
    fun specificWalletId(): String?
    fun basket(): Basket?
    fun posPayload(): PosPayload?
    fun merchantPayload(): MerchantPayload?
}