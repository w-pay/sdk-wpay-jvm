package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.HeathCheck
import au.com.woolworths.village.sdk.openapi.dto.HealthCheckResultData
import java.util.*

class OpenApiHealthCheck(
    private val check: HealthCheckResultData
): HeathCheck {
    override fun result(): HeathCheck.Status {
        return HeathCheck.Status.valueOf(check.healthCheck.value.toUpperCase(Locale.ROOT))
    }
}