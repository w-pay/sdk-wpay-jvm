package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.SdkApiClient
import au.com.woolworths.village.sdk.model.HealthCheck

/**
 * Can be used to perform Administration functions on the API
 */
class AdministrationApi(val client: SdkApiClient) {
    /**
     * Check the health/status of the API
     */
    suspend fun checkHealth(): ApiResult<HealthCheck> =
        client(HealthCheck::class)(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/")
        ))
}