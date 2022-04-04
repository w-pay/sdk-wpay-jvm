package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.CreatePaymentSessionRequest
import au.com.wpay.sdk.model.CustomerUpdatePaymentSessionRequest
import au.com.wpay.sdk.model.DynamicPayload
import au.com.wpay.sdk.model.MerchantUpdatePaymentSessionRequest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*

fun aCreatePaymentSessionRequest(): CreatePaymentSessionRequest =
    CreatePaymentSessionRequest(
        generateQR = true,
        timeToLivePaymentSession = 0,
        timeToLiveQR = 0,
        location = "somewhere",
        merchantInfo = DynamicPayload(
            schemaId = UUID.randomUUID().toString(),
            payload = JsonObject(mapOf(
                "merchantInfoKey" to JsonPrimitive("some value")
            ))
        )
    )

fun createPaymentSessionResultDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentSessionId" to JsonPrimitive(UUID.randomUUID().toString()),
        "qr" to qrCodeDTO()
    ))

fun aCustomerUpdatePaymentSessionRequest(): CustomerUpdatePaymentSessionRequest =
    CustomerUpdatePaymentSessionRequest(
        customerInfo = DynamicPayload(
            schemaId = UUID.randomUUID().toString(),
            payload = JsonObject(mapOf(
                "customerInfoKey" to JsonPrimitive("some value")
            ))
        )
    )

fun aMerchantUpdatePaymentSessionRequest(): MerchantUpdatePaymentSessionRequest =
    MerchantUpdatePaymentSessionRequest(
        paymentRequestId = UUID.randomUUID().toString(),
        merchantInfo = DynamicPayload(
            schemaId = UUID.randomUUID().toString(),
            payload = JsonObject(mapOf(
                "merchantInfoKey" to JsonPrimitive("some value")
            ))
        )
    )

fun paymentSessionDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentSessionId" to JsonPrimitive(UUID.randomUUID().toString()),
        "paymentRequestId" to JsonPrimitive(UUID.randomUUID().toString()),
        "merchantId" to JsonPrimitive(UUID.randomUUID().toString()),
        "walletId" to JsonPrimitive("103929348"),
        "expiryTime" to JsonPrimitive("2021-02-17T06:31:46.358Z"),
        "location" to JsonPrimitive("location"),
        "merchantInfo" to JsonObject(mapOf(
            "schemaId" to JsonPrimitive(UUID.randomUUID().toString()),
            "payload" to JsonObject(mapOf(
                "merchantInfoKey" to JsonPrimitive("some value")
            ))
        )),
        "customerInfo" to JsonObject(mapOf(
            "schemaId" to JsonPrimitive(UUID.randomUUID().toString()),
            "payload" to JsonObject(mapOf(
                "customerInfoKey" to JsonPrimitive("some value")
            ))
        ))
    ))
