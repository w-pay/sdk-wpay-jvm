package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.model.HealthCheck

interface VillageAdministrationApiRepository: Configurable {
    fun checkHealth(): ApiResult<HealthCheck>
}