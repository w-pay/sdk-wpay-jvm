package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.model.TermsAndConditionsAcceptance
import au.com.wpay.sdk.model.TermsAndConditionsAcceptances
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun termsAndConditionsAcceptancesFrom(dto: JsonObject): Matcher<TermsAndConditionsAcceptances> =
    object : Matcher<TermsAndConditionsAcceptances> {
        override fun test(value: TermsAndConditionsAcceptances): MatcherResult =
            MatcherResult.test(
                MatcherResult.forEach<JsonElement, TermsAndConditionsAcceptance>(::termsAndConditionsFrom),
                dto["termsAndConditionsAcceptances"]?.jsonArray,
                value.termsAndConditionsAcceptances
            )
    }

fun termsAndConditionsFrom(dto: JsonElement): Matcher<TermsAndConditionsAcceptance> =
    termsAndConditionsFrom(dto.jsonObject)

fun termsAndConditionsFrom(dto: JsonObject): Matcher<TermsAndConditionsAcceptance> =
    object : Matcher<TermsAndConditionsAcceptance> {
        override fun test(value: TermsAndConditionsAcceptance): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["type"]?.content(), value.type),
                MatcherResult.test(::equal, dto["version"]?.content(), value.version),
                MatcherResult.test(::equal, dto["timestamp"]?.toLong(), value.timestamp)
            )
    }
