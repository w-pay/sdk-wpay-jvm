package au.com.woolworths.village.sdk.auth

import au.com.woolworths.village.sdk.ApiResult

class EmptyTokenApiAuthenticator : ApiAuthenticator<HasAccessToken> {
    override fun authenticate(): ApiResult<HasAccessToken> {
        return ApiResult.Success(object: HasAccessToken {
            override val accessToken: String
                get() = ""
        })
    }

    override fun setHost(host: String) {

    }
}