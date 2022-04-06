package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.wpay.sdk.StubApiClient
import au.com.wpay.sdk.data.aJsonResponse
import au.com.wpay.sdk.data.healthCheckDTO
import au.com.wpay.sdk.kotlinxSerialisationMarshaller
import au.com.wpay.sdk.kotlinxSerialisationUnmarshaller
import au.com.wpay.sdk.matchers.healthCheckFrom
import au.com.wpay.sdk.model.apiResponse
import au.com.wpay.sdk.model.stringData
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

            api = AdministrationApi(
                apiClient.factory(),
                kotlinxSerialisationMarshaller(),
                kotlinxSerialisationUnmarshaller()
            )
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
