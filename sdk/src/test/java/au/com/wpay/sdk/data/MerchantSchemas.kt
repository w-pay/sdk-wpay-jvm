package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.MerchantSchema
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

fun aNewMerchantSchema(): MerchantSchema =
    MerchantSchema(
        schema = JsonObject(mapOf(
            "key" to JsonPrimitive("value")
        )),
        type = "pos",
        description = "my new schema"
    )

fun merchantSchemaDTO(): JsonObject =
    JsonObject(mapOf(
        "schema" to JsonObject(mapOf(
            "key" to JsonPrimitive("value")
        )),
        "type" to JsonPrimitive("pos"),
        "description" to JsonPrimitive("my new schema"),
        "created" to JsonPrimitive("2021-02-17T06:31:46.358Z")
    ))

fun merchantSchemaSummariesDTO(): JsonObject =
    JsonObject(mapOf(
        "schemas" to JsonArray(listOf(merchantSchemaSummaryDTO()))
    ))

fun merchantSchemaSummaryDTO(): JsonObject =
    JsonObject(mapOf(
        "schemaId" to JsonPrimitive(UUID.randomUUID().toString()),
        "type" to JsonPrimitive("pos"),
        "description" to JsonPrimitive("a very important schema"),
        "created" to JsonPrimitive("2021-02-17T06:31:46.358Z")
    ))
