package au.com.woolworths.village.sdk.model.digitalpay

import java.io.Serializable

interface DigitalPayTransactionType : Serializable {
    /** The container transaction type to use for credit card instruments. */
    val creditCard: PreauthPurchase?

    /** The container transaction type to use for gift card instruments. */
    val giftCard: Purchase?

    /** The container transaction type to use for paypal instruments. */
    val payPal: Purchase?

    /** The container transaction type to use for google pay instruments. */
    val googlePay: GooglePayTransactionDetail?

    /** The container transaction type to use for apple pay instruments. */
    val applePay: ApplePayTransactionDetail?
}

interface GooglePayTransactionDetail : Serializable {
    /** The container transaction type to use for google pay credit card instruments. */
    var creditCard: PreauthPurchase

    /** The container transaction type to use for google pay debit card instruments. */
    var debitCard: Purchase
}

interface ApplePayTransactionDetail : Serializable {
    /** The container transaction type to use for apple pay credit card instruments. */
    var creditCard: PreauthPurchase

    /** The container transaction type to use for apple pay debit card instruments. */
    var debitCard: Purchase
}


enum class PreauthPurchase {
    PREAUTH,
    PURCHASE
}

enum class Purchase {
    PURCHASE
}
