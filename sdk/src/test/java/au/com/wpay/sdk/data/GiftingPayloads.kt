package au.com.wpay.sdk.data

import au.com.wpay.sdk.model.digitalpay.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal

fun giftingProductDetail(): JsonObject =
    JsonObject(mapOf(
        "redemptionInstructions" to JsonPrimitive("Test instructions"),
        "redemptionType" to JsonPrimitive("INSTORE"),
        "termsAndConditions" to JsonPrimitive("Test terms and conditions"),
        "minValue" to JsonPrimitive(0),
        "maxValue" to JsonPrimitive(100),
        "expiryPeriodInDays" to JsonPrimitive(100),
        "expiryPeriodText" to JsonPrimitive("Test expiry period"),
        "isActive" to JsonPrimitive(true),
        "redemptionStores" to JsonArray(listOf(JsonPrimitive("test"))),
        "availability" to JsonPrimitive("PHYSICAL"),
        "designs" to JsonArray(listOf(
            JsonObject(mapOf(
                "designId" to JsonPrimitive("design-1"),
                "designType" to JsonPrimitive("DIGITAL"),
                "imageUrl" to JsonPrimitive("http://test.com")
            ))
        )),
        "productId" to JsonPrimitive("product-1"),
        "name" to JsonPrimitive("Test Gift Card"),
        "barCodeType" to JsonPrimitive("PAN"),
        "lastUpdateDateTime" to JsonPrimitive("2017-10-26T04:56:25.046Z"),
        "defaultDesign" to JsonObject(mapOf(
            "designId" to JsonPrimitive("design-1"),
            "designType" to JsonPrimitive("PHYSICAL"),
            "imageUrl" to JsonPrimitive("http://test.com")
        )),
        "discountOffered" to JsonObject(mapOf(
            "discountId" to JsonPrimitive("discount-1"),
            "description" to JsonPrimitive("Test Discount"),
            "percentageDiscount" to JsonPrimitive(10),
            "startDate" to JsonPrimitive("2017-10-26T04:56:25.046Z"),
            "endDate" to JsonPrimitive("2017-10-26T04:56:25.046Z")
        ))
    ))

fun giftingProductList(): JsonObject =
    JsonObject(mapOf(
        "products" to JsonArray(listOf(
            JsonObject(mapOf(
                "productId" to JsonPrimitive("product-1"),
                "name" to JsonPrimitive("Test Gift Card"),
                "barCodeType" to JsonPrimitive("PAN"),
                "lastUpdateDateTime" to JsonPrimitive("2017-10-26T04:56:25.046Z"),
                "defaultDesign" to JsonObject(mapOf(
                    "designId" to JsonPrimitive("design-1"),
                    "designType" to JsonPrimitive("PHYSICAL"),
                    "imageUrl" to JsonPrimitive("http://test.com")
                )),
                "discountOffered" to JsonObject(mapOf(
                    "discountId" to JsonPrimitive("discount-1"),
                    "description" to JsonPrimitive("Test Discount"),
                    "percentageDiscount" to JsonPrimitive(10),
                    "startDate" to JsonPrimitive("2017-10-26T04:56:25.046Z"),
                    "endDate" to JsonPrimitive("2017-10-26T04:56:25.046Z")
                ))
            ))
        ))
    ))

fun giftingQuoteRequest(): DigitalPayGiftingQuoteRequest =
    DigitalPayGiftingQuoteRequest(
        orderItems = listOf(
            GiftingProductOrderItem(
                designId = "design-1",
                amount = BigDecimal(100),
                quantity = 1,
                isGifting = true,
                recipientDetails = RecipientDetail(
                    toName = "John Smith",
                    fromName = "Jane Smith",
                    message = "Test message",
                    imageUrl = "http://test.com",
                    mobileNumber = "+61444555666"
                )
            )
        )
    )

fun giftingQuoteResponse(): JsonObject =
    JsonObject(mapOf(
        "quoteId" to JsonPrimitive("quote-1"),
        "subTotalAmount" to JsonPrimitive(100.0),
        "discountAmount" to JsonPrimitive(10.0),
        "totalOrderAmount" to JsonPrimitive(90.0),
        "orderItems" to JsonObject(mapOf(
            "designId" to JsonPrimitive("design-1"),
            "amount" to JsonPrimitive(100.0),
            "unitPrice" to JsonPrimitive(100.0),
            "totalPrice" to JsonPrimitive(100.0),
            "quantity" to JsonPrimitive(1),
            "isGifting" to JsonPrimitive(true),
            "recipientDetails" to JsonObject(mapOf(
                "toName" to JsonPrimitive("John Smith"),
                "fromName" to JsonPrimitive("Jane Smith"),
                "message" to JsonPrimitive("Test message"),
                "imageUrl" to JsonPrimitive("http://test.com"),
                "mobileNumber" to JsonPrimitive("+61444555666")
            ))
        ))
    ))

fun giftingOrderRequest(): DigitalPayGiftingOrderRequest =
    DigitalPayGiftingOrderRequest(
        instrumentId = "12345",
        deliveryEmail = "test@test.com",
        referenceId = "reference-1",
        subTotalAmount = BigDecimal(100.0),
        discountAmount = BigDecimal(10.0),
        totalOrderAmount = BigDecimal(90.0),
        billingContact = GiftingBillingContact(
            firstName = "Jane",
            lastName = "Smith",
            email = "jane.smith@test.com",
            mobileNumber = "+61444555666",
            streetAddress = "123 Test St",
            extendedAddress = "Unit 1",
            suburb = "Sydney",
            stateOrTerritory = "NSW",
            postalCode = "2000",
            countryCode = "AU"
        ),
        orderItems = listOf(
            GiftingProductOrderItem(
                designId = "design-1",
                amount = BigDecimal(100.0),
                quantity = 1,
                isGifting = true,
                recipientDetails = RecipientDetail(
                    toName = "John Smith",
                    fromName = "Jane Smith",
                    message = "Test message",
                    imageUrl = "http://test.com",
                    mobileNumber = "+61444555666"
                )
            )
        )
    )

fun giftingOrderResponse(): JsonObject =
    JsonObject(mapOf(
        "status" to JsonPrimitive("STATUS"),
        "orderId" to JsonPrimitive("order-1"),
        "quoteNo" to JsonPrimitive("quote-1")
    ))
