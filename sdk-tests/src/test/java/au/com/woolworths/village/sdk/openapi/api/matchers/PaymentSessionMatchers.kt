package au.com.woolworths.village.sdk.openapi.api.matchers

import au.com.woolworths.village.sdk.openapi.dto.CreateMerchantPaymentSessionResponseData
import au.com.woolworths.village.sdk.openapi.dto.PaymentSession
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun hasPaymentSession(): Matcher<PaymentSession> = PaymentSessionMatcher()

class PaymentSessionMatcher: TypeSafeMatcher<PaymentSession>() {
    override fun matchesSafely(item: PaymentSession): Boolean {
        assertThat(item.paymentSessionId, not(blankOrNullString()))
        assertThat(item.merchantId, not(blankOrNullString()))
        assertThat(item.walletId, nullValue())
        assertThat(item.expiryTime, not(nullValue()))
        assertThat(item.location, not(blankOrNullString()))
        assertThat(item.additionalInfo, not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A payment session")
    }
}

fun hasPaymentSessionCreated(): Matcher<CreateMerchantPaymentSessionResponseData> = CreatePaymentSessionResultMatcher()

class CreatePaymentSessionResultMatcher: TypeSafeMatcher<CreateMerchantPaymentSessionResponseData>() {
    override fun matchesSafely(item: CreateMerchantPaymentSessionResponseData): Boolean {
        assertThat(item.paymentSessionId, not(blankOrNullString()))
        assertThat(item.qr, isQrCode())

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A created payment session")
    }
}