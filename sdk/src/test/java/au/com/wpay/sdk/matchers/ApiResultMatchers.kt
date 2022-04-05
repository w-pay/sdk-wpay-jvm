package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.*
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.types.shouldBeInstanceOf

fun apiError(message: String, cause: Any? = null) =
    object: Matcher<ApiError> {
        override fun test(value: ApiError): MatcherResult {
            value.shouldBeInstanceOf<ApiError>()

            return Matcher.tests(
                MatcherResult.test(::equal, message, value.message),
                MatcherResult.test(::equal, cause, value.cause)
            )
        }
    }

fun httpClientError(message: String, cause: Any? = null) =
    object: Matcher<ApiError> {
        override fun test(value: ApiError): MatcherResult {
            value.shouldBeInstanceOf<HttpClientError>()

            return Matcher.tests(
                MatcherResult.test(::equal, message, value.message),
                MatcherResult.test(::equal, cause, value.cause)
            )
        }
    }

fun httpFailureError(code: Int, headers: Map<String, String>, body: String) =
    object: Matcher<ApiError> {
        override fun test(value: ApiError): MatcherResult {
            value.shouldBeInstanceOf<HttpFailureError>()

            return Matcher.tests(
                MatcherResult.test(::equal, code, value.statusCode),
                MatcherResult.test(::equal, headers, value.responseHeaders),
                MatcherResult.test(::equal, body, value.responseBody)
            )
        }
    }

fun jsonMarshallingError(message: String, cause: Any? = null) =
    object: Matcher<ApiError> {
        override fun test(value: ApiError): MatcherResult {
            value.shouldBeInstanceOf<JsonMarshallingError>()

            return Matcher.tests(
                MatcherResult.test(::equal, message, value.message),
                MatcherResult.test(::equal, cause, value.cause)
            )
        }
    }

fun jsonUnmarshallingError(message: String, cause: Any? = null) =
    object: Matcher<ApiError> {
        override fun test(value: ApiError): MatcherResult {
            value.shouldBeInstanceOf<JsonUnmarshallingError>()

            return Matcher.tests(
                MatcherResult.test(::equal, message, value.message),
                MatcherResult.test(::equal, cause, value.cause)
            )
        }
    }
