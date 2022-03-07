package au.com.woolworths.village.sdk.matchers

import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonObject

fun mapFrom(dto: JsonObject): Matcher<Map<String, String>> =
    object : Matcher<Map<String, String>> {
        override fun test(value: Map<String, String>): MatcherResult =
            Matcher.tests(value.map {
                entry -> MatcherResult.test(::equal, dto[entry.key]?.content(), entry.value)
            })
    }