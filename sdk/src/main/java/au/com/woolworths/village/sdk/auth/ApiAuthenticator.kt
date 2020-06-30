package au.com.woolworths.village.sdk.auth

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.Configurable

interface ApiAuthenticator<T : Any>: Configurable {
    fun authenticate(): ApiResult<T>
}