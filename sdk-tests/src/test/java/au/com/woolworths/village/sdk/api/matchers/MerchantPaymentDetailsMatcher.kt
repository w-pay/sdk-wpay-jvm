package au.com.woolworths.village.sdk.api.matchers

import au.com.woolworths.village.sdk.dto.MerchantPayload
import au.com.woolworths.village.sdk.dto.MerchantPaymentDetail
import au.com.woolworths.village.sdk.dto.MerchantPaymentSummary
import au.com.woolworths.village.sdk.dto.PosPayload
import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun withMerchantPaymentSummary(): MerchantPaymentSummaryMatcher<MerchantPaymentSummary> {
    return MerchantPaymentSummaryMatcher()
}

open class MerchantPaymentSummaryMatcher<T: MerchantPaymentSummary>: TypeSafeMatcher<T>() {
    override fun matchesSafely(item: T): Boolean {
        assertThat(item.paymentRequestId, not(blankOrNullString()))
        assertThat(item.merchantReferenceId, not(blankOrNullString()))
        assertThat(item.grossAmount, not(nullValue()))
        assertThat(item.usesRemaining, not(nullValue()))
        // TODO: assertThat(item.createdTime, not(blankOrNullString()))
        assertThat(item.expiryTime, not(blankOrNullString()))
        assertThat(item.specificWalletId, not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant payment summary")
    }
}

fun hasMerchantPaymentDetails(): MerchantPaymentDetailsMatcher {
    return MerchantPaymentDetailsMatcher()
}

class MerchantPaymentDetailsMatcher: MerchantPaymentSummaryMatcher<MerchantPaymentDetail>() {
    override fun matchesSafely(item: MerchantPaymentDetail): Boolean {
        assertThat(item.basket, hasBasketItems())
        assertThat(item.posPayload, isPosPayload())
        assertThat(item.merchantPayload, isMerchantPayload())

        return super.matchesSafely(item)
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant payment details")
    }
}

fun isPosPayload(): PosPayloadMatcher {
    return PosPayloadMatcher()
}

class PosPayloadMatcher: TypeSafeMatcher<PosPayload>() {
    override fun matchesSafely(item: PosPayload): Boolean {
        assertThat(item.schemaId, not(blankOrNullString()))
        assertThat(item.payload, not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Pos payload details")
    }

}

fun isMerchantPayload(): MerchantPayloadMatcher {
    return MerchantPayloadMatcher()
}

class MerchantPayloadMatcher: TypeSafeMatcher<MerchantPayload>() {
    override fun matchesSafely(item: MerchantPayload): Boolean {
        assertThat(item.schemaId, not(blankOrNullString()))
        assertThat(item.payload, not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant payload details")
    }
}