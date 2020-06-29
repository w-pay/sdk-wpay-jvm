package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.openapi.dto.BasketItems
import au.com.woolworths.village.sdk.model.Basket
import java.math.BigDecimal

class OpenApiBasket(
    private val basket: au.com.woolworths.village.sdk.openapi.dto.Basket
) : Basket {
    override fun items(): List<Basket.Items> {
        if (basket.items != null && basket.items!!.size > 0) {
            return basket.items!!.map { OpenApiBasketItem(it) }
        }

        return emptyList()
    }
}

class OpenApiBasketItem(
    private val item: BasketItems
): Basket.Items {
    override fun label(): String {
        return item.label
    }

    override fun description(): String? {
       return item.description
    }

    override fun quantity(): BigDecimal? {
       return item.quantity
    }

    override fun unitPrice(): BigDecimal? {
        return item.unitPrice
    }

    override fun unitMeasure(): String? {
        return item.unitMeasure
    }

    override fun totalPrice(): BigDecimal? {
        return item.totalPrice
    }

    override fun tags(): Map<String, String> {
        if (item.tags != null) {
            return item.tags as Map<String, String>
        }

        return emptyMap()
    }
}