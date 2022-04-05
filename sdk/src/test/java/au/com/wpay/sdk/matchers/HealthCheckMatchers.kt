package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.model.HealthCheck
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonObject

fun healthCheckFrom(dto: JsonObject): Matcher<HealthCheck> =
    object: Matcher<HealthCheck> {
        override fun test(value: HealthCheck): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["healthCheck"].toUpperCase(), value.result.toString()),
            )
    }
