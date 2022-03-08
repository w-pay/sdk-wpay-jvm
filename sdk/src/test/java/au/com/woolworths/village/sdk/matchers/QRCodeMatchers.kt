package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.QRCode
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

fun qrCodeFrom(dto: JsonElement): Matcher<QRCode> =
    qrCodeFrom(dto.jsonObject)

fun qrCodeFrom(dto: JsonObject): Matcher<QRCode> =
    object : Matcher<QRCode> {
        override fun test(value: QRCode): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["qrId"]?.content(), value.qrId),
                MatcherResult.test(::equal, dto["referenceId"]?.content(), value.referenceId),
                MatcherResult.test(::equal, dto["referenceType"]?.toUpperCase(), value.referenceType.toString()),
                MatcherResult.test(::equal, dto["content"]?.content(), value.content),
                MatcherResult.test(::equal, dto["image"]?.content(), value.image),
                MatcherResult.test(::equal, dto["expiryTime"]?.toDate(), value.expiryTime)
            )
    }