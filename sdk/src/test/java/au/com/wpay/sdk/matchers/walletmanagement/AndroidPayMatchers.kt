package au.com.wpay.sdk.matchers.walletmanagement

import au.com.wpay.sdk.matchers.content
import au.com.wpay.sdk.matchers.equal
import au.com.wpay.sdk.matchers.test
import au.com.wpay.sdk.matchers.tests
import au.com.wpay.sdk.model.walletmanagement.TokenizeAndroidPayResponse
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonObject

fun tokenizeAndroidPayResponseFrom(dto: JsonObject): Matcher<TokenizeAndroidPayResponse> =
    object : Matcher<TokenizeAndroidPayResponse> {
        override fun test(value: TokenizeAndroidPayResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["stepUpToken"]?.content(), value.stepUpToken)
            )
    }
