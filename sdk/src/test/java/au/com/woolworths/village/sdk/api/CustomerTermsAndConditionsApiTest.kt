package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import au.com.woolworths.village.sdk.ApiRequestBody
import au.com.woolworths.village.sdk.Meta
import au.com.woolworths.village.sdk.StubApiClient
import au.com.woolworths.village.sdk.data.aJsonResponse
import au.com.woolworths.village.sdk.data.acceptTermsAndConditionsRequest
import au.com.woolworths.village.sdk.data.termsAndConditionsDTO
import au.com.woolworths.village.sdk.kotlinxSerialisationUnmarshaller
import au.com.woolworths.village.sdk.matchers.termsAndConditionsAcceptancesFrom
import au.com.woolworths.village.sdk.model.apiResponse
import au.com.woolworths.village.sdk.model.stringData
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CustomerTermsAndConditionsApiTest : DescribeSpec({
    describe("Terms and Conditions Apis") {
        lateinit var apiClient: StubApiClient
        lateinit var api: CustomerTermsAndConditionsApi

        beforeEach {
            apiClient = StubApiClient()

            api = CustomerTermsAndConditionsApi(
                apiClient.client(),
                kotlinxSerialisationUnmarshaller()
            )
        }

        describe("get") {
            val termsAndConditionsDTO = termsAndConditionsDTO()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .withBody(stringData(apiResponse(termsAndConditionsDTO)))
                    .build()
            }

            it("should set request params") {
                api.get()

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/termsandconditions/acceptance"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = emptyMap(),
                    body = null
                ))
            }

            it("should set optional parameters") {
                val type = "EVERYDAY_PAY"
                val version = "1.0.0"
                
                api.get(type, version)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String("/instore/customer/termsandconditions/acceptance"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = mapOf(
                        "type" to type,
                        "version" to version
                    ),
                    body = null
                ))
            }

            it("should get terms and conditions acceptances") {
                val result = api.get()

                result.toEither().shouldBeRight(termsAndConditionsAcceptancesFrom(termsAndConditionsDTO))
            }
        }

        describe("accept") {
            val request = acceptTermsAndConditionsRequest()

            beforeEach {
                apiClient.response = aJsonResponse<UnstructuredData>()
                    .build()
            }

            it("should set request params") {
                api.accept(request)

                apiClient.request.shouldBe(HttpRequest(
                    method = HttpRequestMethod.POST,
                    url = HttpRequestUrl.String("/instore/customer/termsandconditions/acceptance"),
                    headers = emptyMap(),
                    pathParams = null,
                    queryParams = null,
                    body = ApiRequestBody(
                        data = request,
                        meta = Meta()
                    )
                ))
            }
        }
    }
})