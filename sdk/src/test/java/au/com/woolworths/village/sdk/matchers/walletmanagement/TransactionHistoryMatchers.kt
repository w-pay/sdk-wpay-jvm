package au.com.woolworths.village.sdk.matchers.walletmanagement

import au.com.woolworths.village.sdk.matchers.*
import au.com.woolworths.village.sdk.model.walletmanagement.Transaction
import au.com.woolworths.village.sdk.model.walletmanagement.TransactionHistoryResponse
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun transactionHistoryResponseFrom(dto: JsonObject): Matcher<TransactionHistoryResponse> =
    object : Matcher<TransactionHistoryResponse> {
        override fun test(value: TransactionHistoryResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["returned"]?.toInt(), value.returned),
                MatcherResult.test(::equal, dto["total"]?.toInt(), value.total),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, Transaction>(::transactionFrom),
                    dto["transactions"]?.jsonArray,
                    value.transactions
                )
            )
    }

fun transactionFrom(dto: JsonElement): Matcher<Transaction> =
    transactionFrom(dto.jsonObject)

fun transactionFrom(dto: JsonObject): Matcher<Transaction> =
    object : Matcher<Transaction> {
        override fun test(value: Transaction): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionType"]?.toUpperCase(), value.transactionType.toString()),
                MatcherResult.test(::equal, dto["transactionRef"]?.content(), value.transactionRef),
                MatcherResult.test(::equal, dto["transactionTimestamp"]?.content(), value.transactionTimestamp),
                MatcherResult.test(::equal, dto["applicationRef"]?.content(), value.applicationRef),
                MatcherResult.test(::equal, dto["applicationName"]?.content(), value.applicationName),
                MatcherResult.test(::equal, dto["clientReference"]?.content(), value.clientReference),
                MatcherResult.test(::equal, dto["orderNumber"]?.content(), value.orderNumber),
                MatcherResult.test(::equal, dto["bin"]?.content(), value.bin ?: "null"),
                MatcherResult.test(::equal, dto["network"]?.content(), value.network),
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix ?: "null"),
                MatcherResult.test(::equal, dto["amount"]?.toDecimal(), value.amount),
                MatcherResult.test(::equal, dto["comment"]?.content(), value.comment),
                MatcherResult.test(::equal, dto["paymentInstrumentType"]?.content(), value.paymentInstrumentType),
            )
    }
