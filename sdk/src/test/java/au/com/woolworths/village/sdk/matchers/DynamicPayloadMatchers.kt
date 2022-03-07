package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.DynamicPayload
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

fun dynamicPayloadFrom(dto: JsonElement): Matcher<DynamicPayload> =
    dynamicPayloadFrom(dto.jsonObject)

fun dynamicPayloadFrom(dto: JsonObject): Matcher<DynamicPayload> =
    object : Matcher<DynamicPayload> {
        override fun test(value: DynamicPayload): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["schemaId"]?.content(), value.schemaId),
                MatcherResult.test(::jsonObjectFrom, dto["payload"]?.jsonObject, value.payload)
            )
    }