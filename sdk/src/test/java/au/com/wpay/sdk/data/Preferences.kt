package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.CustomerPreferences
import au.com.wpay.sdk.model.PaymentPreferences
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

fun customerPreferences(): CustomerPreferences =
    CustomerPreferences(
        payments = paymentPreferences(),
        general = emptyMap()
    )

fun paymentPreferences(): PaymentPreferences =
    PaymentPreferences(UUID.randomUUID().toString(), null)

fun preferencesDTO(): JsonObject =
    JsonObject(mapOf(
        "group" to JsonObject(mapOf(
            "preference" to JsonPrimitive("value")
        ))
    ))
