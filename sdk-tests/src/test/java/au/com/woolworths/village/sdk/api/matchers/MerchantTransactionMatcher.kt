package au.com.woolworths.village.sdk.api.matchers

import au.com.woolworths.village.sdk.dto.MerchantTransactionDetail
import au.com.woolworths.village.sdk.dto.MerchantTransactionSummary
import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun hasMerchantTransactionSummary(): MerchantTransactionSummaryMatcher {
    return MerchantTransactionSummaryMatcher()
}

fun withMerchantTransactionSummary(): MerchantTransactionSummaryMatcher {
    return MerchantTransactionSummaryMatcher()
}

class MerchantTransactionSummaryMatcher: TypeSafeMatcher<MerchantTransactionSummary>() {
    override fun matchesSafely(item: MerchantTransactionSummary): Boolean {
        assertThat(item.merchantReferenceId, not(blankOrNullString()))
        assertThat(item.walletId, not(blankOrNullString()))
        assertThat(item.paymentRequestId, not(blankOrNullString()))
        assertThat(item.type, not(nullValue()))
        assertThat(item.grossAmount, not(nullValue()))
        // TODO: assertThat(item.executionTime, not(blankOrNullString()))
        // TODO: assertThat(item.payments, not(blankOrNullString()))
        // TODO: assertThat(item.status, not(blankOrNullString()))
        assertThat(item.transactionId, not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant Transaction Summary")
    }
}

fun hasMerchantTransactionDetails(): MerchantTransactionDetailMatcher {
    return MerchantTransactionDetailMatcher()
}

class MerchantTransactionDetailMatcher: TypeSafeMatcher<MerchantTransactionDetail>() {
    override fun matchesSafely(item: MerchantTransactionDetail): Boolean {
        assertThat(item.merchantReferenceId, not(blankOrNullString()))
        assertThat(item.walletId, not(blankOrNullString()))
        assertThat(item.paymentRequestId, not(blankOrNullString()))
        assertThat(item.type, not(nullValue()))
        assertThat(item.grossAmount, not(nullValue()))
        // TODO: assertThat(item.executionTime, not(blankOrNullString()))
        // TODO: assertThat(item.payments, not(blankOrNullString()))
        // TODO: assertThat(item.status, not(blankOrNullString()))
        assertThat(item.transactionId, not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant Transaction Details")
    }
}