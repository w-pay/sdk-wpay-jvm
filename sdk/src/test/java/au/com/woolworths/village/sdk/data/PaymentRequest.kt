package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.ImmediatePaymentItem
import au.com.woolworths.village.sdk.model.ImmediatePaymentRequest
import au.com.woolworths.village.sdk.model.digitalpay.PaymentTransactionType
import au.com.woolworths.village.sdk.model.digitalpay.PreauthPurchase
import au.com.woolworths.village.sdk.model.digitalpay.Purchase
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal
import java.util.*

fun customerPaymentRequestDTO(): JsonObject =
    JsonObject(mapOf(
        "merchantId" to JsonPrimitive("10006"),
        "grossAmount" to JsonPrimitive(32.1),
        "paymentRequestId" to JsonPrimitive(UUID.randomUUID().toString()),
        "merchantReferenceId" to JsonPrimitive(UUID.randomUUID().toString()),
        "basket" to basketDTO()
    ))

fun immediatePaymentRequest(): ImmediatePaymentRequest =
    ImmediatePaymentRequest(
        clientReference = "client-ref",
        orderNumber = "order-123",
        payments = listOf(ImmediatePaymentItem(
            paymentInstrumentId = "123456",
            amount = BigDecimal(100)
        ))
    )

fun paymentTransactionType(): PaymentTransactionType = PaymentTransactionType(
    creditCard = PreauthPurchase.PURCHASE,
    giftCard = Purchase.PURCHASE
)