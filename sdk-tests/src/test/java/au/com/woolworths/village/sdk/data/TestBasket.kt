package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.Basket
import java.math.BigDecimal

fun aNewBasket(): Basket = TestBasket()

class TestBasket: Basket {
    override fun items(): List<Basket.Items> {
        return arrayListOf(TestBasketItem())
    }
}

class TestBasketItem: Basket.Items {
    override fun label(): String {
        return "Item 1"
    }

    override fun description(): String? {
       return "This is item 1"
    }

    override fun quantity(): BigDecimal? {
        return BigDecimal(3)
    }

    override fun unitPrice(): BigDecimal? {
        return BigDecimal(2.1)
    }

    override fun unitMeasure(): String? {
        return "EACH"
    }

    override fun totalPrice(): BigDecimal? {
       return BigDecimal(6.3)
    }

    override fun tags(): Map<String, String> {
        return hashMapOf(
            "property1" to "string",
            "property2" to "string"
        )
    }
}