package au.com.wpay.sdk.model.walletmanagement

import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable

@Serializable
data class AllowedPaymentMethodsGiftCard(
    /** The gift card bin numbers that are allowed for the relevant merchant. This does not indicate sub-bins, ie. exact gift card programs. */
    val allowedBins: List<String>,

    /** This property indicates the status of the gift card service in the container. */
    val serviceStatus: ServiceStatus,

    /** Flag to indicate if the pin is always required for gift card transactions. */
    val pinAlwaysRequired: Boolean? = null
) : ModelType

enum class ServiceStatus {
    ENABLED,
    DISABLED
}

enum class TransactionTypeEnum {
    PREAUTH,
    PURCHASE
}

@Serializable
data class AllowedPaymentMethodsCreditCard(
    /** The allowed networks (schemes) for credit card transactions. */
    val allowedNetworks: List<String>,

    val allowedTransactionTypes: List<TransactionTypeEnum>,

    /** This property indicates the status of the credit card service in the container. */
    val serviceStatus: ServiceStatus
) : ModelType

@Serializable
data class AllowedPaymentMethodsPaypal(
    /** The paypal client token used for configuration and authorization of paypal transactions. */
    val clientToken: String,

    /** This property indicates the status of the paypal service in the container. */
    val serviceStatus: ServiceStatus
) : ModelType

@Serializable
data class AllowedPaymentMethodsGooglePay(
    /** The public key required by the Google Pay wallet. */
    val publicKey: String,

    /** The public key hash required by the Google Pay wallet. */
    val publicKeyHash: String,

    /** The expiry timestamp of the public key hash. The timestamp format is milliseconds since epoch. */
    val publicKeyExpiry: Long,

    /** The merchant id required by the Google Pay wallet. */
    val merchantId: String,

    /** The merchant name required by the Google Pay wallet. */
    val merchantName: String,

    val creditCard: Card,

    val debitCard: Card,

    /** This property indicates the status of the google pay service in the container. */
    val serviceStatus: ServiceStatus
) : ModelType

@Serializable
data class AllowedPaymentMethodsApplePay(
    val creditCard: Card,
    val debitCard: Card,

    /** This property indicates the status of the apple pay service in the container. */
    val serviceStatus: ServiceStatus
) : ModelType

@Serializable
data class Card(
    /** The allowed networks (schemes) for debit card transactions. */
    val allowedNetworks: List<String>,

    /** The allowed transaction types for debit card transactions. */
    val allowedTransactionTypes: List<TransactionTypeEnum>
) : ModelType

@Serializable
data class MerchantProfileResponse(
    val allowedPaymentMethods: AllowedPaymentMethods
) : ModelType

@Serializable
data class AllowedPaymentMethods(
    /** The presence of this object in the response indicates that a gift card is an allowed payment method and instrument in the container for the relevant merchant. */
    val giftCard: AllowedPaymentMethodsGiftCard? = null,

    /** The presence of this object in the response indicates that a credit card is an allowed payment method and instrument in the container for the relevant merchant. */
    val creditCard: AllowedPaymentMethodsCreditCard? = null,

    /** The presence of this object in the response indicates that paypal is an allowed payment method and instrument in the container for the relevant merchant. */
    val paypal: AllowedPaymentMethodsPaypal? = null,

    /** The presence of this object in the response indicates that google pay is an allowed payment method and instrument in the container for the relevant merchant. */
    val googlePay: AllowedPaymentMethodsGooglePay? = null,

    /** The presence of this object in the response indicates that apple pay is an allowed payment method and instrument in the container for the relevant merchant. */
    val applePay: AllowedPaymentMethodsApplePay? = null
) : ModelType
