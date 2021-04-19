package au.com.woolworths.village.sdk

/**
 * Options to configure the SDK
 */
open class VillageOptions(
    /** The API key to identify the SDK to the API. */
    val apiKey: String,

    /**
     * An initial base URL for the API host to use. Is comprised of the the scheme, host, and any
     * context root for the API paths to use eg: https://myawesomeapp.com/api
     */
    val baseUrl: String,

    /**
     * Which wallet to interact with for everything related to payment instruments, preferences, etc.
     * Applies across the SDK. If a different Wallet is needed, instantiate the SDK again.
     *
     * If not specified, will default to [Wallet.MERCHANT].
     */
    val wallet: Wallet? = null
)
