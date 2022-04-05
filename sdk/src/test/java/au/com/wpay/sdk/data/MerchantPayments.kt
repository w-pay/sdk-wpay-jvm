package au.com.wpay.sdk.data

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

fun merchantPaymentSummariesDTO(): JsonObject =
    JsonObject(mapOf(
        "payments" to JsonArray(listOf(merchantPaymentSummaryDTO()))
    ))

fun merchantPaymentSummaryDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentRequestId" to JsonPrimitive(UUID.randomUUID().toString()),
        "merchantReferenceId" to JsonPrimitive(UUID.randomUUID().toString()),
        "grossAmount" to JsonPrimitive(345.23),
        "usesRemaining" to JsonPrimitive(2),
        "expiryTime" to JsonPrimitive("2021-02-17T06:31:46.358Z"),
        "specificWalletId" to JsonPrimitive("399394393920")
    ))

fun merchantPaymentDetailsDTO(): JsonObject =
    JsonObject(buildMap {
        putAll(merchantPaymentSummaryDTO())
        put("basket", basketDTO())
        put("posPayload", posPayloadDTO())
        put("merchantPayload", merchantPayloadDTO())
    })
