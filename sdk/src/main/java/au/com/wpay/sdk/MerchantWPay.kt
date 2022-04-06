package au.com.wpay.sdk

/**
 * Options unique to using the Merchant API operations.
 */
open class WPayMerchantOptions(
    apiKey: String,
    baseUrl: String,
    accessToken: ApiTokenType,
    wallet: Wallet,

    /**
     * If given, the merchant ID will be added to the headers.
     *
     * Since the merchant ID identifies the merchant it can be overridden with another value by the
     * API gateway which uses the authentication token to identify the merchant.
     */
    val merchantId: String? = null
) : WPayOptions(apiKey, baseUrl, accessToken, wallet) {
    constructor(
        apiKey: String,
        baseUrl: String
    ): this(apiKey, baseUrl, DEFAULT_API_TOKEN_TYPE, DEFAULT_WALLET, null)

    constructor(
        apiKey: String,
        baseUrl: String,
        merchantId: String?
    ): this(apiKey, baseUrl, DEFAULT_API_TOKEN_TYPE, DEFAULT_WALLET, merchantId)

    constructor(
        apiKey: String,
        baseUrl: String,
        accessToken: ApiTokenType,
        merchantId: String?
    ): this(apiKey, baseUrl, accessToken, DEFAULT_WALLET, merchantId)
}

/**
 * Entry point into the SDK for merchants.
 *
 * @param httpClient A factory for creating an HTTP Client.
 * @param options
 */
fun createMerchantSDK(
    httpClient: HttpClientFactory,
    options: WPayMerchantOptions
): WPayMerchantApi =
    WPayMerchantApi(
        createApiClient(httpClient, options),
        kotlinxSerialisationMarshaller(),
        kotlinxSerialisationUnmarshaller(),
        options
    )
