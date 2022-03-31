package au.com.woolworths.village.sdk.data.walletmanagement

import au.com.woolworths.village.sdk.model.walletmanagement.InitiateCardCaptureRequest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun initiateCardCaptureRequest(): InitiateCardCaptureRequest =
    InitiateCardCaptureRequest(
        clientReference = "JRIQ6EOK2NEQ6BY"
    )

fun initiateCardCaptureResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "cardCaptureURL" to JsonPrimitive("https://uat.woolworthspay.com.au/container-ws/getCaptureFrame/7038dda0-5928-4656-b051-b6c2ba20a8b7"),
        "transactionRef" to JsonPrimitive("1000000000661300")
    ))
