package au.com.woolworths.village.sdk.data.walletmanagement

import au.com.woolworths.village.sdk.model.walletmanagement.GiftCardBalanceGiftCard
import au.com.woolworths.village.sdk.model.walletmanagement.GiftCardsBalanceRequest
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizeGiftCardRequest
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun giftCardsBalanceRequest(): GiftCardsBalanceRequest =
    GiftCardsBalanceRequest(
        giftCards = listOf(
            GiftCardBalanceGiftCard(
                cardNumber = "6280003090926286519",
                pinCode = "9101"
            ),
            GiftCardBalanceGiftCard(
                cardNumber = "6280005550028790195",
                pinCode = "4697"
            )
        ),
        giftCardInstruments = emptyList()
    )

fun giftCardsBalanceResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "giftCardBalances" to JsonArray(listOf(
            JsonObject(mapOf(
                "cardNumber" to JsonPrimitive("6280003090920742517"),
                "paymentInstrumentId" to JsonPrimitive("81054"),
                "balance" to JsonPrimitive(333.38),
                "expiryDay" to JsonPrimitive("31"),
                "expiryMonth" to JsonPrimitive("03"),
                "expiryYear" to JsonPrimitive("2018"),
                "expired" to JsonPrimitive(false)
            ))
        ))
    ))

fun tokenizeGiftCardRequest(): TokenizeGiftCardRequest =
    TokenizeGiftCardRequest(
        cardNumber = "6280003090920742517",
        pinCode = "3196",
        primary = true,
        save = true
    )

fun tokenizeGiftCardResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "giftCard" to JsonObject(mapOf(
            "paymentInstrumentId" to JsonPrimitive("81054"),
            "status" to JsonPrimitive("UNVERIFIED_PERSISTENT"),
            "lastUpdated" to JsonPrimitive("2017-10-26T06:35:09.083Z"),
            "lastUsed" to JsonPrimitive("2017-10-12T02:25:49.770Z"),
            "primary" to JsonPrimitive(true),
            "allowed" to JsonPrimitive(true),
            "programName" to JsonPrimitive("WISH Gift Card"),
            "cardSuffix" to JsonPrimitive("2517")
        )),
        "balance" to JsonPrimitive(333.4),
        "expiryDay" to JsonPrimitive("31"),
        "expiryMonth" to JsonPrimitive("03"),
        "expiryYear" to JsonPrimitive("2018"),
        "expired" to JsonPrimitive(false)
    ))
