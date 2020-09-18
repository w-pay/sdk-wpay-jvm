package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.ApiAuthenticator

/**
 * Entry point into the SDK for merchants. It is responsible for managing the relationship between
 * the app concerns, and calling the API.
 *
 * @constructor
 * @param api The API repository to use
 * @param authenticator The [ApiAuthenticator] to use to obtain authorisation needed to access the API
 */
class MerchantVillage<A : Any>(
    private val api: VillageMerchantApiRepository,
    private val authenticator: ApiAuthenticator<A>
): Configurable {
    /**
     * Allows the application to change the host the SDK sends API requests too.
     *
     * This allows the application to read data from an outside source (eg: a QR code) and have
     * the SDK use the same host.
     */
    override fun setHost(host: String) {
        authenticator.setHost(host)
        api.setHost(host)
    }
}