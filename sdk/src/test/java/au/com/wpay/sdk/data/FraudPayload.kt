package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.FraudPayload
import au.com.wpay.sdk.model.FraudPayloadFormat
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun fraudPayload(): FraudPayload =
    FraudPayload(
        message = "GzbOxpLagX6iFEb7td61cZyA=",
        provider = "cybersource",
        format = FraudPayloadFormat.XML,
        responseFormat = FraudPayloadFormat.XML,
        version = "CyberSourceTransaction_1.101"
    )

fun fraudPayloadDTO(): JsonObject =
    JsonObject(mapOf(
        "message" to JsonPrimitive("GzbOxpLagX6iFEb7td61cZyA="),
        "provider" to JsonPrimitive("cybersource"),
        "format" to JsonPrimitive("XML"),
        "responseFormat" to JsonPrimitive("XML"),
        "version" to JsonPrimitive("CyberSourceTransaction_1.101")
    ))
