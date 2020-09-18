package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.model.HealthCheck

/**
 * Can be used to perform Administration functions on the API
 */
interface VillageAdministrationApiRepository: Configurable {
    /**
     * Check the health/status of the API
     */
    fun checkHealth(): ApiResult<HealthCheck>
}