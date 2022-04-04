package au.com.wpay.sdk.matchers

import au.com.wpay.sdk.model.digitalpay.*
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun giftingProductListFrom(dto: JsonObject): Matcher<DigitalPayGiftingProducts> =
    object : Matcher<DigitalPayGiftingProducts> {
        override fun test(value: DigitalPayGiftingProducts): MatcherResult =
            MatcherResult.test(
                MatcherResult.forEach<JsonElement, DigitalPayGiftingProduct>(::giftingProductFrom),
                dto["products"]?.jsonArray,
                value.products
            )
    }

fun giftingProductFrom(dto: JsonElement): Matcher<DigitalPayGiftingProduct> =
    giftingProductFrom(dto.jsonObject)

fun giftingProductFrom(dto: JsonObject): Matcher<DigitalPayGiftingProduct> =
    object : Matcher<DigitalPayGiftingProduct> {
        override fun test(value: DigitalPayGiftingProduct): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["productId"]?.content(), value.productId),
                MatcherResult.test(::equal, dto["name"]?.content(), value.name),
                MatcherResult.test(::equal, dto["barCodeType"]?.toUpperCase(), value.barCodeType.toString()),
                MatcherResult.test(::equal, dto["lastUpdateDateTime"]?.toDate(), value.lastUpdateDateTime),
                MatcherResult.test(::giftingProductDesignFrom, dto["defaultDesign"], value.defaultDesign),
                MatcherResult.test(::giftingProductDiscountFrom, dto["discountOffered"], value.discountOffered),
            )
    }

fun giftingProductDetailFrom(dto: JsonObject): Matcher<DigitalPayGiftingProductDetail> =
    object : Matcher<DigitalPayGiftingProductDetail> {
        override fun test(value: DigitalPayGiftingProductDetail): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["productId"]?.content(), value.productId),
                MatcherResult.test(::equal, dto["name"]?.content(), value.name),
                MatcherResult.test(::equal, dto["barCodeType"]?.toUpperCase(), value.barCodeType.toString()),
                MatcherResult.test(::equal, dto["lastUpdateDateTime"]?.toDate(), value.lastUpdateDateTime),
                MatcherResult.test(::giftingProductDesignFrom, dto["defaultDesign"], value.defaultDesign),
                MatcherResult.test(::giftingProductDiscountFrom, dto["discountOffered"], value.discountOffered),
                MatcherResult.test(::equal, dto["redemptionInstructions"]?.content(), value.redemptionInstructions),
                MatcherResult.test(::equal, dto["redemptionType"]?.toUpperCase(), value.redemptionType.toString()),
                MatcherResult.test(::equal, dto["termsAndConditions"]?.content(), value.termsAndConditions),
                MatcherResult.test(::equal, dto["minValue"]?.toInt(), value.minValue),
                MatcherResult.test(::equal, dto["maxValue"]?.toInt(), value.maxValue),
                MatcherResult.test(::equal, dto["expiryPeriodInDays"]?.toInt(), value.expiryPeriodInDays),
                MatcherResult.test(::equal, dto["expiryPeriodText"]?.content(), value.expiryPeriodText),
                MatcherResult.test(::equal, dto["isActive"]?.toBoolean(), value.isActive),
                MatcherResult.test(
                    MatcherResult.forEach { el -> PredicateMatcher(::equal, el.content()) },
                    dto["redemptionStores"]?.jsonArray,
                    value.redemptionStores
                ),
                MatcherResult.test(::equal, dto["availability"]?.toUpperCase(), value.availability.toString())
            )
    }

fun giftingProductDesignFrom(dto: JsonElement): Matcher<GiftingProductDesign> =
    giftingProductDesignFrom(dto.jsonObject)

fun giftingProductDesignFrom(dto: JsonObject): Matcher<GiftingProductDesign> =
    object : Matcher<GiftingProductDesign> {
        override fun test(value: GiftingProductDesign): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["designId"]?.content(), value.designId),
                MatcherResult.test(::equal, dto["designType"]?.toUpperCase(), value.designType.toString()),
                MatcherResult.test(::equal, dto["imageUrl"]?.content(), value.imageUrl)
            )
    }

fun giftingProductDiscountFrom(dto: JsonElement): Matcher<GiftingProductDiscount> =
    giftingProductDiscountFrom(dto.jsonObject)

fun giftingProductDiscountFrom(dto: JsonObject): Matcher<GiftingProductDiscount> =
    object : Matcher<GiftingProductDiscount> {
        override fun test(value: GiftingProductDiscount): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["discountId"]?.content(), value.discountId),
                MatcherResult.test(::equal, dto["description"]?.content(), value.description),
                MatcherResult.test(::equal, dto["percentageDiscount"]?.toInt(), value.percentageDiscount),
                MatcherResult.test(::equal, dto["startDate"]?.toDate(), value.startDate),
                MatcherResult.test(::equal, dto["endDate"]?.toDate(), value.endDate)
            )
    }

fun giftingQuoteFrom(dto: JsonObject): Matcher<DigitalPayGiftingQuoteResponse> =
    object : Matcher<DigitalPayGiftingQuoteResponse> {
        override fun test(value: DigitalPayGiftingQuoteResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["quoteId"]?.content(), value.quoteId),
                MatcherResult.test(::equal, dto["subTotalAmount"]?.toDecimal(), value.subTotalAmount),
                MatcherResult.test(::equal, dto["discountAmount"]?.toDecimal(), value.discountAmount),
                MatcherResult.test(::equal, dto["totalOrderAmount"]?.toDecimal(), value.totalOrderAmount),
                MatcherResult.test(::giftingQuoteResponseItemFrom, dto["orderItems"], value.orderItems)
            )
    }

fun giftingQuoteResponseItemFrom(dto: JsonElement): Matcher<GiftingProductQuoteResponseItem> =
    giftingQuoteResponseItemFrom(dto.jsonObject)

fun giftingQuoteResponseItemFrom(dto: JsonObject): Matcher<GiftingProductQuoteResponseItem> =
    object : Matcher<GiftingProductQuoteResponseItem> {
        override fun test(value: GiftingProductQuoteResponseItem): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["designId"]?.content(), value.designId),
                MatcherResult.test(::equal, dto["amount"]?.toDecimal(), value.amount),
                MatcherResult.test(::equal, dto["unitPrice"]?.toDecimal(), value.unitPrice),
                MatcherResult.test(::equal, dto["totalPrice"]?.toDecimal(), value.totalPrice),
                MatcherResult.test(::equal, dto["quantity"]?.toInt(), value.quantity),
                MatcherResult.test(::equal, dto["isGifting"]?.toBoolean(), value.isGifting),
                MatcherResult.test(::equal, dto["mobileNumber"]?.content(), value.mobileNumber)
            )
    }

fun giftingOrderResponseFrom(dto: JsonObject): Matcher<DigitalPayGiftingOrderResponse> =
    object : Matcher<DigitalPayGiftingOrderResponse> {
        override fun test(value: DigitalPayGiftingOrderResponse): MatcherResult =
            Matcher.tests(
                MatcherResult.test(::equal, dto["status"]?.content(), value.status),
                MatcherResult.test(::equal, dto["orderId"]?.content(), value.orderId),
                MatcherResult.test(::equal, dto["quoteNo"]?.content(), value.quoteNo)
            )
    }
