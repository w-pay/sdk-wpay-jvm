package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.*

sealed class ApiTokenType {
    data class StringToken(val token: String) : ApiTokenType()
    data class ApiAuthenticatorToken(val authenticator: ApiAuthenticator<HasAccessToken>) :
        ApiTokenType()

    data class NoToken(val token: Unit = Unit) : ApiTokenType()
}

/**
 * Base factory function that can be used to create components needed for an SDK instance.
 *
 * @param options
 * @param token An ApiAuthenticator instance for obtaining an access token, or null.
 */
fun createSDKComponents(
    options: VillageOptions,
    token: ApiTokenType
): Pair<MutableList<RequestHeaderFactory>, ApiAuthenticator<HasAccessToken>> {
    val factories: MutableList<RequestHeaderFactory> = mutableListOf(ApiKeyRequestHeader(options))

    val (authenticator, bearerTokenRequestHeader) = createAuthentication(token)

    bearerTokenRequestHeader?.let { factories.add(it) }

    return Pair(factories, authenticator)
}

fun createAuthentication(
    token: ApiTokenType
): Pair<ApiAuthenticator<HasAccessToken>, BearerTokenRequestHeader<HasAccessToken>?> {
    return when (token) {
        is ApiTokenType.NoToken -> Pair(EmptyTokenApiAuthenticator(), null)
        is ApiTokenType.StringToken -> Pair(
            ProvidedTokenAuthenticator(token.token),
            BearerTokenRequestHeader(token.token)
        )
        is ApiTokenType.ApiAuthenticatorToken -> {
            val bearerTokenRequestHeader = BearerTokenRequestHeader<HasAccessToken>()

            return Pair(
                StoringApiAuthenticator(token.authenticator, bearerTokenRequestHeader),
                bearerTokenRequestHeader
            )
        }
    }
}