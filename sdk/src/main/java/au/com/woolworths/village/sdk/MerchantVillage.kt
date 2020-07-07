package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.ApiAuthenticator

class MerchantVillage<A : Any>(
    private val api: VillageMerchantApiRepository,
    private val authenticator: ApiAuthenticator<A>
): Configurable {
    override fun setHost(host: String) {
        authenticator.setHost(host)
        api.setHost(host)
    }
}