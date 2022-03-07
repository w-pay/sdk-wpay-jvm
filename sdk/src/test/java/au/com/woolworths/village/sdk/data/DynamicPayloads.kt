package au.com.woolworths.village.sdk.data

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

fun aNewPosPayload(): JsonObject =
    JsonObject(mapOf(
        "schemaId" to JsonPrimitive(UUID.randomUUID().toString()),
        "payload" to JsonObject(mapOf(
            "posPayloadKey" to JsonPrimitive("some value")
        ))
    ))

fun posPayloadDTO(): JsonObject =
    JsonObject(mapOf(
        "schemaId" to JsonPrimitive(UUID.randomUUID().toString()),
        "payload" to JsonObject(mapOf(
            "posPayloadKey" to JsonPrimitive("some value")
        ))
    ))

fun aNewMerchantPayload(): JsonObject =
    JsonObject(mapOf(
        "schemaId" to JsonPrimitive(UUID.randomUUID().toString()),
        "payload" to JsonObject(mapOf(
            "merchantPayloadKey" to JsonPrimitive("some value")
        ))
    ))

fun merchantPayloadDTO(): JsonObject =
    JsonObject(mapOf(
        "schemaId" to JsonPrimitive(UUID.randomUUID().toString()),
        "payload" to JsonObject(mapOf(
            "merchantPayloadKey" to JsonPrimitive("some value")
        ))
    ))