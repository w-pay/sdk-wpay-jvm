package au.com.woolworths.village.sdk.openapi.api

import au.com.woolworths.village.sdk.openapi.client.Configuration
import au.com.woolworths.village.sdk.openapi.dto.HealthCheckResult
import au.com.woolworths.village.sdk.openapi.dto.HealthCheckResultData

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.BeforeClass
import org.junit.Test

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

        assertThat(result.data.healthCheck, equalTo(HealthCheckResultData.HealthCheckEnum.SUCCESS))
        assertThat(result.meta, not(nullValue()))
    }
}