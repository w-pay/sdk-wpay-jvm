package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.ChallengeResponse
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

fun aChallengeResponse(): ChallengeResponse =
    ChallengeResponse(
        instrumentId = UUID.randomUUID().toString(),
        type = ChallengeResponse.Type.PASSCODE,
        token = "fajkfafkaf",
        reference = "a reference"
    )

fun aChallengeResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "instrumentId" to JsonPrimitive(UUID.randomUUID().toString()),
        "type" to JsonPrimitive("passcode"),
        "token" to JsonPrimitive("fajkfafkaf"),
        "reference" to JsonPrimitive("a reference")
    ))
