package au.com.wpay.sdk.data.walletmanagement

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun merchantProfileResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "allowedPaymentMethods" to JsonObject(mapOf(
            "giftCard" to JsonObject(mapOf(
                "allowedBins" to JsonArray(listOf(JsonPrimitive("628000"))),
                "serviceStatus" to JsonPrimitive("ENABLED"),
                "pinAlwaysRequired" to JsonPrimitive(false)
            )),
            "creditCard" to JsonObject(mapOf(
                "allowedNetworks" to JsonArray(listOf(
                    JsonPrimitive("AMEX"), JsonPrimitive("MASTERCARD"), JsonPrimitive("JCB"),
                    JsonPrimitive("VISA"), JsonPrimitive("DINERS"
                 ))),
                "allowedTransactionTypes" to JsonArray(listOf(JsonPrimitive("PREAUTH"), JsonPrimitive("PURCHASE"))),
                "serviceStatus" to JsonPrimitive("ENABLED")
            )),
            "payPal" to JsonObject(mapOf(
                "clientToken" to JsonPrimitive("eyJ2ZXJzaW9uIjoyLCJhdXRob3Jpem="),
                "serviceStatus" to JsonPrimitive("ENABLED")
            )),
            "googlePay" to JsonObject(mapOf(
                "publicKey" to JsonPrimitive("5HjfYVMvcLN3CakMj3yVzVlYAQ=="),
                "publicKeyHash" to JsonPrimitive("7S7yCA0TpuZ6hoYrdMzZhMQ="),
                "publicKeyExpiry" to JsonPrimitive(1600491347369),
                "merchantId" to JsonPrimitive("11111"),
                "merchantName" to JsonPrimitive("DUMMY"),
                "creditCard" to JsonObject(mapOf(
                    "allowedNetworks" to JsonArray(listOf(JsonPrimitive("AMEX"), JsonPrimitive("MASTERCARD"), JsonPrimitive("VISA"))),
                    "allowedTransactionTypes" to JsonArray(listOf(JsonPrimitive("PURCHASE"), JsonPrimitive("PREAUTH")))
                )),
                "debitCard" to JsonObject(mapOf(
                    "allowedNetworks" to JsonArray(emptyList()),
                    "allowedTransactionTypes" to JsonArray(listOf(JsonPrimitive("PURCHASE"), JsonPrimitive("PREAUTH")))
                )),
                "serviceStatus" to JsonPrimitive("ENABLED")
            )),
            "applePay" to JsonObject(mapOf(
                "creditCard" to JsonObject(mapOf(
                    "allowedNetworks" to JsonArray(listOf(JsonPrimitive("AMEX"), JsonPrimitive("MASTERCARD"), JsonPrimitive("VISA"))),
                    "allowedTransactionTypes" to JsonArray(listOf(JsonPrimitive("PURCHASE"), JsonPrimitive("PREAUTH")))
                )),
                "debitCard" to JsonObject(mapOf(
                    "allowedNetworks" to JsonArray(emptyList()),
                    "allowedTransactionTypes" to JsonArray(listOf(JsonPrimitive("PURCHASE"), JsonPrimitive("PREAUTH")))
                )),
                "serviceStatus" to JsonPrimitive("ENABLED")
            ))
        ))
    ))
