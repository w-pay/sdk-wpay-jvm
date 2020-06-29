package au.com.woolworths.village.sdk.openapi.api.data

import au.com.woolworths.village.sdk.openapi.dto.Basket
import au.com.woolworths.village.sdk.openapi.dto.BasketItems
import java.math.BigDecimal

fun basketWithItem(): Basket {
    val basket = Basket()
    basket.addItemsItem(basketItem())

    return basket
}

fun basketItem(): BasketItems {
    val item = BasketItems()
    item.label = "Item 1"
    item.description = "This is item 1"
    item.quantity = BigDecimal(3)
    item.unitPrice = BigDecimal(2.1)
    item.unitMeasure = "EACH"
    item.totalPrice = BigDecimal(6.3)
    item.tags = hashMapOf(
        "property1" to "string",
        "property2" to "string"
    )

    return item
}