package au.com.woolworths.village.sdk.data.digitalpay

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun digitalPayPaymentAgreementResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("abc123def"),
        "paymentAgreement" to digitalPayResponsePaymentAgreementDTO()
    ))

fun digitalPayResponsePaymentAgreementDTO(): JsonObject =
    JsonObject(mapOf(
        "type" to JsonPrimitive("RECURRING"),
        "paymentInstrumentId" to JsonPrimitive("90731"),
        "paymentInstrumentType" to JsonPrimitive("card"),
        "scheme" to JsonPrimitive("VISA"),
        "cardSuffix" to JsonPrimitive("4405"),
        "expiryMonth" to JsonPrimitive("11"),
        "expiryYear" to JsonPrimitive("22"),
        "startDate" to JsonPrimitive("2020-09-01T00:00:00.000Z"),
        "endDate" to JsonPrimitive("2021-12-31T23:29:29.999Z"),
        "chargeFrequency" to JsonPrimitive("MONTHLY"),
        "chargeAmount" to JsonPrimitive(25.99)
    ))