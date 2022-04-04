package au.com.wpay.sdk.matchers.walletmanagement

import au.com.wpay.sdk.matchers.content
import au.com.wpay.sdk.matchers.equal
import au.com.wpay.sdk.matchers.test
import au.com.wpay.sdk.matchers.tests
import au.com.wpay.sdk.model.walletmanagement.InitiateCardCaptureResponse
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
