package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.CreatePaymentRequestResult
import au.com.woolworths.village.sdk.model.MerchantPaymentDetails
import au.com.woolworths.village.sdk.model.MerchantPaymentSummaries
import au.com.woolworths.village.sdk.model.MerchantPaymentSummary
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString
import org.junit.Assert.assertThat

fun merchantPaymentSummaries(): Matcher<MerchantPaymentSummaries> {
    return MerchantPaymentSummariesMatcher()
}

class MerchantPaymentSummariesMatcher: TypeSafeMatcher<MerchantPaymentSummaries>() {
    override fun matchesSafely(item: MerchantPaymentSummaries): Boolean {
        val matcher = MerchantPaymentSummaryMatcher()

        assertThat(item.payments().size, greaterThanOrEqualTo(1))

        return item.payments().fold(true, { result, it -> result && matcher.matches(it) })
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of payments")
    }
}

class MerchantPaymentSummaryMatcher: TypeSafeMatcher<MerchantPaymentSummary>() {
    override fun matchesSafely(item: MerchantPaymentSummary): Boolean {
        assertThat(item.paymentRequestId(), not(blankOrNullString()))
        assertThat(item.merchantReferenceId(), not(blankOrNullString()))
        assertThat(item.grossAmount(), not(nullValue()))
        assertThat(item.usesRemaining(), not(nullValue()))
        assertThat(item.expiryTime(), not(nullValue()))
        assertThat(item.specificWalletId(), not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A merchant payment summary")
    }
}

fun merchantPaymentDetails(): Matcher<MerchantPaymentDetails> {
    return MerchantPaymentDetailsMatcher()
}

class MerchantPaymentDetailsMatcher: TypeSafeMatcher<MerchantPaymentDetails>() {
    override fun matchesSafely(item: MerchantPaymentDetails): Boolean {
        assertThat(item.paymentRequestId(), not(blankOrNullString()))
        assertThat(item.merchantReferenceId(), not(blankOrNullString()))
        assertThat(item.grossAmount(), not(nullValue()))
        assertThat(item.usesRemaining(), not(nullValue()))
        assertThat(item.expiryTime(), not(nullValue()))
        assertThat(item.specificWalletId(), not(blankOrNullString()))
        assertThat(item.basket(), isBasket())
        assertThat(item.posPayload(), isPosPayload())
        assertThat(item.merchantPayload(), isMerchantPayload())

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant Payment Details")
    }
}

fun paymentRequestCreated(): Matcher<CreatePaymentRequestResult> {
    return CreatePaymentRequestResultMatcher()
}

class CreatePaymentRequestResultMatcher: TypeSafeMatcher<CreatePaymentRequestResult>() {
    override fun matchesSafely(item: CreatePaymentRequestResult): Boolean {
        assertThat(item.paymentRequestId(), not(blankOrNullString()))
        assertThat(item.qr()!!, isAQrCode())

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A payment request result")
    }
}