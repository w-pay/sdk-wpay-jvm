package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.CredentialsStore
import au.com.woolworths.village.sdk.auth.HasAccessToken

/**
 * Adds a 'Bearer' token to the request.
 */
class BearerTokenRequestHeader<T : HasAccessToken>(
    private var token: String? = null
) : RequestHeaderFactory, CredentialsStore<T> {
    override fun addHeaders(headers: MutableMap<String, String>) {
        val token =
            this.token ?: throw IllegalStateException("Must set bearer token before calling API")

        headers["Authorization"] = "Bearer $token"
    }

    override fun storeCredentials(credentials: T) {
        token = credentials.accessToken
    }
}