package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.model.*
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun walletContentsFrom(dto: JsonObject): Matcher<WalletContents> =
    object : Matcher<WalletContents> {
        override fun test(value: WalletContents): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::hasPaymentInstrumentsFrom, dto, value),
                MatcherResult.test(::hasPaymentInstrumentsFrom, dto["everydayPay"], value.everydayPay)
            )
    }

fun hasPaymentInstrumentsFrom(dto: JsonElement): Matcher<PaymentInstruments> =
    hasPaymentInstrumentsFrom(dto.jsonObject)

fun hasPaymentInstrumentsFrom(dto: JsonObject): Matcher<PaymentInstruments> =
    object : Matcher<PaymentInstruments> {
        override fun test(value: PaymentInstruments): MatcherResult =
            Matcher.tests(
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, CreditCard>(::creditCardFrom),
                    dto["creditCards"]?.jsonArray,
                    value.creditCards
                ),
                MatcherResult.test(
                    MatcherResult.forEach<JsonElement, GiftCard>(::giftCardFrom),
                    dto["giftCards"]?.jsonArray,
                    value.giftCards
                )
            )
    }

fun creditCardFrom(dto: JsonElement): Matcher<CreditCard> =
    creditCardFrom(dto.jsonObject)

fun creditCardFrom(dto: JsonObject): Matcher<CreditCard> =
    object : Matcher<CreditCard> {
        override fun test(value: CreditCard): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["cardName"]?.content(), value.cardName),
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix),
                MatcherResult.test(::equal, dto["cvvValidated"]?.toBoolean(), value.cvvValidated),
                MatcherResult.test(::equal, dto["expired"]?.toBoolean(), value.expired),
                MatcherResult.test(::equal, dto["expiryMonth"]?.content(), value.expiryMonth),
                MatcherResult.test(::equal, dto["expiryYear"]?.content(), value.expiryYear),
                MatcherResult.test(::equal, dto["lastUpdated"]?.toDate(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.toDate(), value.lastUsed),
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["requiresCVV"]?.toBoolean(), value.requiresCVV),
                MatcherResult.test(::equal, dto["scheme"]?.content(), value.scheme),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["updateURL"]?.content(), value.updateURL),
                MatcherResult.test(::creditCardStepUpFrom, dto["stepUp"], value.stepUp),
            )
    }

fun creditCardStepUpFrom(dto: JsonElement): Matcher<CreditCardStepUp> =
    creditCardStepUpFrom(dto.jsonObject)

fun creditCardStepUpFrom(dto: JsonObject): Matcher<CreditCardStepUp> =
    object : Matcher<CreditCardStepUp> {
        override fun test(value: CreditCardStepUp): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["mandatory"]?.toBoolean(), value.mandatory),
                MatcherResult.test(::equal, dto["type"]?.content(), value.type),
                MatcherResult.test(::equal, dto["url"]?.content(), value.url),
            )
    }

fun giftCardFrom(dto: JsonElement): Matcher<GiftCard> =
    giftCardFrom(dto.jsonObject)

fun giftCardFrom(dto: JsonObject): Matcher<GiftCard> =
    object : Matcher<GiftCard> {
        override fun test(value: GiftCard): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix),
                MatcherResult.test(::equal, dto["lastUpdated"]?.toDate(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.toDate(), value.lastUsed),
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["programName"]?.content(), value.programName),
                MatcherResult.test(::giftCardStepUpFrom, dto["stepUp"], value.stepUp),
            )
    }

fun giftCardStepUpFrom(dto: JsonElement): Matcher<GiftCardStepUp> =
    giftCardStepUpFrom(dto.jsonObject)

fun giftCardStepUpFrom(dto: JsonObject): Matcher<GiftCardStepUp> =
    object : Matcher<GiftCardStepUp> {
        override fun test(value: GiftCardStepUp): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["mandatory"]?.toBoolean(), value.mandatory),
                MatcherResult.test(::equal, dto["type"]?.content(), value.type),
            )
    }

fun individualPaymentInstrumentFrom(dto: JsonObject): Matcher<IndividualPaymentInstrument> =
    object : Matcher<IndividualPaymentInstrument> {
        override fun test(value: IndividualPaymentInstrument): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, fromDataDTO(dto)["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, fromDataDTO(dto)["lastUpdated"]?.toDate(), value.lastUpdated),
                MatcherResult.test(::equal, fromDataDTO(dto)["lastUsed"]?.toDate(), value.lastUsed),
                MatcherResult.test(::equal, fromDataDTO(dto)["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, fromDataDTO(dto)["paymentToken"]?.content(), value.paymentToken),
                MatcherResult.test(::equal, fromDataDTO(dto)["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, fromDataDTO(dto)["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, fromDataDTO(dto)["paymentInstrumentType"]?.content(), value.paymentInstrumentType),
                MatcherResult.test(
                    ::individualPaymentInstrumentDetailFrom,
                    fromDataDTO(dto)["paymentInstrumentDetail"],
                    value.paymentInstrumentDetail
                ),
                MatcherResult.test(::equal, fromMetaDTO(dto)["cipherText"]?.content(), value.cipherText)
            )
    }

fun individualPaymentInstrumentDetailFrom(
    dto: JsonElement
): Matcher<IndividualPaymentInstrument.InstrumentDetail> =
    individualPaymentInstrumentDetailFrom(dto.jsonObject)

fun individualPaymentInstrumentDetailFrom(
    dto: JsonObject
): Matcher<IndividualPaymentInstrument.InstrumentDetail> =
    object : Matcher<IndividualPaymentInstrument.InstrumentDetail> {
        override fun test(value: IndividualPaymentInstrument.InstrumentDetail): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["programName"]?.content(), value.programName),
                MatcherResult.test(::giftCardStepUpFrom, dto["stepUp"], value.stepUp)
            )
    }

fun paymentInstrumentAddedFrom(dto: JsonObject): Matcher<PaymentInstrumentAdditionResult> =
    object : Matcher<PaymentInstrumentAdditionResult> {
        override fun test(value: PaymentInstrumentAdditionResult): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["cardCaptureURL"]?.content(), value.cardCaptureURL),
                MatcherResult.test(::equal, dto["transactionRef"]?.content(), value.transactionRef)
            )
    }
