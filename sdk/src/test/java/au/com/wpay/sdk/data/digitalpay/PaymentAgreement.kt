package au.com.wpay.sdk.data.digitalpay

import au.com.wpay.sdk.model.PaymentAgreementChargeFrequency
import au.com.wpay.sdk.model.PaymentAgreementType
import au.com.wpay.sdk.model.digitalpay.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal

fun digitalPayPaymentAgreementResponseDTO(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("abc123def"),
        "paymentAgreement" to JsonObject(mapOf(
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
    ))

fun digitalPayCreatePaymentAgreementRequest(): DigitalPayCreatePaymentAgreementRequest =
    DigitalPayCreatePaymentAgreementRequest(
        clientReference = "T5ESYRPWJKPHF54",
        customerRef = "654321",
        orderNumber = "201905070001",
        billingAddress = DigitalPayAddress(
            firstName = "Francois",
            lastName = "van der Merwe",
            email = "fvandermerwe@woolworths.com.au",
            company = "Woolworths Limited",
            extendedAddress = "Level 1",
            streetAddress = "22 River Oak Circuit",
            suburb = "Kellyville",
            stateOrTerritory = "NSW",
            postalCode = "2155",
            countryCode = "AU"
        ),
        paymentAgreement = DigitalPayRequestPaymentAgreement(
            type = PaymentAgreementType.RECURRING,
            paymentInstrumentId = "90731",
            startDate = "2018-09-01T00:00:00.000+1100",
            endDate = "2018-12-31T23:59:59.999+1100",
            chargeFrequency = PaymentAgreementChargeFrequency.WEEKLY,
            chargeAmount = BigDecimal(25.99),
            immediateCharge = true,
            stepUpToken = "e86b3a32-96a5-4659-b6d8-5d685bfa78e8"
        )
    )

fun digitalPayUpdatePaymentAgreementRequest(): DigitalPayUpdatePaymentAgreementRequest =
    DigitalPayUpdatePaymentAgreementRequest(
        clientReference = "T5ESYRPWJKPHF54",
        customerRef = "654321",
        billingAddress = DigitalPayAddress(
            firstName = "Francois",
            lastName = "van der Merwe",
            email = "fvandermerwe@woolworths.com.au",
            company = "Woolworths Limited",
            extendedAddress = "Level 1",
            streetAddress = "22 River Oak Circuit",
            suburb = "Kellyville",
            stateOrTerritory = "NSW",
            postalCode = "2155",
            countryCode = "AU"
        ),
        paymentAgreement = DigitalPayPaymentAgreementUpdate(
            paymentInstrumentId = "90731",
            startDate = "2018-09-01T00:00:00.000+1100",
            endDate = "2018-12-31T23:59:59.999+1100",
            chargeFrequency = PaymentAgreementChargeFrequency.WEEKLY,
            chargeAmount = BigDecimal(25.99),
            stepUpToken = "e86b3a32-96a5-4659-b6d8-5d685bfa78e8"
        )
    )

fun digitalPayChargePaymentAgreementRequest(): DigitalPayChargePaymentAgreementRequest =
    DigitalPayChargePaymentAgreementRequest(
        transactionType = PaymentTransactionType(
            creditCard = PreauthPurchase.PURCHASE
        ),
        clientReference = "T5ESYRPWJKPHF54",
        orderNumber = "201808312007",
        paymentToken = "8f65ca0a-7873-4d1f-ab8d-6815adae5300",
        amount = BigDecimal(25.99)
    )
