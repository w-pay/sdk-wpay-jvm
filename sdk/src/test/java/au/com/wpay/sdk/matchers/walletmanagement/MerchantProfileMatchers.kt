package au.com.wpay.sdk.matchers.walletmanagement

import au.com.wpay.sdk.matchers.*
import au.com.wpay.sdk.model.walletmanagement.*
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun merchantProfileFrom(dto: JsonObject): Matcher<MerchantProfileResponse> =
    object : Matcher<MerchantProfileResponse> {
        override fun test(value: MerchantProfileResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::allowedPaymentMethodsFrom, dto["allowedPaymentMethods"], value.allowedPaymentMethods),
            )
    }

fun allowedPaymentMethodsFrom(dto: JsonElement): Matcher<AllowedPaymentMethods> =
    allowedPaymentMethodsFrom(dto.jsonObject)

fun allowedPaymentMethodsFrom(dto: JsonObject): Matcher<AllowedPaymentMethods> =
    object : Matcher<AllowedPaymentMethods> {
        override fun test(value: AllowedPaymentMethods): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::allowedPaymentMethodsGiftCardFrom, dto["giftCard"], value.giftCard),
                MatcherResult.test(::allowedPaymentMethodsCreditCardFrom, dto["creditCard"], value.creditCard),
                MatcherResult.test(::allowedPaymentMethodsPayPalFrom, dto["paypal"], value.paypal),
                MatcherResult.test(::allowedPaymentMethodsGooglePayFrom, dto["googlePay"], value.googlePay),
                MatcherResult.test(::allowedPaymentMethodsApplePayFrom, dto["applePay"], value.applePay)
            )
    }

fun allowedPaymentMethodsGiftCardFrom(dto: JsonElement): Matcher<AllowedPaymentMethodsGiftCard> =
    allowedPaymentMethodsGiftCardFrom(dto.jsonObject)

fun allowedPaymentMethodsGiftCardFrom(dto: JsonObject): Matcher<AllowedPaymentMethodsGiftCard> =
    object : Matcher<AllowedPaymentMethodsGiftCard> {
        override fun test(value: AllowedPaymentMethodsGiftCard): MatcherResult =
            Matcher.tests(
                MatcherResult.test(
                    MatcherResult.forEach { el -> PredicateMatcher(::equal, el.content()) },
                    dto["allowedBins"]?.jsonArray,
                    value.allowedBins
                ),
                MatcherResult.test(::equal, dto["serviceStatus"]?.toUpperCase(), value.serviceStatus.toString()),
                MatcherResult.test(::equal, dto["pinAlwaysRequired"]?.toBoolean(), value.pinAlwaysRequired),
            )
    }

fun allowedPaymentMethodsCreditCardFrom(dto: JsonElement): Matcher<AllowedPaymentMethodsCreditCard> =
    allowedPaymentMethodsCreditCardFrom(dto.jsonObject)

fun allowedPaymentMethodsCreditCardFrom(dto: JsonObject): Matcher<AllowedPaymentMethodsCreditCard> =
    object : Matcher<AllowedPaymentMethodsCreditCard> {
        override fun test(value: AllowedPaymentMethodsCreditCard): MatcherResult =
            Matcher.tests(
                MatcherResult.test(
                    MatcherResult.forEach { el -> PredicateMatcher(::equal, el.content()) },
                    dto["allowedNetworks"]?.jsonArray,
                    value.allowedNetworks
                ),
                MatcherResult.test(
                    MatcherResult.forEach { el -> PredicateMatcher(::equal, el.toUpperCase()) },
                    dto["allowedTransactionTypes"]?.jsonArray,
                    value.allowedTransactionTypes.map { it.toString() }
                ),
                MatcherResult.test(::equal, dto["serviceStatus"]?.toUpperCase(), value.serviceStatus.toString())
            )
    }

fun allowedPaymentMethodsPayPalFrom(dto: JsonElement): Matcher<AllowedPaymentMethodsPaypal> =
    allowedPaymentMethodsPayPalFrom(dto.jsonObject)

fun allowedPaymentMethodsPayPalFrom(dto: JsonObject): Matcher<AllowedPaymentMethodsPaypal> =
    object : Matcher<AllowedPaymentMethodsPaypal> {
        override fun test(value: AllowedPaymentMethodsPaypal): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["clientToken"]?.content(), value.clientToken),
                MatcherResult.test(::equal, dto["serviceStatus"]?.toUpperCase(), value.serviceStatus.toString()),
            )
    }

fun allowedPaymentMethodsGooglePayFrom(dto: JsonElement): Matcher<AllowedPaymentMethodsGooglePay> =
    allowedPaymentMethodsGooglePayFrom(dto.jsonObject)

fun allowedPaymentMethodsGooglePayFrom(dto: JsonObject): Matcher<AllowedPaymentMethodsGooglePay> =
    object : Matcher<AllowedPaymentMethodsGooglePay> {
        override fun test(value: AllowedPaymentMethodsGooglePay): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["publicKey"]?.content(), value.publicKey),
                MatcherResult.test(::equal, dto["publicKeyHash"]?.content(), value.publicKeyHash),
                MatcherResult.test(::equal, dto["publicKeyExpiry"]?.toLong(), value.publicKeyExpiry),
                MatcherResult.test(::equal, dto["merchantId"]?.content(), value.merchantId),
                MatcherResult.test(::equal, dto["merchantName"]?.content(), value.merchantName),
                MatcherResult.test(::cardFrom, dto["creditCard"], value.creditCard),
                MatcherResult.test(::cardFrom, dto["debitCard"], value.debitCard),
                MatcherResult.test(::equal, dto["serviceStatus"]?.toUpperCase(), value.serviceStatus.toString()),
            )
    }

fun cardFrom(dto: JsonElement): Matcher<Card> =
    cardFrom(dto.jsonObject)

fun cardFrom(dto: JsonObject): Matcher<Card> =
    object : Matcher<Card> {
        override fun test(value: Card): MatcherResult =
            Matcher.tests(
                MatcherResult.test(
                    MatcherResult.forEach { el -> PredicateMatcher(::equal, el.content()) },
                    dto["allowedNetworks"]?.jsonArray,
                    value.allowedNetworks
                ),
                MatcherResult.test(
                    MatcherResult.forEach { el -> PredicateMatcher(::equal, el.toUpperCase()) },
                    dto["allowedTransactionTypes"]?.jsonArray,
                    value.allowedTransactionTypes.map { it.toString() }
                )
            )
    }

fun allowedPaymentMethodsApplePayFrom(dto: JsonElement): Matcher<AllowedPaymentMethodsApplePay> =
    allowedPaymentMethodsApplePayFrom(dto.jsonObject)

fun allowedPaymentMethodsApplePayFrom(dto: JsonObject): Matcher<AllowedPaymentMethodsApplePay> =
    object : Matcher<AllowedPaymentMethodsApplePay> {
        override fun test(value: AllowedPaymentMethodsApplePay): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::cardFrom, dto["creditCard"], value.creditCard),
                MatcherResult.test(::cardFrom, dto["debitCard"], value.debitCard),
                MatcherResult.test(::equal, dto["serviceStatus"]?.toUpperCase(), value.serviceStatus.toString()),
            )
    }
