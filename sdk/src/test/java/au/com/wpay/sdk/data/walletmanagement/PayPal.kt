package au.com.wpay.sdk.data.walletmanagement

import au.com.wpay.sdk.model.walletmanagement.TokenizePaypalRequest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun tokenizePaypalRequest(): TokenizePaypalRequest =
    TokenizePaypalRequest(
        nonce = "d5e2986b-ffd5-03af-1bee-ff2dc1e83a56",
        primary = true
    )

fun tokenizePaypalResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "payPal" to JsonObject(mapOf(
            "paymentInstrumentId" to JsonPrimitive("90271"),
            "status" to JsonPrimitive("UNVERIFIED_PERSISTENT"),
            "lastUpdated" to JsonPrimitive("2017-10-26T04:56:25.046Z"),
            "primary" to JsonPrimitive(true),
            "allowed" to JsonPrimitive(true),
            "payPalId" to JsonPrimitive("jane.doe@paypal.com"),
            "customerId" to JsonPrimitive("690238314")
        ))
    ))
