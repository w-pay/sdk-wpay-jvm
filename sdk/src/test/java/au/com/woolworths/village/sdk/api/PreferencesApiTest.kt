package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.ApiRequestBody
import au.com.woolworths.village.sdk.Meta
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.aJsonResponse
import au.com.woolworths.village.sdk.data.customerPreferences
import au.com.woolworths.village.sdk.data.preferencesDTO
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.customerPreferencesFrom
import au.com.woolworths.village.sdk.matchers.preferencesFrom
import au.com.woolworths.village.sdk.model.apiResponse
import au.com.woolworths.village.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PreferencesApiTest : DescribeSpec({
    lateinit var apiClient: StubApiClient

    beforeEach {
        apiClient = StubApiClient()
    }

    describe("CustomerPreferencesApi") {
        lateinit var api: CustomerPreferencesApi

        beforeEach {
            api = CustomerPreferencesApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("getPreferences") {
            val preferencesDTO = preferencesDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(preferencesDTO)))
                    .build()
            }

            it("should set request params") {
                api.get()

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/preferences")
                ))
            }

            it("should get preferences") {
                val result = api.get()

                result.toEither().shouldBeRight(customerPreferencesFrom(preferencesDTO))
            }
        }

        describe("setPreferences") {
            val preferences = customerPreferences()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.set(preferences)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/customer/preferences"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = preferences,
                        meta = Meta()
                    )
                ))
            }
        }
    }

    describe("MerchantPreferencesApi") {
        lateinit var api: MerchantPreferencesApi

        beforeEach {
            api = MerchantPreferencesApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("getPreferences") {
            val preferencesDTO = preferencesDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(preferencesDTO)))
                    .build()
            }

            it("should set request params") {
                api.get()

                apiClient.request.shouldBe(HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/merchant/preferences")
                ))
            }

            it("should get preferences") {
                val result = api.get()

                result.toEither().shouldBeRight(preferencesFrom(preferencesDTO))
            }
        }

        describe("setPreferences") {
            val preferences: MerchantPreferences = emptyMap()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.set(preferences)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/merchant/preferences"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = preferences,
                        meta = Meta()
                    )
                ))
            }
        }
    }
})