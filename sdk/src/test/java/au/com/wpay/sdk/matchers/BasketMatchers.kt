package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.model.Basket
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun basketFrom(dto: JsonElement): Matcher<Basket> =
    basketFrom(dto.jsonObject)

fun basketFrom(dto: JsonObject): Matcher<Basket> =
    object : Matcher<Basket> {
        override fun test(value: Basket): MatcherResult =
            MatcherResult.test(
                MatcherResult.forEach<JsonElement, Basket.Item>(::basketItemFrom),
                dto["items"]?.jsonArray,
                value.items
            )
    }

fun basketItemFrom(dto: JsonElement): Matcher<Basket.Item> =
    basketItemFrom(dto.jsonObject)

fun basketItemFrom(dto: JsonObject): Matcher<Basket.Item> =
    object : Matcher<Basket.Item> {
        override fun test(value: Basket.Item): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["label"]?.content(), value.label),
                MatcherResult.test(::equal, dto["description"]?.content(), value.description),
                MatcherResult.test(::equal, dto["quantity"]?.toDecimal(), value.quantity),
                MatcherResult.test(::equal, dto["unitPrice"]?.toDecimal(), value.unitPrice),
                MatcherResult.test(::equal, dto["unitMeasure"]?.content(), value.unitMeasure),
                MatcherResult.test(::equal, dto["totalPrice"]?.toDecimal(), value.totalPrice),
                MatcherResult.test(::mapFrom, dto["tags"]?.jsonObject, value.tags)
            )
    }
