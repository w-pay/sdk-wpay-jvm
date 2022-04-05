package au.com.wpay.sdk.matchers.walletmanagement

import au.com.wpay.sdk.matchers.content
import au.com.wpay.sdk.matchers.equal
import au.com.wpay.sdk.matchers.test
import au.com.wpay.sdk.matchers.tests
import au.com.wpay.sdk.model.walletmanagement.TokenizeGooglePayResponse
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonObject

fun tokenizeGooglePayResponseFrom(dto: JsonObject): Matcher<TokenizeGooglePayResponse> =
    object : Matcher<TokenizeGooglePayResponse> {
        override fun test(value: TokenizeGooglePayResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
            )
    }
