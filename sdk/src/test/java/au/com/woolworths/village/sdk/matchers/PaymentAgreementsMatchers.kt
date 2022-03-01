package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.PaymentAgreement
import au.com.woolworths.village.sdk.model.PaymentAgreementStepUp
import au.com.woolworths.village.sdk.model.PaymentAgreements
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.*

fun paymentAgreementsFrom(dto: JsonObject): Matcher<PaymentAgreements> =
    object: Matcher<PaymentAgreements> {
        override fun test(value: PaymentAgreements): MatcherResult =
            MatcherResult.test(
                MatcherResult.forEach<JsonElement, PaymentAgreement>(::paymentAgreementFrom),
                dto["paymentAgreements"]?.jsonArray,
                value.paymentAgreements
            )
    }

fun paymentAgreementFrom(dto: JsonElement): Matcher<PaymentAgreement> =
    paymentAgreementFrom(dto.jsonObject)

fun paymentAgreementFrom(dto: JsonObject): Matcher<PaymentAgreement> =
    object: Matcher<PaymentAgreement> {
        override fun test(value: PaymentAgreement): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentToken"].content(), value.paymentToken),
                MatcherResult.test(::equal, dto["status"].toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["lastUpdated"].toDate(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"].toDate(), value.lastUsed),
                MatcherResult.test(::equal, dto["createdOn"].toDate(), value.createdOn),
                MatcherResult.test(::equal, dto["primary"].toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["allowed"].toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["type"].toUpperCase(), value.type.toString()),
                MatcherResult.test(::equal, dto["paymentInstrumentId"].content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["scheme"].content(), value.scheme),
                MatcherResult.test(::equal, dto["cardSuffix"].content(), value.cardSuffix),
                MatcherResult.test(::equal, dto["expiryMonth"].content(), value.expiryMonth),
                MatcherResult.test(::equal, dto["expiryYear"].content(), value.expiryYear),
                MatcherResult.test(::equal, dto["startDate"].toDate(), value.startDate),
                MatcherResult.test(::equal, dto["endDate"].toDate(), value.endDate),
                MatcherResult.test(::equal, dto["chargeFrequency"].toUpperCase(), value.chargeFrequency.toString()),
                MatcherResult.test(::equal, dto["chargeAmount"].toDecimal(), value.chargeAmount),
                MatcherResult.test(::equal, dto["chargeCycle"].toInt(), value.chargeCycle),
                MatcherResult.test(::equal, dto["expired"].toBoolean(), value.expired),
                MatcherResult.test(::equal, dto["updateURL"].content(), value.updateURL),
                MatcherResult.test(::equal, dto["description"].content(), value.description),
                MatcherResult.test(::paymentAgreementStepUpFrom, dto["stepUp"]?.jsonObject, value.stepUp)
            )
    }

fun paymentAgreementStepUpFrom(dto: JsonObject): Matcher<PaymentAgreementStepUp> =
    object : Matcher<PaymentAgreementStepUp> {
        override fun test(value: PaymentAgreementStepUp): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["type"].content(), value.type),
                MatcherResult.test(::equal, dto["mandatory"].toBoolean(), value.mandatory),
                MatcherResult.test(::equal, dto["url"].content(), value.url),
            )
    }
