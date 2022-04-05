package au.com.wpay.sdk.data.digitalpay

import au.com.wpay.sdk.model.digitalpay.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal

fun openPayPaymentRequest(): OpenPayPaymentRequest =
    OpenPayPaymentRequest(
        transactionType = OpenPayPaymentRequestTransactionType(
            OpenPayTransactionType.PURCHASE
        ),
        clientReference = "ref-1234",
        orderNumber = "12345678",
        channel = "Online",
        payments = listOf(
            OpenPayPayments(
                paymentToken = "54321",
                amount = BigDecimal(150.25)
            )
        ),
        storeData = OpenPayStoreData(
            storeId = "store-1",
            pin = "12345"
        )
    )

fun openPayTransactionResponse(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("receipt-123"),
        "paymentResponses" to JsonArray(listOf(
            openPayPaymentResponse()
        ))
    ))

fun openPayPaymentResponse(): JsonObject =
    JsonObject(mapOf(
        "paymentToken" to JsonPrimitive("54321"),
        "paymentTransactionRef" to JsonPrimitive("pay-ref-1234")
    ))

fun openPayCompletionRequest(): OpenPayCompletionRequest =
    OpenPayCompletionRequest(
        clientReference = "ref-1234",
        orderNumber = "12345678",
        merchantTransactedAt = "2021-02-25T05:45:31.941Z",
        completions = listOf(
            OpenPayCompletion(
                paymentTransactionRef = "payment-123",
                amount = BigDecimal(100.0)
            )
        )
    )

fun openPayCompletionResponse(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("receipt-123"),
        "completionResponses" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentTransactionRef" to JsonPrimitive("txn-123"),
                "completionTransactionRef" to JsonPrimitive("comp-123"),
                "amount" to JsonPrimitive(100.0),
                "externalServiceCode" to JsonPrimitive("Code"),
                "externalServiceMessage" to JsonPrimitive("Message")
            ))
        ))
    ))

fun openPayVoidRequest(): OpenPayVoidRequest =
    OpenPayVoidRequest(
        clientReference = "ref-123",
        voids = listOf(
            DigitalPayVoid(
                paymentTransactionRef = "pay-123"
            )
        )
    )

fun openPayVoidResponse(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("txn-123"),
        "voidResponses" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentTransactionRef" to JsonPrimitive("pay-123"),
                "voidTransactionRef" to JsonPrimitive("void-123"),
                "amount" to JsonPrimitive(153.22)
            ))
        ))
    ))

fun openPayRefundRequest(): OpenPayRefundRequest =
    OpenPayRefundRequest(
        clientReference = "ref-123",
        refunds = listOf(
            OpenPayRefund(
                paymentTransactionRef = "pay-123",
                amount = BigDecimal(123.45)
            )
        )
    )

fun openPayRefundResponse(): JsonObject =
    JsonObject(mapOf(
        "transactionReceipt" to JsonPrimitive("txn-123"),
        "refundResponses" to JsonArray(listOf(
            JsonObject(mapOf(
                "paymentTransactionRef" to JsonPrimitive("pay-123"),
                "refundTransactionRef" to JsonPrimitive("ref-123"),
                "amount" to JsonPrimitive(123.45)
            ))
        ))
    ))
