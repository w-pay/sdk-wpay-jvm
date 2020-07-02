package au.com.woolworths.village.sdk.model

interface CustomerPaymentRequest: Payment {
    fun merchantId(): String
    fun basket(): Basket?
}