package au.com.woolworths.village.sdk.matchers

import kotlinx.serialization.json.JsonElement
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

fun JsonElement?.toUpperCase(): String =
    this!!.jsonPrimitive.content.uppercase()
