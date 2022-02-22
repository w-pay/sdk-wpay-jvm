package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.model.HealthCheck
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class AdministrationApiTest : DescribeSpec({
    describe("AdministrationApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: AdministrationApi

        beforeEach {
            apiClient = StubApiClient()

            api = AdministrationApi(apiClient.client())
        }

        it("should set request params") {
            apiClient.result = ApiResult.Success(HealthCheck(HealthCheck.Status.SUCCESS))

            api.checkHealth()

            apiClient.request.shouldBe(HttpRequest<Unit>(
                method = HttpRequestMethod.GET,
                url = HttpRequestUrl.String("/")
            ))
        }

        it("should return HealthCheck") {
            val result = HealthCheck(HealthCheck.Status.SUCCESS)
            apiClient.result = ApiResult.Success(result)

            api.checkHealth().toEither().shouldBeRight(result)
        }
    }
})