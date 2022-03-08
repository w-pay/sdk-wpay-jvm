package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.CustomerPreferences
import au.com.woolworths.village.sdk.model.PaymentPreferences
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