package au.com.woolworths.village.sdk.matchers.walletmanagement

import au.com.woolworths.village.sdk.matchers.content
import au.com.woolworths.village.sdk.matchers.equal
import au.com.woolworths.village.sdk.matchers.test
import au.com.woolworths.village.sdk.matchers.tests
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizeGooglePayResponse
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
