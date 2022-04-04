package au.com.wpay.sdk.matchers.digitalpay

import au.com.wpay.sdk.matchers.*
import au.com.wpay.sdk.model.digitalpay.DigitalPayExtendedTransactionData
import au.com.wpay.sdk.model.digitalpay.DigitalPayFraudResponse
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

fun digitalPayFraudResponseFrom(dto: JsonElement): Matcher<DigitalPayFraudResponse> =
    digitalPayFraudResponseFrom(dto.jsonObject)

fun digitalPayFraudResponseFrom(dto: JsonObject): Matcher<DigitalPayFraudResponse> =
    object : Matcher<DigitalPayFraudResponse> {
        override fun test(value: DigitalPayFraudResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["clientId"]?.content(), value.clientId),
                MatcherResult.test(::equal, dto["reasonCode"]?.content(), value.reasonCode),
                MatcherResult.test(::equal, dto["decision"]?.content(), value.decision)
            )
    }

fun digitalPayExtendedTransactionDataFrom(dto: JsonElement): Matcher<DigitalPayExtendedTransactionData> =
    digitalPayExtendedTransactionDataFrom(dto.jsonObject)

fun digitalPayExtendedTransactionDataFrom(dto: JsonObject): Matcher<DigitalPayExtendedTransactionData> =
    object : Matcher<DigitalPayExtendedTransactionData> {
        override fun test(value: DigitalPayExtendedTransactionData): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["field"]?.toUpperCase(), value.field.toString()),
                MatcherResult.test(::equal, dto["value"]?.content(), value.value)
            )
    }
