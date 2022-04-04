package au.com.wpay.sdk.data.walletmanagement

import au.com.wpay.sdk.model.walletmanagement.TransactionClass
import au.com.wpay.sdk.model.walletmanagement.TransactionHistoryRequest
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun transactionHistoryRequestDTO(): TransactionHistoryRequest =
    TransactionHistoryRequest(
        transactionTypes = listOf(
            TransactionClass.PREAUTH,
            TransactionClass.PURCHASE
        ),
        paymentInstrumentIds = listOf("90731", "81054", "90271"),
        clientReference = "T5ESYRPWJKPHF54",
        transactionRef = "1000000000670621",
        orderNumber = "20170505090",
        startDate = "2017-01-01T00:00:00.000+1100",
        endDate = "2017-12-31T23:59:59.999+1100",
        maxRecords = 5
    )

fun transactionHistoryResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "returned" to JsonPrimitive(2),
        "total" to JsonPrimitive(6),
        "transactions" to JsonArray(listOf(
            JsonObject(mapOf(
                "transactionType" to JsonPrimitive("PURCHASE"),
                "transactionRef" to JsonPrimitive("1000000000671560"),
                "transactionTimestamp" to JsonPrimitive("2017-11-08T05:06:57.513Z"),
                "applicationRef" to JsonPrimitive("T5ESYRPWJKPHF54"),
                "applicationName" to JsonPrimitive("WowOnline"),
                "clientReference" to JsonPrimitive("T5ESYRPWJKPHF54"),
                "orderNumber" to JsonPrimitive("20170505090"),
                "bin" to JsonPrimitive("5468"),
                "network" to JsonPrimitive("MASTERCARD"),
                "cardSuffix" to JsonPrimitive("6106"),
                "amount" to JsonPrimitive(20.5),
                "comment" to JsonPrimitive(""),
                "paymentInstrumentType" to JsonPrimitive("CREDIT_CARD")
            )),
            JsonObject(mapOf(
                "transactionType" to JsonPrimitive("PURCHASE"),
                "transactionRef" to JsonPrimitive("1000000000670623"),
                "transactionTimestamp" to JsonPrimitive("2017-11-07T02:38:27.677Z"),
                "applicationRef" to JsonPrimitive("95ORIO45ZMD4ZRF"),
                "applicationName" to JsonPrimitive("WowOnline"),
                "clientReference" to JsonPrimitive("95ORIO45ZMD4ZRF"),
                "orderNumber" to JsonPrimitive("20171107051"),
                "bin" to JsonPrimitive(null as String?),
                "network" to JsonPrimitive("GIFT_CARD"),
                "cardSuffix" to JsonPrimitive(null as String?),
                "amount" to JsonPrimitive(1.0),
                "comment" to JsonPrimitive(""),
                "paymentInstrumentType" to JsonPrimitive("GIFT_CARD")
            ))
        ))
    ))
