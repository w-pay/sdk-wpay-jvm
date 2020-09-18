package au.com.woolworths.village.sdk.auth

/**
 * Abstracts how the SDK stores credentials obtained after authenticating with the API
 *
 * @param T The type of credentials being stored
 */
interface CredentialsStore<T> {
    /**
     * Stores credentials for later use
     *
     * @param credentials The credentials to store
     */
    fun storeCredentials(credentials: T)
}