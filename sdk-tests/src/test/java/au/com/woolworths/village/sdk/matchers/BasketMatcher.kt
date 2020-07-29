package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.Basket
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString
import java.math.BigDecimal

fun isBasket(): Matcher<Basket?> = BasketMatcher()

class BasketMatcher: TypeSafeMatcher<Basket>() {
    override fun matchesSafely(basket: Basket): Boolean {
        val matcher = BasketItemMatcher()
        val items = basket.items()

        assertThat(items, not(nullValue()))
        assertThat(items.size, greaterThanOrEqualTo(1))

        return items.fold(true, { result, item -> result and matcher.matches(item) })
    }

    override fun describeTo(description: Description) {
        description.appendText("A basket with at least one item")
    }

    class BasketItemMatcher: TypeSafeMatcher<Basket.Items>() {
        override fun matchesSafely(item: Basket.Items): Boolean {
            assertThat(item, not(nullValue()))
            assertThat(item.label(), not(blankOrNullString()))
            assertThat(item.description(), not(blankOrNullString()))
            assertThat(item.quantity(), greaterThan(BigDecimal.ZERO))
            assertThat(item.unitPrice(), greaterThan(BigDecimal.ZERO))
            assertThat(item.unitMeasure(), not(blankOrNullString()))
            assertThat(item.totalPrice(), greaterThan(BigDecimal.ZERO))
            assertThat(item.tags(), not(nullValue()))
            assertThat(item.tags().size, greaterThan(0))

            return true
        }

        override fun describeTo(description: Description) {
            description.appendText("An item with all properties")
        }
    }
}