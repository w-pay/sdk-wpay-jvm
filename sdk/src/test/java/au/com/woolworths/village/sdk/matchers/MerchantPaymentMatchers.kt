package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.MerchantPaymentDetails
import au.com.woolworths.village.sdk.model.MerchantPaymentSummaries
import au.com.woolworths.village.sdk.model.MerchantPaymentSummary
import au.com.woolworths.village.sdk.model.MerchantPaymentSummaryType
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun merchantPaymentSummariesFrom(dto: JsonObject): Matcher<MerchantPaymentSummaries> =
    object : Matcher<MerchantPaymentSummaries> {
        override fun test(value: MerchantPaymentSummaries): MatcherResult =
            MatcherResult.test(
                MatcherResult.forEach<JsonElement, MerchantPaymentSummary>(::merchantPaymentSummaryFrom),
                dto["payments"]?.jsonArray,
                value.payments
            )
    }

fun merchantPaymentSummaryFrom(dto: JsonElement): Matcher<MerchantPaymentSummaryType> =
    merchantPaymentSummaryFrom(dto.jsonObject)

fun merchantPaymentSummaryFrom(dto: JsonObject): Matcher<MerchantPaymentSummaryType> =
    object : Matcher<MerchantPaymentSummaryType> {
        override fun test(value: MerchantPaymentSummaryType): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentRequestId"]?.content(), value.paymentRequestId),
                MatcherResult.test(::equal, dto["merchantReferenceId"]?.content(), value.merchantReferenceId),
                MatcherResult.test(::equal, dto["grossAmount"]?.toDecimal(), value.grossAmount),
                MatcherResult.test(::equal, dto["usesRemaining"]?.toInt(), value.usesRemaining),
                MatcherResult.test(::equal, dto["expiryTime"]?.toDate(), value.expiryTime),
                MatcherResult.test(::equal, dto["specificWalletId"]?.content(), value.specificWalletId)
            )
    }

fun merchantPaymentDetailsFrom(dto: JsonObject): Matcher<MerchantPaymentDetails> =
    object : Matcher<MerchantPaymentDetails> {
        override fun test(value: MerchantPaymentDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::merchantPaymentSummaryFrom, dto, value),
                MatcherResult.test(::basketFrom, dto["basket"], value.basket),
                MatcherResult.test(::dynamicPayloadFrom, dto["posPayload"], value.posPayload),
                MatcherResult.test(::dynamicPayloadFrom, dto["merchantPayload"], value.merchantPayload)
            )
    }