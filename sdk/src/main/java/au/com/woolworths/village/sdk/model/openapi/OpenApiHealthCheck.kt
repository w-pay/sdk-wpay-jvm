package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.HealthCheck
import au.com.woolworths.village.sdk.openapi.dto.HealthCheckResultData
import java.util.*

class OpenApiHealthCheck(
    private val check: HealthCheckResultData
): HealthCheck {
    override fun result(): HealthCheck.Status {
        return HealthCheck.Status.valueOf(check.healthCheck.value.toUpperCase(Locale.ROOT))
    }
}