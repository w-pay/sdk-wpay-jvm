package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.CredentialsStore
import au.com.woolworths.village.sdk.auth.IdmTokenDetails
import java.lang.IllegalStateException

class BearerTokenRequestHeader(
    private var token: String? = null
) : RequestHeaderFactory, CredentialsStore<IdmTokenDetails> {
    override fun addHeaders(headers: MutableMap<String, String>) {
        val token = this.token ?: throw IllegalStateException("Must set bearer token before calling API")

        headers["Authorization"] = "Bearer $token"
    }

    override fun storeCredentials(credentials: IdmTokenDetails) {
        token = credentials.accessToken
    }
}