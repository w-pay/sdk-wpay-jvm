package au.com.wpay.sdk.data.walletmanagement

import au.com.wpay.sdk.model.walletmanagement.TokenizeAndroidPayRequest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun tokenizeAndroidPayRequest(): TokenizeAndroidPayRequest =
    TokenizeAndroidPayRequest(
        encryptedMessage = "AkG06mqN9YuZKY6gWtXFdOAJUkJtCByrj9boK1UEWIbz",
        ephemeralPublicKey = "BP38r9n4rE6PtYruJV2IUv2y6ztU10o7=",
        tag = "oUjJxWs0SbHy4t1P6KETS34xGLr3ZkbJv=",
        publicKeyHash = "L121Yu2YU6ApskgvSMWg7H0=",
        instrumentType = "MASTERCARD",
        primary = true,
        comment = "MASTER-5803"
    )

fun tokenizeAndroidPayResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentInstrumentId" to JsonPrimitive("20191"),
        "stepUpToken" to JsonPrimitive("6bf0a37c-0a5d-4619-8182-4c331358d021")
    ))
