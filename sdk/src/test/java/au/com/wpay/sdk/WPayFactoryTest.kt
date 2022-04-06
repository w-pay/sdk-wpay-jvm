package au.com.wpay.sdk

import arrow.core.left
import arrow.core.right
import au.com.redcrew.apisdkcreator.httpclient.*
import au.com.wpay.sdk.data.aHttpRequest
import au.com.wpay.sdk.data.aHttpResponse
import au.com.wpay.sdk.matchers.httpClientError
import au.com.wpay.sdk.matchers.httpFailureError
import au.com.wpay.sdk.matchers.jsonUnmarshallingError
import au.com.wpay.sdk.model.HealthCheck
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

fun defaultResult() =
    HttpResult(aHttpRequest<Any>().build(), aHttpResponse<UnstructuredData>().build())

class WPayFactoryTest: DescribeSpec({
    val healthCheck = HealthCheck(HealthCheck.Status.SUCCESS)
    val onSuccess: HttpResultHandler<*, UnstructuredData, HealthCheck> =
        { result ->
            HttpResult(
                request = result.request,
                response = result.response.copyWithBody(
                    body = healthCheck
                )
            ).right()
        }

    describe("createApiClient") {
        val opts = WPayOptions("abc123", "http://localhost")
        lateinit var httpClient: StubHttpClient

        beforeEach {
            httpClient = StubHttpClient()
            httpClient.response = aHttpResponse<UnstructuredData>().build()
        }

        it("should resolve url to baseUrl config") {
            val path = "/foo/bar"

            createApiClient(httpClient.factory(), opts)(
                kotlinxSerialisationMarshaller()(unitEncoder),
                kotlinxSerialisationUnmarshaller()(::jsonPassthrough)(unitDecoder)
            )(
                HttpRequest<Unit>(
                    method = HttpRequestMethod.GET,
                    url = HttpRequestUrl.String(path)
                )
            )

            httpClient.request.url.shouldBe(HttpRequestUrl.String("${opts.baseUrl}$path"))
        }
    }

    describe("resultHandler") {
        it("should return http failure") {
            val code = 500
            val headers = mapOf(
                "content-type" to "application/json"
            )
            val body = "{}"

            val httpResult = resultHandler(onSuccess)(
                HttpResult(
                    request = aHttpRequest<Any>().build(),
                    response = aHttpResponse<UnstructuredData>()
                        .withStatusCode(code)
                        .withHeaders(headers)
                        .withBody(UnstructuredData.String(body))
                        .build()
                )
            )

            val apiResult = httpResult.shouldBeRight()
            apiResult.toEither().shouldBeLeft(httpFailureError(code, headers, body))
        }

        describe("success handler") {
            it("should return result of handler") {
                val httpResult = resultHandler(onSuccess)(defaultResult())

                val apiResult = httpResult.shouldBeRight()
                apiResult.toEither().shouldBeRight(healthCheck)
            }

            it("should return nothing when no body in result") {
                val handler: HttpResultHandler<*, UnstructuredData, Nothing> =
                    { result ->
                        HttpResult(
                            request = result.request,
                            response = result.response.copyWithBody(
                                body = null
                            )
                        ).right()
                    }
                val httpResult = resultHandler(handler)(defaultResult())

                val apiResult = httpResult.shouldBeRight()
                apiResult.toEither().shouldBeRight()
            }

            it("should return error from handler") {
                val message = "Can't unmarshall data"
                val handler: HttpResultHandler<*, UnstructuredData, Nothing> =
                    { SdkError(UNMARSHALLING_ERROR_TYPE, message).left() }
                val httpResult = resultHandler(handler)(defaultResult())

                val apiResult = httpResult.shouldBeRight()
                apiResult.toEither().shouldBeLeft(jsonUnmarshallingError(message))
            }
        }
    }

    describe("apiResult") {
        it("should convert sdk error") {
            val message = "Can't connect to server"
            val error = SdkError(HTTP_CLIENT_ERROR_TYPE, message)

            val result = apiResult<Unit>(error.left())
            result.toEither().shouldBeLeft(httpClientError(message))
        }

        it("should return api result") {
            val value = "OK"
            val success = ApiResult.Success(value)

            val result = apiResult(success.right())
            result.toEither().shouldBeRight(value)
        }
    }
})
