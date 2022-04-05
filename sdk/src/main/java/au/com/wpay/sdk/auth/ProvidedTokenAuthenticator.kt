package au.com.wpay.sdk.auth

import au.com.wpay.sdk.ApiResult

class ProvidedTokenAuthenticator(val token: String) : ApiAuthenticator<HasAccessToken> {
    override fun authenticate(): ApiResult<HasAccessToken> {
        return ApiResult.Success(object : HasAccessToken {
            override val accessToken: String
                get() = token
        })
    }
}
