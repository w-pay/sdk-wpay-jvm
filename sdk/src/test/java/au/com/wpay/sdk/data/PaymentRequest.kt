package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.ImmediatePaymentItem
import au.com.wpay.sdk.model.ImmediatePaymentRequest
import au.com.wpay.sdk.model.NewPaymentRequest
import au.com.wpay.sdk.model.digitalpay.PaymentTransactionType
import au.com.wpay.sdk.model.digitalpay.PreauthPurchase
import au.com.wpay.sdk.model.digitalpay.Purchase
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal
import java.util.*

fun aNewPaymentRequest(): NewPaymentRequest =
    NewPaymentRequest(
        merchantReferenceId = UUID.randomUUID().toString(),
        grossAmount = BigDecimal(32.45),
        generateQR = false,
        maxUses = 1,
        timeToLivePayment = 300,
        timeToLiveQR = 90,
        specificWalletId = "1949203",
        basket = aNewBasket(),
        posPayload = aNewPosPayload(),
        merchantPayload = aNewMerchantPayload()
    )

fun createPaymentRequestResultDTO(): JsonObject =
    JsonObject(mapOf(
        "paymentRequestId" to JsonPrimitive(UUID.randomUUID().toString()),
        "qr" to qrCodeDTO()
    ))

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
        payments = listOf(
            ImmediatePaymentItem(
            paymentInstrumentId = "123456",
            amount = BigDecimal(100)
        )
        )
    )

fun paymentTransactionType(): PaymentTransactionType = PaymentTransactionType(
    creditCard = PreauthPurchase.PURCHASE,
    giftCard = Purchase.PURCHASE
)
