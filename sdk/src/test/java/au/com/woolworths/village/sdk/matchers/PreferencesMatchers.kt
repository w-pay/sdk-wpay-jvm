package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.CustomerPreferences
import au.com.woolworths.village.sdk.model.PaymentPreferences
import au.com.woolworths.village.sdk.model.Preferences
import au.com.woolworths.village.sdk.model.SecondaryInstrumentPreferences
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun paymentPreferencesFrom(dto: JsonElement): Matcher<PaymentPreferences> =
    paymentPreferencesFrom(dto.jsonObject)

fun paymentPreferencesFrom(dto: JsonObject): Matcher<PaymentPreferences> =
    object : Matcher<PaymentPreferences> {
        override fun test(value: PaymentPreferences): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["primaryInstrumentId"]?.content(), value.primaryInstrumentId),
                MatcherResult.test(::secondaryInstrumentsPreferencesFrom, dto["secondaryInstruments"], value.secondaryInstruments)
            )
    }

fun secondaryInstrumentsPreferencesFrom(dto: JsonElement): Matcher<SecondaryInstrumentPreferences> =
    secondaryInstrumentsPreferencesFrom(dto.jsonObject)

fun secondaryInstrumentsPreferencesFrom(dto: JsonObject): Matcher<SecondaryInstrumentPreferences> =
    object : Matcher<SecondaryInstrumentPreferences> {
        override fun test(value: SecondaryInstrumentPreferences): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["enableSecondaryInstruments"].toBoolean(), value.enableSecondaryInstruments),
                MatcherResult.test(::equal, dto["order"]?.toUpperCase(), value.order.toString()),
                MatcherResult.test(::equal, dto["exclude"]?.jsonArray, value.exclude),
                MatcherResult.test(::equal, dto["include"]?.jsonArray, value.include),
            )
    }

fun customerPreferencesFrom(dto: JsonObject): Matcher<CustomerPreferences> =
    object : Matcher<CustomerPreferences> {
        override fun test(value: CustomerPreferences): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::paymentPreferencesFrom, dto["payments"], value.payments),
                MatcherResult.test(::preferencesFrom, dto["general"]?.jsonObject, value.general)
            )
    }

fun preferencesFrom(dto: JsonObject): Matcher<Preferences> =
    object : Matcher<Preferences> {
        override fun test(value: Preferences): MatcherResult =
            MatcherResult.test(::equal, jsonObjectToPreferences(dto), value)
    }

@Suppress("NestedLambdaShadowedImplicitParameter")
fun jsonObjectToPreferences(dto: JsonObject): Preferences =
    dto.toMap().mapValues { it.value.jsonObject.toMap().mapValues { it.value.content() } }
