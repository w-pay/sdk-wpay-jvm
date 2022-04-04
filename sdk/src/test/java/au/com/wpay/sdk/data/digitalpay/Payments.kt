package au.com.wpay.sdk.data.digitalpay

import au.com.wpay.sdk.model.digitalpay.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal

fun digitalPayPaymentRequest(): DigitalPayPaymentRequest =
    DigitalPayPaymentRequest(
        transactionType = PaymentTransactionType(
            creditCard = PreauthPurchase.PURCHASE
        ),
        clientReference = "ref-1234",
        orderNumber = "12345678",
        shippingAddress = DigitalPayAddress(
            firstName = "John",
            lastName = "Smith",
            streetAddress = "1 Woolworths Way",
            suburb = "Bella Vista",
            stateOrTerritory = "NSW",
            postalCode = "2153",
            countryCode = "AU"
        ),
        payments = listOf(
            DigitalPayPayment(
                paymentInstrumentId = "123",
                paymentToken = "54321",
                amount = BigDecimal(150.25)
            )
        )
    )

fun digitalPayPaymentResponse(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("txn-123"),
        "partialSuccess" to JsonPrimitive(false),
        "fraudResponse" to JsonObject(mapOf(
            "clientId" to JsonPrimitive("client-id"),
            "reasonCode" to JsonPrimitive("123"),
            "decision" to JsonPrimitive("decision")
        )),
        "creditCards" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentInstrumentId" to JsonPrimitive("12345"),
                "paymentToken" to JsonPrimitive("token-12"),
                "paymentTransactionRef" to JsonPrimitive("ref-123")
            ))
        )),
        "giftCards" to JsonArray(emptyList()),
        "payPal" to JsonArray(emptyList()),
        "androidPay" to JsonArray(emptyList()),
        "googlePay" to JsonArray(emptyList()),
        "applePay" to JsonArray(emptyList()),
        "unknown" to JsonArray(emptyList())
    ))

fun digitalPayCompletionRequest(): DigitalPayCompletionRequest =
    DigitalPayCompletionRequest(
        clientReference = "ref-1234",
        orderNumber = "12345678",
        completions = listOf(
            DigitalPayCompletion(
                paymentTransactionRef = "payment-123",
                amount = BigDecimal(100.0)
            )
        )
    )

fun digitalPayCompletionResponse(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("receipt-123"),
        "partialSuccess" to JsonPrimitive(false),
        "completionResponses" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentTransactionRef" to JsonPrimitive("txn-123"),
                "completionTransactionRef" to JsonPrimitive("comp-123"),
                "amount" to JsonPrimitive(100.0)
            ))
        ))
    ))

fun digitalPayVoidRequest(): DigitalPayVoidRequest =
    DigitalPayVoidRequest(
        clientReference = "ref-123",
        orderNumber = "order-123",
        voids = listOf(
            DigitalPayVoid(
                paymentTransactionRef = "pay-123"
            )
        )
    )

fun digitalPayVoidResponse(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("txn-123"),
        "partialSuccess" to JsonPrimitive(false),
        "voidResponses" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentTransactionRef" to JsonPrimitive("pay-123"),
                "voidTransactionRef" to JsonPrimitive("void-123")
            ))
        ))
    ))

fun digitalPayRefundRequest(): DigitalPayRefundRequest =
    DigitalPayRefundRequest(
        clientReference = "ref-123",
        orderNumber = "order-123",
        refunds = listOf(
            DigitalPayRefund(
                paymentTransactionRef = "pay-123",
                amount = BigDecimal(123.45)
            )
        )
    )

fun digitalPayRefundResponse(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("txn-123"),
        "partialSuccess" to JsonPrimitive(false),
        "refundResponses" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentTransactionRef" to JsonPrimitive("pay-123"),
                "refundTransactionRef" to JsonPrimitive("ref-123"),
                "amount" to JsonPrimitive(123.45),
                "externalServiceMessage" to JsonPrimitive("Message")
            ))
        ))
    ))
