package au.com.woolworths.village.sdk.auth

import au.com.woolworths.village.sdk.ApiResult

interface ApiAuthenticator<T : Any> {
    fun authenticate(): ApiResult<T>
}