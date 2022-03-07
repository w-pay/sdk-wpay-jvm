package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.TransactionSummaryType
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun transactionSummaryTypeFrom(dto: JsonElement): Matcher<TransactionSummaryType> =
    transactionSummaryTypeFrom(dto.jsonObject)

fun transactionSummaryTypeFrom(dto: JsonObject): Matcher<TransactionSummaryType> =
    object : Matcher<TransactionSummaryType> {
        override fun test(value: TransactionSummaryType): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["merchantReferenceId"]?.content(), value.merchantReferenceId),
                MatcherResult.test(::equal, dto["paymentRequestId"]?.content(), value.paymentRequestId),
                MatcherResult.test(::equal, dto["type"]?.toUpperCase(), value.type.toString()),
                MatcherResult.test(::equal, dto["grossAmount"]?.toDecimal(), value.grossAmount),
                MatcherResult.test(::equal, dto["executionTime"]?.toDate(), value.executionTime),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["rollback"]?.toUpperCase(), value.rollback.toString()),
                MatcherResult.test(::equal, dto["refundReason"]?.toUpperCase(), value.refundReason),
                MatcherResult.test(::equal, dto["transactionId"]?.content(), value.transactionId),
                MatcherResult.test(::equal, dto["clientReference"]?.content(), value.clientReference),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, JsonObject>(::jsonObjectFrom),
                    dto["subTransactions"]?.jsonArray,
                    value.subTransactions
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, TransactionSummaryType.UsedPaymentInstrument>(::customerUsedPaymentInstrumentFrom),
                    dto["instruments"]?.jsonArray,
                    value.instruments
                )
            )
    }