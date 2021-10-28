package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.auth.HasAccessToken

/**
 * Options unique to using the Merchant API operations.
 */
open class VillageMerchantOptions(
    apiKey: String,
    baseUrl: String,
    wallet: Wallet? = null,

    /**
     * If given, the merchant ID will be added to the headers.
     *
     * Since the merchant ID identifies the merchant it can be overridden with another value by the
     * API gateway which uses the authentication token to identify the merchant.
     */
    val merchantId: String? = null
) : VillageOptions(apiKey, baseUrl, wallet)

/**
 * Factory function type to give to SDK factory functions to instantiate a new API repository instance.
 */
typealias MerchantApiRepositoryFactory = (
    options: VillageMerchantOptions,
    headers: RequestHeadersFactory,
    authenticator: ApiAuthenticator<HasAccessToken>
) -> VillageMerchantApiRepository

/**
 * Entry point into the SDK for merchants.
 *
 * @param options
 * @param token An access token or ApiAuthenticator instance for obtaining an access token, or nothing.
 * @param repository A factory function to create a new API repository instance.
 */
fun createMerchantSDK(
    options: VillageMerchantOptions,
    token: ApiTokenType,
    repository: MerchantApiRepositoryFactory
): VillageMerchantApiRepository {
    val (headers, authenticator) = createSDKComponents(options, token);

    options.merchantId?.let { headers.add(WalletIdRequestHeader(it)) }

    return repository(options, RequestHeaderChain(headers), authenticator)
}