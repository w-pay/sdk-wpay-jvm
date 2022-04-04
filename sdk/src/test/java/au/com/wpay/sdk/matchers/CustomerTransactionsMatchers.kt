package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.model.CustomerTransactionDetails
import au.com.wpay.sdk.model.CustomerTransactionSummaries
import au.com.wpay.sdk.model.CustomerTransactionSummaryType
import au.com.wpay.sdk.model.TransactionSummaryType
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun customerTransactionSummaryFrom(dto: JsonElement): Matcher<CustomerTransactionSummaryType> =
    customerTransactionSummaryFrom(dto.jsonObject)

fun customerTransactionSummaryFrom(dto: JsonObject): Matcher<CustomerTransactionSummaryType> =
    object: Matcher<CustomerTransactionSummaryType> {
        override fun test(value: CustomerTransactionSummaryType): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::transactionSummaryTypeFrom, dto, value),
                MatcherResult.test(::equal, dto["merchantId"]?.content(), value.merchantId),
            )
    }

fun customerTransactionSummariesFrom(dto: JsonObject): Matcher<CustomerTransactionSummaries> =
    object : Matcher<CustomerTransactionSummaries> {
        override fun test(value: CustomerTransactionSummaries): MatcherResult =
            MatcherResult.test(
                MatcherResult.forEach<JsonElement, CustomerTransactionSummaryType>(::customerTransactionSummaryFrom),
                dto["transactions"]?.jsonArray,
                value.transactions
            )
    }

fun customerTransactionDetailsFrom(dto: JsonObject): Matcher<CustomerTransactionDetails> =
    object : Matcher<CustomerTransactionDetails> {
        override fun test(value: CustomerTransactionDetails): MatcherResult =
            Matcher.tests(
                Matcher.tests(
                    MatcherResult.test(::customerTransactionSummaryFrom, dto, value),
                    MatcherResult.test(::basketFrom, dto["basket"], value.basket)
                )
            )
    }

fun customerUsedPaymentInstrumentFrom(dto: JsonElement): Matcher<TransactionSummaryType.UsedPaymentInstrument> =
    customerUsedPaymentInstrumentFrom(dto.jsonObject)

fun customerUsedPaymentInstrumentFrom(dto: JsonObject): Matcher<TransactionSummaryType.UsedPaymentInstrument> =
    object : Matcher<TransactionSummaryType.UsedPaymentInstrument> {
        override fun test(value: TransactionSummaryType.UsedPaymentInstrument): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["instrumentType"]?.content(), value.instrumentType),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, TransactionSummaryType.UsedPaymentInstrumentTransaction>(::customerUsedPaymentInstrumentTransaction),
                    dto["transactions"]?.jsonArray,
                    value.transactions
                )
            )
    }

fun customerUsedPaymentInstrumentTransaction(
    dto: JsonElement
): Matcher<TransactionSummaryType.UsedPaymentInstrumentTransaction> =
    customerUsedPaymentInstrumentTransaction(dto.jsonObject)

fun customerUsedPaymentInstrumentTransaction(
    dto: JsonObject
): Matcher<TransactionSummaryType.UsedPaymentInstrumentTransaction> =
    object : Matcher<TransactionSummaryType.UsedPaymentInstrumentTransaction> {
        override fun test(value: TransactionSummaryType.UsedPaymentInstrumentTransaction): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["type"]?.toUpperCase(), value.type.toString()),
                MatcherResult.test(::equal, dto["executionTime"]?.toDate(), value.executionTime),
                MatcherResult.test(::equal, dto["paymentTransactionRef"]?.content(), value.paymentTransactionRef),
                MatcherResult.test(::equal, dto["refundTransactionRef"]?.content(), value.refundTransactionRef),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["amount"]?.toDecimal(), value.amount)
            )
    }
