package au.com.woolworths.village.sdk.model

/**
 * Detailed information for a single Payment Request
 */
interface CustomerPaymentRequest : Payment {
    /** The ID of the merchant associated with this transaction */
    val merchantId: String

    /** The [Basket] associated to this Payment Request` */
    val basket: Basket?
}