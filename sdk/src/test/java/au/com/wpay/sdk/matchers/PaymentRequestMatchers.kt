package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.model.CustomerPaymentRequest
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

fun customerPaymentRequestFrom(dto: JsonObject): Matcher<CustomerPaymentRequest> =
    object : Matcher<CustomerPaymentRequest> {
        override fun test(value: CustomerPaymentRequest): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["merchantId"]?.content(), value.merchantId),
                MatcherResult.test(::equal, dto["paymentRequestId"]?.content(), value.paymentRequestId),
                MatcherResult.test(::equal, dto["merchantReferenceId"]?.content(), value.merchantReferenceId),
                MatcherResult.test(::equal, dto["grossAmount"]?.toDecimal(), value.grossAmount),
                MatcherResult.test(::basketFrom, dto["basket"]?.jsonObject, value.basket)
            )
    }
