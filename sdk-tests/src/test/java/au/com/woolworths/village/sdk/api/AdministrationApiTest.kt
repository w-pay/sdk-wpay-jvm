package au.com.woolworths.village.sdk.api

import com.google.common.truth.Truth.assertThat
import org.junit.BeforeClass
import org.junit.Test

import au.com.woolworths.village.sdk.client.Configuration
import au.com.woolworths.village.sdk.dto.HealthCheckResult
import au.com.woolworths.village.sdk.dto.HealthCheckResultData

class AdministrationApiTest {
    companion object {
        @BeforeClass
        @JvmStatic
        fun setupApiClient() {
            Configuration.getDefaultApiClient().basePath = "http://localhost:8080"
        }
    }

    @Test
    fun checkHealthTest() {
        val api = AdministrationApi()
        val result: HealthCheckResult = api.checkHealth()

        assertThat(result.data.healthCheck).isEqualTo(HealthCheckResultData.HealthCheckEnum.SUCCESS)
        assertThat(result.meta).isNotNull()
    }
}