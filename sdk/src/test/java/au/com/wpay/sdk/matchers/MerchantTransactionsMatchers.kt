package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.model.MerchantTransactionDetails
import au.com.wpay.sdk.model.MerchantTransactionSummaries
import au.com.wpay.sdk.model.MerchantTransactionSummaryType
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun merchantTransactionSummariesFrom(dto: JsonObject): Matcher<MerchantTransactionSummaries> =
    object : Matcher<MerchantTransactionSummaries> {
        override fun test(value: MerchantTransactionSummaries): MatcherResult =
            MatcherResult.test(
                MatcherResult.forEach<JsonElement, MerchantTransactionSummaryType>(::merchantTransactionSummaryFrom),
                dto["transactions"]?.jsonArray,
                value.transactions
            )
    }

fun merchantTransactionSummaryFrom(dto: JsonElement): Matcher<MerchantTransactionSummaryType> =
    merchantTransactionSummaryFrom(dto.jsonObject)

fun merchantTransactionSummaryFrom(dto: JsonObject): Matcher<MerchantTransactionSummaryType> =
    object : Matcher<MerchantTransactionSummaryType> {
        override fun test(value: MerchantTransactionSummaryType): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::transactionSummaryTypeFrom, dto, value),
                MatcherResult.test(::equal, dto["walletId"]?.content(), value.walletId)
            )
    }

fun merchantTransactionDetailsFrom(dto: JsonObject): Matcher<MerchantTransactionDetails> =
    object : Matcher<MerchantTransactionDetails> {
        override fun test(value: MerchantTransactionDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::merchantTransactionSummaryFrom, dto, value),
                MatcherResult.test(::basketFrom, dto["basket"], value.basket),
                MatcherResult.test(::dynamicPayloadFrom, dto["posPayload"], value.posPayload),
                MatcherResult.test(::dynamicPayloadFrom, dto["merchantPayload"], value.merchantPayload)
            )
    }
