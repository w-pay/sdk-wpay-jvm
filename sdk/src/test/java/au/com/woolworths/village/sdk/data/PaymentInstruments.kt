package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.Wallet
import au.com.woolworths.village.sdk.model.PaymentInstrumentAddition
import au.com.woolworths.village.sdk.model.SecondaryPaymentInstrument
import au.com.woolworths.village.sdk.model.walletmanagement.PaymentInstrumentStatus
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal
import java.util.*

fun aSecondaryPaymentInstrument(): SecondaryPaymentInstrument =
    SecondaryPaymentInstrument(UUID.randomUUID().toString(), BigDecimal(10))

fun aNewPaymentInstrument(): PaymentInstrumentAddition =
    PaymentInstrumentAddition("abc123", Wallet.EVERYDAY_PAY)

fun paymentInstrumentDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentInstrumentId" to JsonPrimitive(UUID.randomUUID().toString()),
        "allowed" to JsonPrimitive(true),
        "lastUpdated" to JsonPrimitive("2021-02-17T06:31:46.358Z"),
        "lastUsed" to JsonPrimitive("2021-02-17T06:31:46.358Z"),
        "paymentToken" to JsonPrimitive("token"),
        "primary" to JsonPrimitive(false),
        "status" to JsonPrimitive(PaymentInstrumentStatus.UNVERIFIED_PERSISTENT.toString().lowercase())
    ))

fun cardInstrumentDTO(): JsonObject =
    JsonObject(buildMap {
        putAll(paymentInstrumentDTO())
        put("cardSuffix", JsonPrimitive("1234"))
    })

fun creditCardDTO(): JsonObject =
    JsonObject(buildMap {
        putAll(cardInstrumentDTO())
        put("cardName", JsonPrimitive("My Card"))
        put("cvvValidated", JsonPrimitive(true))
        put("expired", JsonPrimitive(true))
        put("expiryMonth", JsonPrimitive("02"))
        put("expiryYear", JsonPrimitive("2100"))
        put("requiresCVV", JsonPrimitive(false))
        put("scheme", JsonPrimitive("visa"))
        put("updateURL", JsonPrimitive("http://foobar.com/"))
        put("stepUp", JsonObject(mapOf(
            "type" to JsonPrimitive("capture_cvv"),
            "mandatory" to JsonPrimitive(false),
            "url" to JsonPrimitive("http://foobar.com/")
        )))
    })

fun giftCardDTO(): JsonObject =
    JsonObject(buildMap {
        putAll(cardInstrumentDTO())
        put("programName", JsonPrimitive("Gift cards"))
        put("stepUp", JsonObject(mapOf(
            "type" to JsonPrimitive("REQUIRE_PASSCODE"),
            "mandatory" to JsonPrimitive(false),
        )))
    })

fun walletContentsDTO(): JsonObject =
    JsonObject(mapOf(
        "creditCards" to JsonArray(listOf(creditCardDTO())),
        "giftCards" to JsonArray(listOf(giftCardDTO())),
        "everydayPay" to JsonObject(mapOf(
            "creditCards" to JsonArray(listOf(creditCardDTO())),
            "giftCards" to JsonArray(listOf(giftCardDTO())),
        ))
    ))

fun individualPaymentInstrumentDTO(): JsonObject =
    JsonObject(buildMap {
        putAll(paymentInstrumentDTO())
        put("paymentInstrumentType", JsonPrimitive("GIFT_CARD"))
        put("paymentInstrumentDetail", JsonObject(mapOf(
            "cardSuffix" to JsonPrimitive("5678"),
            "programName" to JsonPrimitive("Gift cards"),
            "stepUp" to JsonObject(mapOf(
                "type" to JsonPrimitive("REQUIRE_PASSCODE"),
                "mandatory" to JsonPrimitive(true)
            ))
        )))
    })

fun paymentInstrumentAdditionResultDTO(): JsonObject =
    JsonObject(mapOf(
        "cardCaptureURL" to JsonPrimitive("http://foo.com"),
        "transactionRef" to JsonPrimitive(UUID.randomUUID().toString())
    ))