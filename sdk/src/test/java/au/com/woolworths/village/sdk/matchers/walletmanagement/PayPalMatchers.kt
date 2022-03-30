package au.com.woolworths.village.sdk.matchers.walletmanagement

import au.com.woolworths.village.sdk.matchers.*
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizePaypalResponse
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizedPaypalDetails
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

fun tokenizePayPalResponseFrom(dto: JsonObject): Matcher<TokenizePaypalResponse> =
    object : Matcher<TokenizePaypalResponse> {
        override fun test(value: TokenizePaypalResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::tokenizedPayPayDetailsFrom, dto["payPal"], value.payPal)
            )
    }

fun tokenizedPayPayDetailsFrom(dto: JsonElement): Matcher<TokenizedPaypalDetails> =
    tokenizedPayPayDetailsFrom(dto.jsonObject)

fun tokenizedPayPayDetailsFrom(dto: JsonObject): Matcher<TokenizedPaypalDetails> =
    object : Matcher<TokenizedPaypalDetails> {
        override fun test(value: TokenizedPaypalDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["lastUpdated"]?.content(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.content(), value.lastUsed),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["payPalId"]?.content(), value.payPalId),
                MatcherResult.test(::equal, dto["customerId"]?.content(), value.customerId)
            )
    }
