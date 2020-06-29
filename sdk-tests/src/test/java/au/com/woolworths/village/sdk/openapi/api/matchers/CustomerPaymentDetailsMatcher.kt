package au.com.woolworths.village.sdk.openapi.api.matchers

import au.com.woolworths.village.sdk.openapi.dto.CustomerPaymentDetail

import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

import java.math.BigDecimal

fun hasCustomerPaymentDetails(): CustomerPaymentDetailsMatcher {
    return CustomerPaymentDetailsMatcher()
}

class CustomerPaymentDetailsMatcher : TypeSafeMatcher<CustomerPaymentDetail>() {
    override fun matchesSafely(item: CustomerPaymentDetail): Boolean {
        assertThat(item, not(nullValue()))
        assertThat(item.paymentRequestId, not(blankOrNullString()))
        assertThat(item.merchantId, not(blankOrNullString()))
        assertThat(item.merchantReferenceId, not(blankOrNullString()))
        assertThat(item.grossAmount, greaterThan(BigDecimal.ZERO))
        assertThat(item.basket, hasBasketItems())

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A customer payment results with payment details")
    }
}