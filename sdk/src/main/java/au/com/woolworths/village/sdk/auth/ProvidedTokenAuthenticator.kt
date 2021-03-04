package au.com.woolworths.village.sdk.auth

import au.com.woolworths.village.sdk.ApiResult

class ProvidedTokenAuthenticator(val token: String) : ApiAuthenticator<HasAccessToken> {
    override fun authenticate(): ApiResult<HasAccessToken> {
        return ApiResult.Success(object: HasAccessToken {
            override val accessToken: String
                get() = token
        })
    }

    override fun setHost(host: String) {

    }
}