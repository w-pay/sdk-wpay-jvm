package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.auth.HasAccessToken

/**
 * Options unique to using the Customer API operations.
 */
class VillageCustomerOptions(
    apiKey: String,
    baseUrl: String,
    wallet: Wallet? = null,

    /**
     * If given, the wallet ID will be added to the headers.
     *
     * Since the wallet ID identifies the customer it can be overridden with another value by the
     * API gateway which uses the authentication token to identify the customer.
     */
    val walletId: String? = null
) : VillageOptions(apiKey, baseUrl, wallet)

/**
 * Factory function type to give to SDK factory functions to instantiate a new API repository instance.
 */
typealias CustomerApiRepositoryFactory = (
    options: VillageCustomerOptions,
    headers: RequestHeadersFactory,
    authenticator: ApiAuthenticator<HasAccessToken>
) -> VillageCustomerApiRepository

/**
 * Entry point into the SDK for customers.
 *
 * @param options
 * @param token An access token or ApiAuthenticator instance for obtaining an access token, or nothing.
 * @param repository A factory function to create a new API repository instance.
 */
fun createCustomerSDK(
    options: VillageCustomerOptions,
    token: ApiTokenType,
    repository: CustomerApiRepositoryFactory
): VillageCustomerApiRepository {
    val (headers, authenticator) = createSDKComponents(options, token);

    options.walletId?.let { headers.add(WalletIdRequestHeader(it)) }

    return repository(options, RequestHeaderChain(headers), authenticator)
}