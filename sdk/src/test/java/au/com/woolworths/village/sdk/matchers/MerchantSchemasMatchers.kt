package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.MerchantSchema
import au.com.woolworths.village.sdk.model.MerchantSchemaSummaries
import au.com.woolworths.village.sdk.model.MerchantSchemaSummary
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun merchantSchemaSummariesFrom(dto: JsonObject): Matcher<MerchantSchemaSummaries> =
    object : Matcher<MerchantSchemaSummaries> {
        override fun test(value: MerchantSchemaSummaries): MatcherResult =
            MatcherResult.test(
                MatcherResult.forEach<JsonElement, MerchantSchemaSummary>(::merchantSchemaSummaryFrom),
                dto["schemas"]?.jsonArray,
                value.schemas
            )
    }

fun merchantSchemaSummaryFrom(dto: JsonElement): Matcher<MerchantSchemaSummary> =
    merchantSchemaSummaryFrom(dto.jsonObject)

fun merchantSchemaSummaryFrom(dto: JsonObject): Matcher<MerchantSchemaSummary> =
    object : Matcher<MerchantSchemaSummary> {
        override fun test(value: MerchantSchemaSummary): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["schemaId"]?.content(), value.schemaId),
                MatcherResult.test(::equal, dto["type"]?.content(), value.type),
                MatcherResult.test(::equal, dto["description"]?.content(), value.description),
            )
    }

fun merchantSchemaFrom(dto: JsonObject): Matcher<MerchantSchema> =
    object : Matcher<MerchantSchema> {
        override fun test(value: MerchantSchema): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["schema"], value.schema),
                MatcherResult.test(::equal, dto["type"]?.content(), value.type),
                MatcherResult.test(::equal, dto["description"]?.content(), value.description),
                MatcherResult.test(::equal, dto["created"]?.toDate(), value.created),
            )
    }