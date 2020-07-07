package au.com.woolworths.village.sdk.openapi.api.matchers

import au.com.woolworths.village.sdk.openapi.dto.MerchantPayload
import au.com.woolworths.village.sdk.openapi.dto.MerchantPaymentDetail
import au.com.woolworths.village.sdk.openapi.dto.MerchantPaymentSummary
import au.com.woolworths.village.sdk.openapi.dto.PosPayload
import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun withMerchantPaymentSummary(): MerchantPaymentSummaryMatcher {
    return MerchantPaymentSummaryMatcher()
}

open class MerchantPaymentSummaryMatcher: TypeSafeMatcher<MerchantPaymentSummary>() {
    override fun matchesSafely(item: MerchantPaymentSummary): Boolean {
        assertThat(item.paymentRequestId, not(blankOrNullString()))
        assertThat(item.merchantReferenceId, not(blankOrNullString()))
        assertThat(item.grossAmount, not(nullValue()))
        assertThat(item.usesRemaining, not(nullValue()))
        assertThat(item.expiryTime, not(nullValue()))
        assertThat(item.specificWalletId, not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant Payment Summary")
    }
}

fun hasMerchantPaymentDetails(): MerchantPaymentDetailsMatcher {
    return MerchantPaymentDetailsMatcher()
}

class MerchantPaymentDetailsMatcher: TypeSafeMatcher<MerchantPaymentDetail>() {
    override fun matchesSafely(item: MerchantPaymentDetail): Boolean {
        assertThat(item.paymentRequestId, not(blankOrNullString()))
        assertThat(item.merchantReferenceId, not(blankOrNullString()))
        assertThat(item.grossAmount, not(nullValue()))
        assertThat(item.usesRemaining, not(nullValue()))
        assertThat(item.expiryTime, not(nullValue()))
        assertThat(item.specificWalletId, not(blankOrNullString()))
        assertThat(item.basket, hasBasketItems())
        assertThat(item.posPayload, isPosPayload())
        assertThat(item.merchantPayload, isMerchantPayload())

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant Payment Details")
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