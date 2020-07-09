package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.MerchantTransactionDetails
import au.com.woolworths.village.sdk.model.MerchantTransactionSummaries
import au.com.woolworths.village.sdk.model.MerchantTransactionSummary
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString
import org.junit.Assert.assertThat

fun merchantTransactionSummaries(): Matcher<MerchantTransactionSummaries> {
    return MerchantTransactionsSummariesMatcher()
}

class MerchantTransactionsSummariesMatcher: TypeSafeMatcher<MerchantTransactionSummaries>() {
    override fun matchesSafely(item: MerchantTransactionSummaries): Boolean {
        val transactionMatcher = MerchantTransactionSummaryMatcher()

        assertThat(item.transactions().size, Matchers.greaterThan(0))

        return item.transactions().fold(true, { result, it ->
            return result && transactionMatcher.matches(it)
        })
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of merchant transactions")
    }
}

fun merchantTransactionSummary(): Matcher<MerchantTransactionSummary> {
    return MerchantTransactionSummaryMatcher()
}

open class MerchantTransactionSummaryMatcher: TypeSafeMatcher<MerchantTransactionSummary>() {
    override fun matchesSafely(item: MerchantTransactionSummary): Boolean {
        assertThat(item.walletId(), not(blankOrNullString()))
        assertThat(item.merchantReferenceId(), not(blankOrNullString()))
        assertThat(item.paymentRequestId(), not(blankOrNullString()))
        assertThat(item.type(), not(nullValue()))
        assertThat(item.grossAmount(), not(nullValue()))
        assertThat(item.executionTime(), not(nullValue()))
        assertThat(item.status(), not(nullValue()))
        assertThat(item.transactionId(), not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A Merchant Transaction Summary")
    }
}

fun merchantTransactionDetails(): Matcher<MerchantTransactionDetails> {
    return MerchantTransactionDetailsMatcher()
}

class MerchantTransactionDetailsMatcher: TypeSafeMatcher<MerchantTransactionDetails>() {
    private val summaryMatcher = MerchantTransactionSummaryMatcher()

    override fun matchesSafely(item: MerchantTransactionDetails): Boolean {
        assertThat(item.basket(), nullValue())
        assertThat(item.posPayload(), nullValue())
        assertThat(item.merchantPayload(), nullValue())

        return summaryMatcher.matches(item)
    }

    override fun describeTo(description: Description) {
        description.appendText("A Merchant Transaction Summary")
    }
}