package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.CustomerTransactionDetails
import au.com.woolworths.village.sdk.model.CustomerTransactionSummaries
import au.com.woolworths.village.sdk.model.CustomerTransactionSummary
import au.com.woolworths.village.sdk.model.CustomerTransactions
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString
import org.junit.Assert.assertThat

fun customerTransactionSummaries(): Matcher<CustomerTransactionSummaries> {
    return CustomerTransactionsSummariesMatcher()
}

class CustomerTransactionsSummariesMatcher: TypeSafeMatcher<CustomerTransactionSummaries>() {
    override fun matchesSafely(item: CustomerTransactionSummaries): Boolean {
        val transactionMatcher = CustomerTransactionSummaryMatcher()

        MatcherAssert.assertThat(item.transactions().size, greaterThan(0))

        return item.transactions().fold(true, { result, it ->
            return result && transactionMatcher.matches(it)
        })
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of customer transaction summaries")
    }
}

fun customerTransactionSummary(): Matcher<CustomerTransactionSummary> {
    return CustomerTransactionSummaryMatcher()
}

open class CustomerTransactionSummaryMatcher: TypeSafeMatcher<CustomerTransactionSummary>() {
    override fun matchesSafely(item: CustomerTransactionSummary): Boolean {
        assertThat(item.merchantId(), not(blankOrNullString()))
        assertThat(item.merchantReferenceId(), not(blankOrNullString()))
        assertThat(item.paymentRequestId(), not(blankOrNullString()))
        assertThat(item.type(), not(nullValue()))
        assertThat(item.grossAmount(), not(nullValue()))
        assertThat(item.executionTime(), not(nullValue()))
        assertThat(item.status(), not(nullValue()))
        assertThat(item.instruments().size, greaterThanOrEqualTo(1))
        assertThat(item.instruments(), hasItems(withCustomerPaymentInstruments()))
        assertThat(item.transactionId(), not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A Customer Transaction Summary")
    }
}

fun customerTransactionDetails(): Matcher<CustomerTransactionDetails> = CustomerTransactionDetailsMatcher()

class CustomerTransactionDetailsMatcher: TypeSafeMatcher<CustomerTransactionDetails>() {
    private val summaryMatcher: CustomerTransactionSummaryMatcher = CustomerTransactionSummaryMatcher()

    override fun matchesSafely(item: CustomerTransactionDetails): Boolean {
        assertThat(item.basket(), isBasket())

        return summaryMatcher.matches(item)
    }

    override fun describeTo(description: Description) {
        description.appendText("Details on a customer transaction")
    }
}

fun withCustomerPaymentInstruments(): CustomerPaymentInstrumentsMatcher {
    return CustomerPaymentInstrumentsMatcher()
}

class CustomerPaymentInstrumentsMatcher: TypeSafeMatcher<CustomerTransactions.UsedPaymentInstrument>() {
    override fun matchesSafely(item: CustomerTransactions.UsedPaymentInstrument): Boolean {
        MatcherAssert.assertThat(item.paymentInstrumentId(), not(blankOrNullString()))
        MatcherAssert.assertThat(item.amount(), not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Customer Transaction Summary with instruments")
    }
}