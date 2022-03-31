package au.com.woolworths.village.sdk.matchers.walletmanagement

import au.com.woolworths.village.sdk.matchers.content
import au.com.woolworths.village.sdk.matchers.equal
import au.com.woolworths.village.sdk.matchers.test
import au.com.woolworths.village.sdk.matchers.tests
import au.com.woolworths.village.sdk.model.walletmanagement.InitiateCardCaptureResponse
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonObject

fun initCardCaptureResponseFrom(dto: JsonObject): Matcher<InitiateCardCaptureResponse> =
    object : Matcher<InitiateCardCaptureResponse> {
        override fun test(value: InitiateCardCaptureResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["cardCaptureURL"]?.content(), value.cardCaptureURL),
                MatcherResult.test(::equal, dto["transactionRef"]?.content(), value.transactionRef)
            )
    }
