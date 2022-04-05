package au.com.wpay.sdk

/**
 * Options unique to using the Customer API operations.
 */
open class WPayCustomerOptions(
    apiKey: String,
    baseUrl: String,
    accessToken: ApiTokenType,
    wallet: Wallet,

    /**
     * If given, the wallet ID will be added to the headers.
     *
     * Since the wallet ID identifies the customer it can be overridden with another value by the
     * API gateway which uses the authentication token to identify the customer.
     */
    val walletId: String? = null
) : WPayOptions(apiKey, baseUrl, accessToken, wallet) {
    constructor(
        apiKey: String,
        baseUrl: String
    ): this(apiKey, baseUrl, DEFAULT_API_TOKEN_TYPE, DEFAULT_WALLET, null)

    constructor(
        apiKey: String,
        baseUrl: String,
        walletId: String?
    ): this(apiKey, baseUrl, DEFAULT_API_TOKEN_TYPE, DEFAULT_WALLET, walletId)

    constructor(
        apiKey: String,
        baseUrl: String,
        accessToken: ApiTokenType,
        walletId: String?
    ): this(apiKey, baseUrl, accessToken, DEFAULT_WALLET, walletId)
}

/**
 * Entry point into the SDK for customers.
 *
 * @param httpClient A factory for creating an HTTP Client.
 * @param options
 */
fun createCustomerSDK(
    httpClient: HttpClientFactory,
    options: WPayCustomerOptions,
): WPayCustomerApi =
    WPayCustomerApi(
        createApiClient(httpClient, options),
        kotlinxSerialisationUnmarshaller(),
        options
    )
