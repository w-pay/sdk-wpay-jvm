package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.CreatePaymentSessionResult
import au.com.woolworths.village.sdk.model.PaymentSession
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString
import org.junit.Assert.assertThat

fun paymentSession(): Matcher<PaymentSession> = PaymentSessionMatcher()

class PaymentSessionMatcher: TypeSafeMatcher<PaymentSession>() {
    override fun matchesSafely(item: PaymentSession): Boolean {
        assertThat(item.paymentSessionId(), not(blankOrNullString()))
        assertThat(item.merchantId(), not(blankOrNullString()))
        assertThat(item.walletId(), nullValue())
        assertThat(item.expiryTime(), not(nullValue()))
        assertThat(item.location(), not(blankOrNullString()))
        assertThat(item.additionalInfo(), not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A payment session")
    }
}

fun paymentSessionCreated(): Matcher<CreatePaymentSessionResult> = CreatePaymentSessionResultMatcher()

class CreatePaymentSessionResultMatcher: TypeSafeMatcher<CreatePaymentSessionResult>() {
    override fun matchesSafely(item: CreatePaymentSessionResult): Boolean {
        assertThat(item.paymentSessionId(), not(blankOrNullString()))
        assertThat(item.qr()!!, isAQrCode())

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A created payment session")
    }

}