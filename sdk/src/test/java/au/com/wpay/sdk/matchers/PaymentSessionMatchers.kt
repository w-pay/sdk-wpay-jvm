package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.model.CreatePaymentSessionResult
import au.com.wpay.sdk.model.PaymentSession
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonObject

fun paymentSessionFrom(dto: JsonObject): Matcher<PaymentSession> =
    object : Matcher<PaymentSession> {
        override fun test(value: PaymentSession): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentSessionId"]?.content(), value.paymentSessionId),
                MatcherResult.test(::equal, dto["paymentRequestId"]?.content(), value.paymentRequestId),
                MatcherResult.test(::equal, dto["merchantId"]?.content(), value.merchantId),
                MatcherResult.test(::equal, dto["walletId"]?.content(), value.walletId),
                MatcherResult.test(::equal, dto["expiryTime"]?.toDate(), value.expiryTime),
                MatcherResult.test(::equal, dto["location"]?.content(), value.location),
                MatcherResult.test(::dynamicPayloadFrom, dto["merchantInfo"], value.merchantInfo),
                MatcherResult.test(::dynamicPayloadFrom, dto["customerInfo"], value.customerInfo),
            )

    }

fun paymentSessionCreatedFrom(dto: JsonObject): Matcher<CreatePaymentSessionResult> =
    object : Matcher<CreatePaymentSessionResult> {
        override fun test(value: CreatePaymentSessionResult): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentSessionId"]?.content(), value.paymentSessionId),
                MatcherResult.test(::qrCodeFrom, dto["qr"], value.qr)
            )
    }
