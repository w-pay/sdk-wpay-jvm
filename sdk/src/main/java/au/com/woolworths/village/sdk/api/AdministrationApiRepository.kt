package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.HealthCheck

/**
 * Can be used to perform Administration functions on the API
 */
interface AdministrationApiRepository {
    /**
     * Check the health/status of the API
     */
    fun checkHealth(): ApiResult<HealthCheck>
}