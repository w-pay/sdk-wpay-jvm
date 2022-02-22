package au.com.woolworths.village.sdk.data

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun healthCheckDTO(status: String = "SUCCESS"): JsonObject =
    JsonObject(mapOf(
        "healthCheck" to JsonPrimitive(status)
    ))