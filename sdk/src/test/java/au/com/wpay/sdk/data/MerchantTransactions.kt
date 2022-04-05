package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.TransactionSummaryType
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

fun merchantTransactionSummaryDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentRequestId" to JsonPrimitive(UUID.randomUUID().toString()),
        "merchantReferenceId" to JsonPrimitive(UUID.randomUUID().toString()),
        "grossAmount" to JsonPrimitive(12.32),
        "transactionId" to JsonPrimitive(UUID.randomUUID().toString()),
        "clientReference" to JsonPrimitive(UUID.randomUUID().toString()),
        "type" to JsonPrimitive(TransactionSummaryType.PaymentType.PAYMENT.toString().lowercase()),
        "executionTime" to JsonPrimitive("2021-02-17T06:31:46.358Z"),
        "status" to JsonPrimitive(TransactionSummaryType.PaymentStatus.APPROVED.toString().lowercase()),
        "rollback" to JsonPrimitive(TransactionSummaryType.SummaryRollback.NOT_REQUIRED.toString().lowercase()),
        "walletId" to JsonPrimitive("394589294"),
        "instruments" to JsonArray(emptyList())
    ))

fun merchantTransactionSummariesDTO(): JsonObject =
    JsonObject(mapOf(
        "transactions" to JsonArray(listOf(merchantTransactionSummaryDTO()))
    ))

fun merchantTransactionDetailsDTO(): JsonObject =
    JsonObject(buildMap {
        putAll(merchantTransactionSummaryDTO())
        put("basket", basketDTO())
        put("posPayload", posPayloadDTO())
        put("merchantPayload", merchantPayloadDTO())
    })
