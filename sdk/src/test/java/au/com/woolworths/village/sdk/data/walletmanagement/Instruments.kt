package au.com.woolworths.village.sdk.data.walletmanagement

import au.com.woolworths.village.sdk.model.walletmanagement.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal

fun importPaymentInstrumentsRequest(): ImportPaymentInstrumentsRequest =
    ImportPaymentInstrumentsRequest(
        uid = "61ea4c7310df484d91e15cd6ad883ccb",
        shopperId = "12345",
        creditCards = listOf(
            PaymentInstrumentRequestCreditCard(
                transactionRef = "2000000009719570",
                transactionTimestamp = "2017-09-26T23:11:27.000Z",
                orderNumber = "20170505090",
                bin = "543048",
                cardSuffix = "4307",
                amount = BigDecimal(75.5)
            )
        ),
        payPal = PayPalDetail(
            customerId = "690238314",
            payPalId = "jane.doe@paypal.com",
            paymentMethodToken = "I7wME6uOKgsq3fz3y52s"
        )
    )

fun importPaymentInstrumentsResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "uid" to JsonPrimitive("61ea4c7310df484d91e15cd6ad883ccb"),
        "shopperId" to JsonPrimitive("12345"),
        "creditCards" to JsonArray(listOf(
            JsonObject(mapOf(
                "transactionRef" to JsonPrimitive("2000000009719570"),
                "transactionTimestamp" to JsonPrimitive("2017-09-26T23:11:27.000Z"),
                "transactionType" to JsonPrimitive("PURCHASE"),
                "transactionResponseCode" to JsonPrimitive("00"),
                "transactionResponseText" to JsonPrimitive("APPROVED"),
                "orderNumber" to JsonPrimitive("20170505090"),
                "bin" to JsonPrimitive("543048"),
                "cardSuffix" to JsonPrimitive("4307"),
                "expiryMonth" to JsonPrimitive("01"),
                "expiryYear" to JsonPrimitive("19"),
                "amount" to JsonPrimitive(75.5),
                "result" to JsonPrimitive("OK")

            ))
        )),
        "payPal" to JsonObject(mapOf(
            "customerId" to JsonPrimitive("690238314"),
            "payPalId" to JsonPrimitive("jane.doe@paypal.com"),
            "paymentMethodToken" to JsonPrimitive("I7wME6uOKgsq3fz3y52s"),
            "result" to JsonPrimitive("OK")
        ))
    ))

fun listPaymentInstrumentsRequest(): ListPaymentInstrumentsRequest =
    ListPaymentInstrumentsRequest(
        uid = "61ea4c7310df484d91e15cd6ad883ccb",
        shopperId = "12345"
    )

fun listPaymentInstrumentsResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "creditCards" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentInstrumentId" to JsonPrimitive("90731"),
                "paymentToken" to JsonPrimitive("712029a1-c0aa-41bc-a810-3d42424c5834"),
                "status" to JsonPrimitive("UNVERIFIED_PERSISTENT"),
                "lastUpdated" to JsonPrimitive("2017-11-06T08:38:09.890Z"),
                "lastUsed" to JsonPrimitive("2017-11-06T08:38:09.890Z"),
                "createdOn" to JsonPrimitive("2017-11-06T08:38:09.890Z"),
                "primary" to JsonPrimitive(true),
                "allowed" to JsonPrimitive(true),
                "expiryYear" to JsonPrimitive("21"),
                "expiryMonth" to JsonPrimitive("05"),
                "scheme" to JsonPrimitive("MASTERCARD"),
                "cardSuffix" to JsonPrimitive("6106"),
                "cvvValidated" to JsonPrimitive(false),
                "cardName" to JsonPrimitive("My Card"),
                "expired" to JsonPrimitive(false),
                "requiresCVV" to JsonPrimitive(true),
                "updateURL" to JsonPrimitive("https://uat.woolworthspay.com.au/container-ws/getCaptureFrame/cvvExpiry/353629ec-4cb5-4fc3-ab47-8c9c3f117ab8/90731"),
                "stepUp" to JsonObject(mapOf(
                    "type" to JsonPrimitive("REFRESH_TOKEN"),
                    "mandatory" to JsonPrimitive(true),
                    "url" to JsonPrimitive("https://uat.woolworthspay.com.au/container-ws/getCaptureFrame/cvv/353629ec-4cb5-4fc3-ab47-8c9c3f117ab8/90731")
                ))
            ))
        )),
        "giftCards" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentInstrumentId" to JsonPrimitive("81054"),
                "paymentToken" to JsonPrimitive("ec9b062a-220a-43b3-8185-a8ca4fc4dc0c"),
                "status" to JsonPrimitive("UNVERIFIED_PERSISTENT"),
                "lastUpdated" to JsonPrimitive("2017-11-06T08:38:09.860Z"),
                "lastUsed" to JsonPrimitive("2017-10-12T02:25:49.770Z"),
                "createdOn" to JsonPrimitive("2017-11-06T08:38:09.890Z"),
                "primary" to JsonPrimitive(false),
                "allowed" to JsonPrimitive(true),
                "programName" to JsonPrimitive("WISH Gift Card"),
                "cardSuffix" to JsonPrimitive("2517"),
                "stepUp" to JsonObject(mapOf(
                    "type" to JsonPrimitive("REFRESH_TOKEN"),
                    "mandatory" to JsonPrimitive(true),
                    "url" to JsonPrimitive("https://uat.woolworthspay.com.au/container-ws/tbd")
                ))
            ))
        )),
        "payPal" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentInstrumentId" to JsonPrimitive("90271"),
                "paymentToken" to JsonPrimitive("15f774d0-e42e-11e9-a359-2a2ae2dbcce4"),
                "status" to JsonPrimitive("UNVERIFIED_PERSISTENT"),
                "lastUpdated" to JsonPrimitive("2017-11-06T08:38:09.860Z"),
                "lastUsed" to JsonPrimitive("2017-11-06T08:38:09.860Z"),
                "createdOn" to JsonPrimitive("2017-11-06T08:38:09.890Z"),
                "primary" to JsonPrimitive(false),
                "allowed" to JsonPrimitive(true),
                "payPalId" to JsonPrimitive("jane.doe@paypal.com"),
                "customerId" to JsonPrimitive("690238314")
            ))
        )),
        "paymentAgreements" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentToken" to JsonPrimitive("27e07e4e-58df-4072-8e75-33dd464af667"),
                "status" to JsonPrimitive("VERIFIED"),
                "lastUpdated" to JsonPrimitive("2018-09-01T00:00:00.000+1100"),
                "lastUsed" to JsonPrimitive("2018-09-14T12:00:00.000+1100"),
                "createdOn" to JsonPrimitive("2017-11-06T08:38:09.890Z"),
                "primary" to JsonPrimitive(false),
                "allowed" to JsonPrimitive(true),
                "type" to JsonPrimitive("RECURRING"),
                "paymentInstrumentId" to JsonPrimitive("90731"),
                "scheme" to JsonPrimitive("VISA"),
                "cardSuffix" to JsonPrimitive("4405"),
                "expiryMonth" to JsonPrimitive("11"),
                "expiryYear" to JsonPrimitive("22"),
                "startDate" to JsonPrimitive("2018-09-01T00:00:00.000+1100"),
                "endDate" to JsonPrimitive("2018-12-31T23:59:59.999+1100"),
                "chargeFrequency" to JsonPrimitive("WEEKLY"),
                "chargeAmount" to JsonPrimitive(25.99),
                "chargeCycle" to JsonPrimitive(2),
                "expired" to JsonPrimitive(false),
                "updateURL" to JsonPrimitive("https://{environment}.mobile-api.woolworths.com.au/wow/v1/pay/paymentagreements/27e07e4e-58df-4072-8e75-33dd464af667"),
                "stepUp" to JsonObject(mapOf(
                    "type" to JsonPrimitive("REFRESH_TOKEN"),
                    "mandatory" to JsonPrimitive(true),
                    "url" to JsonPrimitive("https://uat.woolworthspay.com.au/container-ws/getCaptureFrame/cvv/353629ec-4cb5-4fc3-ab47-8c9c3f117ab8/90731")
                ))
            ))
        )),
        "googlePay" to JsonObject(mapOf(
            "paymentInstrumentId" to JsonPrimitive("201155"),
            "paymentToken" to JsonPrimitive("76a4c2f1-7620-4bc4-8f4f-01c1467ea318"),
            "status" to JsonPrimitive("VERIFIED"),
            "lastUpdated" to JsonPrimitive("2017-09-20T06:20:18.173Z"),
            "lastUsed" to JsonPrimitive("2017-07-28T02:58:56.187Z"),
            "createdOn" to JsonPrimitive("2017-11-06T08:38:09.890Z"),
            "primary" to JsonPrimitive(false),
            "allowed" to JsonPrimitive(true),
            "expired" to JsonPrimitive(true),
            "stepUp" to JsonObject(mapOf(
                "type" to JsonPrimitive("REFRESH_TOKEN"),
                "mandatory" to JsonPrimitive(true),
                "url" to JsonPrimitive("https://{environment}.mobile-api.woolworths.com.au/wow/v1/pay/googlepay/tokenize/201155")
            ))
        )),
        "applePay" to JsonObject(mapOf(
            "paymentInstrumentId" to JsonPrimitive("16161"),
            "paymentToken" to JsonPrimitive("64dee650-e42e-11e9-81b4-2a2ae2dbcce4"),
            "status" to JsonPrimitive("VERIFIED"),
            "lastUpdated" to JsonPrimitive("2017-07-28T02:58:56.187Z"),
            "lastUsed" to JsonPrimitive("2017-09-20T06:20:18.173Z"),
            "createdOn" to JsonPrimitive("2017-11-06T08:38:09.890Z"),
            "primary" to JsonPrimitive(false),
            "allowed" to JsonPrimitive(true),
            "stepUp" to JsonObject(mapOf(
                "type" to JsonPrimitive("REFRESH_TOKEN"),
                "mandatory" to JsonPrimitive(true),
                "url" to JsonPrimitive("https://{environment}.mobile-api.woolworths.com.au/wow/v1/pay/applepay/tokenize/16161")
            ))
        ))
    ))

fun verifyPaymentInstrumentsRequest(): VerifyPaymentInstrumentsRequest =
    VerifyPaymentInstrumentsRequest(
        clientReference = "T5ESYRPWJKPHF54",
        paymentInstruments = listOf(
            VerifyPaymentInstrumentsRequestInstrument(
                paymentToken = "f63fbfa8-0a2f-48a6-9162-6b102161a05b",
                stepUpToken = "e86b3a32-96a5-4659-b6d8-5d685bfa78e8"
            )
        ),
        fraudPayload = FraudPayload(
            provider = "cybersource",
            version = "CyberSourceTransaction_1.101",
            format = MessageFormat.ZIP_BASE_64_ENCODED,
            responseFormat = MessageFormat.ZIP_BASE_64_ENCODED,
            message = "GzbOxpLagX6iFEb7td61cZyA="
        )
    )

fun verifyPaymentInstrumentsResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("1000000009303280"),
        "partialSuccess" to JsonPrimitive(false),
        "fraudResponse" to JsonObject(mapOf(
            "clientId" to JsonPrimitive("5615334856056397603065"),
            "reasonCode" to JsonPrimitive("100"),
            "decision" to JsonPrimitive("ACCEPT")
        )),
        "verifyResponses" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentToken" to JsonPrimitive("f63fbfa8-0a2f-48a6-9162-6b102161a05b"),
                "verifyTransactionRef" to JsonPrimitive("1000000009303281"),
                "externalServiceCode" to JsonPrimitive("00"),
                "externalServiceMessage" to JsonPrimitive("ACCEPTED")
            ))
        ))
    ))
