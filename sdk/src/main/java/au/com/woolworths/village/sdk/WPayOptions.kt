package au.com.woolworths.village.sdk

val DEFAULT_API_TOKEN_TYPE = ApiTokenType.NoToken()
val DEFAULT_WALLET = Wallet.MERCHANT

/**
 * Options to configure the SDK
 */
open class WPayOptions(
    /** The API key to identify the SDK to the API. */
    val apiKey: String,

    /**
     * An initial base URL for the API host to use. Is comprised of the the scheme, host, and any
     * context root for the API paths to use eg: https://myawesomeapp.com/api
     */
    val baseUrl: String,

    /**
     * The access token for the API.
     *
     * If not provided, defaults to [ApiTokenType.NoToken]
     */
    val accessToken: ApiTokenType = DEFAULT_API_TOKEN_TYPE,

    /**
     * Which wallet to interact with for everything related to payment instruments, preferences, etc.
     * Applies across the SDK. If a different Wallet is needed, instantiate the SDK again.
     *
     * If not specified, will default to [Wallet.MERCHANT].
     */
    val wallet: Wallet = DEFAULT_WALLET
) {
    constructor(
        apiKey: String,
        baseUrl: String,
        wallet: Wallet
    ) : this(apiKey, baseUrl, DEFAULT_API_TOKEN_TYPE, wallet)
}
