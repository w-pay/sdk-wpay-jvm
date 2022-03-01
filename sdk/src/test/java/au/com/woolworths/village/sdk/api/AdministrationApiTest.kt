package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.aJsonResponse
import au.com.woolworths.village.sdk.data.healthCheckDTO
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.healthCheckFrom
import au.com.woolworths.village.sdk.model.apiResponse
import au.com.woolworths.village.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class AdministrationApiTest : DescribeSpec({
    describe("AdministrationApi") {
        lateinit var apiClient: StubApiClient
        lateinit var api: AdministrationApi

        beforeEach {
            apiClient = StubApiClient()

            apiClient.response = aJsonResponse<UnstructuredData>()
                .withBody(stringData(apiResponse(healthCheckDTO())))
                .build()

            api = AdministrationApi(apiClient.client(), kotlinxSerialisationUnmarshaller())
        }

        it("should set request params") {
            api.checkHealth()

            apiClient.request.shouldBe(HttpRequest<Unit>(
                method = HttpRequestMethod.GET,
                url = HttpRequestUrl.String("/")
            ))
        }

        it("should return HealthCheck") {
            api.checkHealth().toEither().shouldBeRight(healthCheckFrom(healthCheckDTO()))
        }
    }
})