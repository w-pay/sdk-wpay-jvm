package au.com.woolworths.village.sdk.model

import au.com.redcrew.apisdkcreator.httpclient.UnstructuredData
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

val parser = Json {
    ignoreUnknownKeys = true
}

inline fun <reified T> fromDTO(dto: JsonObject): T =
    parser.decodeFromJsonElement(dto)

fun apiResponse(data: JsonObject, meta: JsonObject = JsonObject(emptyMap())): JsonObject =
    JsonObject(mapOf(
        "data" to data,
        "meta" to meta
    ))

fun stringData(data: JsonObject): UnstructuredData =
    UnstructuredData.String(data.toString())