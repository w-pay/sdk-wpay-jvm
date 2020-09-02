package au.com.woolworths.village.sdk.model

interface CustomerPaymentRequest: Payment {
    val merchantId: String
    val basket: Basket?
}