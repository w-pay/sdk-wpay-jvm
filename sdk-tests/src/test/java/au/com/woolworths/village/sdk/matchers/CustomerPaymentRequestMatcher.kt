package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.CustomerPaymentRequest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString
import org.junit.Assert.assertThat

fun customerPaymentRequest(): Matcher<CustomerPaymentRequest> = CustomerPaymentRequestMatcher()

class CustomerPaymentRequestMatcher: TypeSafeMatcher<CustomerPaymentRequest>() {
    override fun matchesSafely(item: CustomerPaymentRequest): Boolean {
        assertThat(item.merchantId(), not(blankOrNullString()))
        assertThat(item.basket(), isBasket())

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A customer payment request")
    }
}