package au.com.woolworths.village.sdk.api.matchers

import au.com.woolworths.village.sdk.dto.Basket
import au.com.woolworths.village.sdk.dto.BasketItems

import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

import java.math.BigDecimal

fun hasBasketItems(): BasketItemsMatcher {
    return BasketItemsMatcher()
}

class BasketItemsMatcher : TypeSafeMatcher<Basket>() {
    override fun matchesSafely(basket: Basket): Boolean {
        val matcher = BasketItemMatcher()
        var result = true

        assertThat(basket.items, not(nullValue()))

        for (item in basket.items!!) {
            result = result and matcher.matches(item)
        }

        return result
    }

    override fun describeTo(description: Description) {
        description.appendText("A basket with at least one item")
    }
}

class BasketItemMatcher : TypeSafeMatcher<BasketItems>() {
    override fun matchesSafely(item: BasketItems): Boolean {
        assertThat(item, not(nullValue()))
        assertThat(item.label, not(blankOrNullString()))
        assertThat(item.description, not(blankOrNullString()))
        assertThat(item.quantity, greaterThan(BigDecimal.ZERO))
        assertThat(item.unitPrice, greaterThan(BigDecimal.ZERO))
        assertThat(item.unitMeasure, not(blankOrNullString()))
        assertThat(item.totalPrice, greaterThan(BigDecimal.ZERO))
        assertThat(item.tags, not(nullValue()))
        assertThat(item.tags!!.size, greaterThan(0))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("An item with all properties")
    }
}