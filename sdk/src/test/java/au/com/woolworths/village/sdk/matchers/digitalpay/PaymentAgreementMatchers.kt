package au.com.woolworths.village.sdk.matchers.digitalpay

import au.com.woolworths.village.sdk.matchers.*
import au.com.woolworths.village.sdk.model.digitalpay.DigitalPayExtendedTransactionData
import au.com.woolworths.village.sdk.model.digitalpay.DigitalPayPaymentAgreementResponse
import au.com.woolworths.village.sdk.model.digitalpay.DigitalPayResponsePaymentAgreement
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun digitalPayPaymentAgreementFrom(dto: JsonObject): Matcher<DigitalPayPaymentAgreementResponse> =
    object : Matcher<DigitalPayPaymentAgreementResponse> {
        override fun test(value: DigitalPayPaymentAgreementResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["transactionReceipt"]?.content(), value.transactionReceipt),
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::digitalPayResponsePaymentAgreementFrom, dto["paymentAgreement"], value.paymentAgreement),
                MatcherResult.test(::digitalPayFraudResponseFrom, dto["fraudResponse"], value.fraudResponse),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, DigitalPayExtendedTransactionData>(::digitalPayExtendedTransactionDataFrom),
                    dto["extendedTransactionData"]?.jsonArray,
                    value.extendedTransactionData
                ),
                MatcherResult.test(::equal, dto["externalServiceCode"]?.content(), value.externalServiceCode),
                MatcherResult.test(::equal, dto["externalServiceMessage"]?.content(), value.externalServiceMessage)
            )
    }

fun digitalPayResponsePaymentAgreementFrom(dto: JsonElement): Matcher<DigitalPayResponsePaymentAgreement> =
    digitalPayResponsePaymentAgreementFrom(dto.jsonObject)

fun digitalPayResponsePaymentAgreementFrom(dto: JsonObject): Matcher<DigitalPayResponsePaymentAgreement> =
    object : Matcher<DigitalPayResponsePaymentAgreement> {
        override fun test(value: DigitalPayResponsePaymentAgreement): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["type"]?.toUpperCase(), value.type.toString()),
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["paymentInstrumentType"]?.content(), value.paymentInstrumentType),
                MatcherResult.test(::equal, dto["scheme"]?.content(), value.scheme),
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix),
                MatcherResult.test(::equal, dto["expiryMonth"]?.content(), value.expiryMonth),
                MatcherResult.test(::equal, dto["expiryYear"]?.content(), value.expiryYear),
                MatcherResult.test(::equal, dto["startDate"]?.content(), value.startDate),
                MatcherResult.test(::equal, dto["endDate"]?.content(), value.endDate),
                MatcherResult.test(::equal, dto["chargeFrequency"]?.toUpperCase(), value.chargeFrequency.toString()),
                MatcherResult.test(::equal, dto["chargeAmount"]?.toDecimal(), value.chargeAmount)
            )
    }