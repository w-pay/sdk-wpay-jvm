package au.com.wpay.sdk.matchers

import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

fun dateFrom(date: String?): OffsetDateTime? =
    date?.let { OffsetDateTime.parse(it) }

fun JsonElement?.content(): String =
    this!!.jsonPrimitive.content

fun JsonElement?.toBoolean(): Boolean =
    this!!.jsonPrimitive.content.toBoolean()

fun JsonElement?.toDecimal(): BigDecimal =
    BigDecimal(this!!.jsonPrimitive.content)

fun JsonElement?.toDate(): OffsetDateTime =
    dateFrom(this!!.jsonPrimitive.content)!!

fun JsonElement?.toInt(): Int =
    this!!.jsonPrimitive.content.toInt()

fun JsonElement?.toLong(): Long =
    this!!.jsonPrimitive.content.toLong()

fun JsonElement?.toUpperCase(): String =
    this!!.jsonPrimitive.content.uppercase()

fun jsonObjectFrom(dto: JsonElement): Matcher<JsonObject> =
    jsonObjectFrom(dto.jsonObject)

fun jsonObjectFrom(dto: JsonObject): Matcher<JsonObject> =
    object : Matcher<JsonObject> {
        override fun test(value: JsonObject): MatcherResult =
            MatcherResult.test(::equal, dto, value)
    }
