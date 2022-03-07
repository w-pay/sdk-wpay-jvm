package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable

@Serializable
data class PaymentTransactionType(
    /** The container transaction type to use for credit card instruments. */
    val creditCard: PreauthPurchase? = null,

    /** The container transaction type to use for gift card instruments. */
    val giftCard: Purchase? = null,

    /** The container transaction type to use for paypal instruments. */
    val payPal: Purchase? = null,

    /** The container transaction type to use for google pay instruments. */
    val googlePay: GooglePayTransactionDetail? = null,

    /** The container transaction type to use for apple pay instruments. */
    val applePay: ApplePayTransactionDetail? = null
) : ModelType

@Serializable
data class GooglePayTransactionDetail(
    /** The container transaction type to use for google pay credit card instruments. */
    var creditCard: PreauthPurchase,

    /** The container transaction type to use for google pay debit card instruments. */
    var debitCard: Purchase
) : ModelType

@Serializable
data class ApplePayTransactionDetail(
    /** The container transaction type to use for apple pay credit card instruments. */
    var creditCard: PreauthPurchase,

    /** The container transaction type to use for apple pay debit card instruments. */
    var debitCard: Purchase
) : ModelType

enum class PreauthPurchase {
    PREAUTH,
    PURCHASE
}

enum class Purchase {
    PURCHASE
}
