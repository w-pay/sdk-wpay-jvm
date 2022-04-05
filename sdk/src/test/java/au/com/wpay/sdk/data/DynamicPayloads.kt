package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.MerchantPayload
import au.com.wpay.sdk.model.PosPayload
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

fun aNewPosPayload(): PosPayload =
    PosPayload(
        schemaId = UUID.randomUUID().toString(),
        payload = JsonObject(mapOf(
            "posPayloadKey" to JsonPrimitive("some value")
        ))
    )

fun posPayloadDTO(): JsonObject =
    JsonObject(mapOf(
        "schemaId" to JsonPrimitive(UUID.randomUUID().toString()),
        "payload" to JsonObject(mapOf(
            "posPayloadKey" to JsonPrimitive("some value")
        ))
    ))

fun aNewMerchantPayload(): MerchantPayload =
    MerchantPayload(
        schemaId = UUID.randomUUID().toString(),
        payload = JsonObject(mapOf(
            "merchantPayloadKey" to JsonPrimitive("some value")
        ))
    )

fun merchantPayloadDTO(): JsonObject =
    JsonObject(mapOf(
        "schemaId" to JsonPrimitive(UUID.randomUUID().toString()),
        "payload" to JsonObject(mapOf(
            "merchantPayloadKey" to JsonPrimitive("some value")
        ))
    ))
