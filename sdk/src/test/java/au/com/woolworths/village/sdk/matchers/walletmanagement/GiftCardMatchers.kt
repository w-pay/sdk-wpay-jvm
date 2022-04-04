package au.com.woolworths.village.sdk.matchers.walletmanagement

import au.com.woolworths.village.sdk.matchers.*
import au.com.woolworths.village.sdk.model.walletmanagement.GiftCardBalance
import au.com.woolworths.village.sdk.model.walletmanagement.GiftCardsBalanceResponse
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizeGiftCardResponse
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizedGiftCard
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun tokenizeGiftCardResponseFrom(dto: JsonObject): Matcher<TokenizeGiftCardResponse> =
    object : Matcher<TokenizeGiftCardResponse> {
        override fun test(value: TokenizeGiftCardResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::tokenizedGiftCardFrom, dto["giftCard"], value.giftCard),
                MatcherResult.test(::equal, dto["balance"]?.toDecimal(), value.balance),
                MatcherResult.test(::equal, dto["expiryDay"]?.content(), value.expiryDay),
                MatcherResult.test(::equal, dto["expiryMonth"]?.content(), value.expiryMonth),
                MatcherResult.test(::equal, dto["expiryYear"]?.content(), value.expiryYear),
                MatcherResult.test(::equal, dto["expired"]?.toBoolean(), value.expired)
            )
    }

fun tokenizedGiftCardFrom(dto: JsonElement): Matcher<TokenizedGiftCard> =
    tokenizedGiftCardFrom(dto.jsonObject)

fun tokenizedGiftCardFrom(dto: JsonObject): Matcher<TokenizedGiftCard> =
    object : Matcher<TokenizedGiftCard> {
        override fun test(value: TokenizedGiftCard): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["status"]?.toUpperCase(), value.status.toString()),
                MatcherResult.test(::equal, dto["lastUpdated"]?.content(), value.lastUpdated),
                MatcherResult.test(::equal, dto["lastUsed"]?.content(), value.lastUsed),
                MatcherResult.test(::equal, dto["primary"]?.toBoolean(), value.primary),
                MatcherResult.test(::equal, dto["allowed"]?.toBoolean(), value.allowed),
                MatcherResult.test(::equal, dto["programName"]?.content(), value.programName),
                MatcherResult.test(::equal, dto["cardSuffix"]?.content(), value.cardSuffix)
            )
    }

fun giftCardsBalanceFrom(dto: JsonObject): Matcher<GiftCardsBalanceResponse> =
    object : Matcher<GiftCardsBalanceResponse> {
        override fun test(value: GiftCardsBalanceResponse): MatcherResult =
            MatcherResult.test(
                MatcherResult.forEach<JsonElement, GiftCardBalance>(::giftCardBalanceFrom),
                dto["giftCardBalances"]?.jsonArray,
                value.giftCardBalances
            )
    }

fun giftCardBalanceFrom(dto: JsonElement): Matcher<GiftCardBalance> =
    giftCardBalanceFrom(dto.jsonObject)

fun giftCardBalanceFrom(dto: JsonObject): Matcher<GiftCardBalance> =
    object : Matcher<GiftCardBalance> {
        override fun test(value: GiftCardBalance): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["cardNumber"]?.content(), value.cardNumber),
                MatcherResult.test(::equal, dto["paymentInstrumentId"]?.content(), value.paymentInstrumentId),
                MatcherResult.test(::equal, dto["balance"]?.toDecimal(), value.balance),
                MatcherResult.test(::equal, dto["expiryDay"]?.content(), value.expiryDay),
                MatcherResult.test(::equal, dto["expiryMonth"]?.content(), value.expiryMonth),
                MatcherResult.test(::equal, dto["expiryYear"]?.content(), value.expiryYear),
                MatcherResult.test(::equal, dto["expired"]?.toBoolean(), value.expired)
            )
    }
