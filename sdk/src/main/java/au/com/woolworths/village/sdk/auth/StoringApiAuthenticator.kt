package au.com.woolworths.village.sdk.auth

import au.com.woolworths.village.sdk.ApiResult

/**
 * An [ApiAuthenticator] that stores credentials after successfully authenticating.
 *
 * @constructor
 * @param delegate An [ApiAuthenticator] that does the authentication.
 * @param store A [CredentialsStore] for storing the credentials.
 */
class StoringApiAuthenticator<T : Any>(
    private val delegate: ApiAuthenticator<T>,
    private val store: CredentialsStore<T>
): ApiAuthenticator<T> {
    override fun authenticate(): ApiResult<T> {
        val result: ApiResult<T> = delegate.authenticate()

        when(result) {
            is ApiResult.Success -> store.storeCredentials(result.value)
        }

        return result
    }

    override fun setHost(host: String) {
        delegate.setHost(host)
    }
}