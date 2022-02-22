package au.com.woolworths.village.sdk.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

val parser = Json {
    ignoreUnknownKeys = true
}

inline fun <reified T> fromDTO(dto: JsonObject): T =
    parser.decodeFromJsonElement(dto)