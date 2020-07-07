package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.model.HeathCheck

interface VillageAdministrationApiRepository: Configurable {
    fun checkHealth(): ApiResult<HeathCheck>
}