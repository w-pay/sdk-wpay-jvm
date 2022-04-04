package au.com.wpay.sdk.matchers.digitalpay

import au.com.wpay.sdk.matchers.*
import au.com.wpay.sdk.model.digitalpay.*
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun openPayTransactionResponseFrom(dto: JsonObject): Matcher<OpenPayPaymentResponse> =
    object : Matcher<OpenPayPaymentResponse> {
        override fun test(value: OpenPayPaymentResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, OpenPayPaymentResponseDetails>(::openPayPaymentResponseDetailsFrom),
                    dto["paymentResponses"]?.jsonArray,
                    value.paymentResponses
                )
            )
    }

fun openPayPaymentResponseDetailsFrom(dto: JsonElement): Matcher<OpenPayPaymentResponseDetails> =
    openPayPaymentResponseDetailsFrom(dto.jsonObject)

fun openPayPaymentResponseDetailsFrom(dto: JsonObject): Matcher<OpenPayPaymentResponseDetails> =
    object : Matcher<OpenPayPaymentResponseDetails> {
        override fun test(value: OpenPayPaymentResponseDetails): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentToken"]?.toUpperCase(), value.paymentToken),
                MatcherResult.test(::equal, dto["paymentTransactionRef"]?.content(), value.paymentTransactionRef),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, OpenPayExtendedTransactionData>(::openPayExtendedTransactionDataFrom),
                    dto["extendedTransactionData"]?.jsonArray,
                    value.extendedTransactionData
                ),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage),
            )
    }

fun openPayExtendedTransactionDataFrom(dto: JsonElement): Matcher<OpenPayExtendedTransactionData> =
    openPayExtendedTransactionDataFrom(dto.jsonObject)

fun openPayExtendedTransactionDataFrom(dto: JsonObject): Matcher<OpenPayExtendedTransactionData> =
    object : Matcher<OpenPayExtendedTransactionData> {
        override fun test(value: OpenPayExtendedTransactionData): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["field"]?.toUpperCase(), value.field),
                MatcherResult.test(::equal, dto["value"]?.content(), value.value)
            )
    }

fun openPayCompletionResponseFrom(dto: JsonObject): Matcher<OpenPayCompletionResponse> =
    object: Matcher<OpenPayCompletionResponse> {
        override fun test(value: OpenPayCompletionResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, OpenPayTransactionCompletionResponse>(::openPayTransactionCompletionResponseFrom),
                    dto["completionResponses"]?.jsonArray,
                    value.completionResponses
                ),
            )
    }

fun openPayTransactionCompletionResponseFrom(dto: JsonElement): Matcher<OpenPayTransactionCompletionResponse> =
    openPayTransactionCompletionResponseFrom(dto.jsonObject)

fun openPayTransactionCompletionResponseFrom(dto: JsonObject): Matcher<OpenPayTransactionCompletionResponse> =
    object : Matcher<OpenPayTransactionCompletionResponse> {
        override fun test(value: OpenPayTransactionCompletionResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentTransactionRef"]?.content(), value.paymentTransactionRef),
                MatcherResult.test(::equal, dto["completionTransactionRef"]?.content(), value.completionTransactionRef),
                MatcherResult.test(::equal, dto["amount"]?.toDecimal(), value.amount),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, OpenPayExtendedTransactionData>(::openPayExtendedTransactionDataFrom),
                    dto["extendedTransactionData"]?.jsonArray,
                    value.extendedTransactionData
                )
            )
    }

fun openPayVoidResponseFrom(dto: JsonObject): Matcher<OpenPayVoidResponse> =
    object : Matcher<OpenPayVoidResponse> {
        override fun test(value: OpenPayVoidResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, OpenPayVoidTransactionResponse>(::openPayTransactionVoidResponseFrom),
                    dto["voidResponses"]?.jsonArray,
                    value.voidResponses
                )
            )
    }

fun openPayTransactionVoidResponseFrom(dto: JsonElement): Matcher<OpenPayVoidTransactionResponse> =
    openPayTransactionVoidResponseFrom(dto.jsonObject)

fun openPayTransactionVoidResponseFrom(dto: JsonObject): Matcher<OpenPayVoidTransactionResponse> =
    object : Matcher<OpenPayVoidTransactionResponse> {
        override fun test(value: OpenPayVoidTransactionResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentTransactionRef"]?.content(), value.paymentTransactionRef),
                MatcherResult.test(::equal, dto["voidTransactionRef"]?.content(), value.voidTransactionRef),
                MatcherResult.test(::equal, dto["amount"]?.toDecimal(), value.amount),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, OpenPayExtendedTransactionData>(::openPayExtendedTransactionDataFrom),
                    dto["extendedTransactionData"]?.jsonArray,
                    value.extendedTransactionData
                )
            )
    }

fun openPayRefundResponseFrom(dto: JsonObject): Matcher<OpenPayRefundResponse> =
    object : Matcher<OpenPayRefundResponse> {
        override fun test(value: OpenPayRefundResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, OpenPayRefundTransactionResponse>(::openPayTransactionRefundResponseFrom),
                    dto["refundResponses"]?.jsonArray,
                    value.refundResponses
                )
            )
    }

fun openPayTransactionRefundResponseFrom(dto: JsonElement): Matcher<OpenPayRefundTransactionResponse> =
    openPayTransactionRefundResponseFrom(dto.jsonObject)

fun openPayTransactionRefundResponseFrom(dto: JsonObject): Matcher<OpenPayRefundTransactionResponse> =
    object : Matcher<OpenPayRefundTransactionResponse> {
        override fun test(value: OpenPayRefundTransactionResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentTransactionRef"]?.content(), value.paymentTransactionRef),
                MatcherResult.test(::equal, dto["refundTransactionRef"]?.content(), value.refundTransactionRef),
                MatcherResult.test(::equal, dto["amount"]?.toDecimal(), value.amount),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, OpenPayExtendedTransactionData>(::openPayExtendedTransactionDataFrom),
                    dto["extendedTransactionData"]?.jsonArray,
                    value.extendedTransactionData
                )
            )
    }
