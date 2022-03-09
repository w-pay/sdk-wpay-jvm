package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.AcceptTermsAndConditionsRequest
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun termsAndConditionsDTO(): JsonObject =
    JsonObject(mapOf(
        "termsAndConditionsAcceptances" to JsonArray(listOf(
            JsonObject(mapOf(
                "type" to JsonPrimitive("EVERYDAY_PAY"),
                "version" to JsonPrimitive("1.0.0"),
                "timestamp" to JsonPrimitive(1604284343175)
            ))
        ))
    ))

fun acceptTermsAndConditionsRequest(): AcceptTermsAndConditionsRequest =
    AcceptTermsAndConditionsRequest(
        type = "EVERYDAY_PAY",
        version = "1.0.0"
    )