package au.com.woolworths.village.sdk.auth

interface CredentialsStore<T> {
    fun storeCredentials(credentials: T)
}