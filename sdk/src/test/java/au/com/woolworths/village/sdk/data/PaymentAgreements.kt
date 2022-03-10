package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.model.walletmanagement.PaymentInstrumentStatus
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal

fun createPaymentAgreementRequest(): CreatePaymentAgreementRequest =
    CreatePaymentAgreementRequest(
        clientReference = "client-ref",
        customerRef = "customer-ref",
        orderNumber = "order-12345",
        description = "Description of the payment agreement.",
        billingAddress = PaymentAgreementBillingAddress(
            firstName = "John",
            lastName = "Smith",
            email = "john.smith@test.com",
            streetAddress = "1 Test St",
            suburb = "Melbourne",
            stateOrTerritory = "VIC",
            postalCode = "3000",
            countryCode = "AU",
            company = null,
            extendedAddress = null
        ),
        paymentAgreement = PaymentAgreement(
            paymentToken = "abc123",
            status = PaymentInstrumentStatus.UNVERIFIED_PERSISTENT,
            type = PaymentAgreementType.RECURRING,
            paymentInstrumentId = "90731",
            chargeFrequency = PaymentAgreementChargeFrequency.WEEKLY,
            chargeAmount = BigDecimal("25.99"),
            chargeCycle = 0,
            updateURL = "http://foobar.com"
        )
    )

fun paymentAgreementsDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentAgreements" to JsonArray(listOf(
            paymentAgreementDTO()
        ))
    ))

fun paymentAgreementDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentToken" to JsonPrimitive("27e07e4e-58df-4072-8e75-33dd464af667"),
        "status" to JsonPrimitive("UNVERIFIED_PERSISTENT"),
        "lastUpdated" to JsonPrimitive("2019-09-01T06:31:46.358Z"),
        "lastUsed" to JsonPrimitive("2018-09-01T06:31:46.358Z"),
        "createdOn" to JsonPrimitive("2017-11-01T06:31:46.358Z"),
        "primary" to JsonPrimitive(false),
        "allowed" to JsonPrimitive(true),
        "type" to JsonPrimitive("RECURRING"),
        "paymentInstrumentId" to JsonPrimitive("90731"),
        "scheme" to JsonPrimitive("VISA"),
        "cardSuffix" to JsonPrimitive("4405"),
        "expiryMonth" to JsonPrimitive("11"),
        "expiryYear" to JsonPrimitive("22"),
        "startDate" to JsonPrimitive("2020-09-01T00:00:00.000Z"),
        "endDate" to JsonPrimitive("2021-12-31T23:29:29.999Z"),
        "chargeFrequency" to JsonPrimitive("MONTHLY"),
        "chargeAmount" to JsonPrimitive(25.99),
        "chargeCycle" to JsonPrimitive(2),
        "expired" to JsonPrimitive(false),
        "updateURL" to JsonPrimitive("https://{environment}.mobile-api.woolworths.com.au/wow/v1/pay/paymentagreements/27e07e4e-58df-4072-8e75-33dd464af667"),
        "stepUp" to JsonObject(mapOf(
            "type" to JsonPrimitive("CAPTURE_CVV"),
            "mandatory" to JsonPrimitive(true),
            "url" to JsonPrimitive("https://uat.woolworthspay.com.au/container-ws/getCaptureFrame/cvvExpiry/353629ec-4cb5-4fc3-ab47-8c9c3f117ab8/90731")
        )),
        "description" to JsonPrimitive("A description of the payment agreement")
    ))

fun updatePaymentAgreementRequest(): UpdatePaymentAgreementRequest =
    UpdatePaymentAgreementRequest(
        clientReference = "client-ref",
        customerRef = "customer-ref",
        orderNumber = "order-12345",
        description = "Description of the payment agreement.",
        billingAddress = PaymentAgreementBillingAddress(
            firstName = "John",
            lastName = "Smith",
            email = "john.smith@test.com",
            streetAddress = "1 Test St",
            suburb = "Melbourne",
            stateOrTerritory = "VIC",
            postalCode = "3000",
            countryCode = "AU",
            company = null,
            extendedAddress = null
        ),
        paymentAgreement = PaymentAgreement(
            paymentToken = "abc123",
            status = PaymentInstrumentStatus.UNVERIFIED_PERSISTENT,
            type = PaymentAgreementType.RECURRING,
            paymentInstrumentId = "90731",
            chargeFrequency = PaymentAgreementChargeFrequency.WEEKLY,
            chargeAmount = BigDecimal("25.99"),
            chargeCycle = 0,
            updateURL = "http://foobar.com"
        )
    )

fun chargePaymentAgreementRequest(): ChargePaymentAgreementRequest =
    ChargePaymentAgreementRequest(
        transactionType = TransactionType.PURCHASE,
        amount = BigDecimal(25.53),
        clientReference = "client-ref",
        customerRef = "customer-ref",
        orderNumber = "order-12345",
        paymentToken = "11111111-1111-1111-111111111111"
    )