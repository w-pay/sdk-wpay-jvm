package au.com.woolworths.village.sdk

/**
 * The different wallets the SDK can interact with via the API
 */
enum class Wallet {
    /** Indicates the wallet has been registered with a merchant */
    MERCHANT,

    /** Everyday pay */
    EVERYDAY_PAY
}