package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.TransactionSummaryType
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

fun customerTransactionSummaryDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentRequestId" to JsonPrimitive(UUID.randomUUID().toString()),
        "merchantReferenceId" to JsonPrimitive(UUID.randomUUID().toString()),
        "grossAmount" to JsonPrimitive(12.32),
        "transactionId" to JsonPrimitive(UUID.randomUUID().toString()),
        "clientReference" to JsonPrimitive(UUID.randomUUID().toString()),
        "type" to JsonPrimitive(TransactionSummaryType.PaymentType.PAYMENT.toString().lowercase()),
        "executionTime" to JsonPrimitive("2021-02-17T06:31:46.358Z"),
        "status" to JsonPrimitive(TransactionSummaryType.PaymentStatus.APPROVED.toString().lowercase()),
        "rollback" to JsonPrimitive(TransactionSummaryType.SummaryRollback.FAILED.toString().lowercase()),
        "merchantId" to JsonPrimitive(UUID.randomUUID().toString()),
        "instruments" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentInstrumentId" to JsonPrimitive(UUID.randomUUID().toString()),
                "instrumentType" to JsonPrimitive("card"),
                "transactions" to JsonArray(listOf(
                    JsonObject(mapOf(
                        "type" to JsonPrimitive(TransactionSummaryType.PaymentType.PAYMENT.toString().lowercase()),
                        "executionTime" to JsonPrimitive("2021-02-17T06:31:46.358Z"),
                        "paymentTransactionRef" to JsonPrimitive(UUID.randomUUID().toString()),
                        "status" to JsonPrimitive(TransactionSummaryType.PaymentStatus.APPROVED.toString().lowercase()),
                        "amount" to JsonPrimitive(12.32)
                    ))
                ))
            ))
        ))
    ))

fun customerTransactionSummariesDTO(): JsonObject =
    JsonObject(mapOf(
        "transactions" to JsonArray(listOf(
            customerTransactionSummaryDTO()
        ))
    ))

fun customerTransactionDetailsDTO(): JsonObject =
    JsonObject(buildMap {
        putAll(customerTransactionSummaryDTO())
        put("basket", basketDTO())
    })