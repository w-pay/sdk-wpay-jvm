package au.com.wpay.sdk.data.walletmanagement

import au.com.wpay.sdk.model.walletmanagement.TokenizeApplePayRequest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun tokenizeApplePayRequest(): TokenizeApplePayRequest =
    TokenizeApplePayRequest(
        data = "a15owFtcCC6xUY3S9R9qGetYgD0iEW7",
        ephemeralPublicKey = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEbLF8=",
        publicKeyHash = "AwqjNEgX2nTIQ9zIbCcA+G9iHOWU4RQ56SrYM=",
        transactionId = "87a124b4d59d3819c666555ef411c",
        signature = "MIAGCSqGSIb3DQEHAqCAMIACAQExDzANBglghk=",
        version = "EC_V1",
        instrumentType = "AMEX",
        primary = true,
        comment = "AMEX-0001",
        applicationData = "666555ef411bf515621f"
    )

fun tokenizeApplePayResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentInstrumentId" to JsonPrimitive("20191"),
        "stepUpToken" to JsonPrimitive("6bf0a37c-0a5d-4619-8182-4c331358d021")
    ))
