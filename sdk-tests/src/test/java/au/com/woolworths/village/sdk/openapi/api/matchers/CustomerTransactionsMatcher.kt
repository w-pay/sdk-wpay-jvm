package au.com.woolworths.village.sdk.openapi.api.matchers

import au.com.woolworths.village.sdk.openapi.dto.CustomerTransactionDetail
import au.com.woolworths.village.sdk.openapi.dto.CustomerTransactionSummary
import au.com.woolworths.village.sdk.openapi.dto.CustomerTransactionSummaryAllOfInstruments
import au.com.woolworths.village.sdk.openapi.dto.GetCustomerTransactionsResultData
import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun hasCustomerTransactions(): CustomerTransactionsMatcher {
    return CustomerTransactionsMatcher()
}

fun hasCustomerTransactionDetails(): CustomerTransactionDetailMatcher {
    return CustomerTransactionDetailMatcher()
}

fun hasCustomerTransactionSummary(): CustomerTransactionSummaryMatcher {
    return CustomerTransactionSummaryMatcher()
}

class CustomerTransactionsMatcher: TypeSafeMatcher<GetCustomerTransactionsResultData>() {
    override fun matchesSafely(item: GetCustomerTransactionsResultData): Boolean {
        val transactionMatcher = CustomerTransactionSummaryMatcher()

        assertThat(item.transactions.size, greaterThan(0))

        return item.transactions.fold(true, { result, it ->
            return result && transactionMatcher.matches(it)
        })
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of customer transactions")
    }
}

class CustomerTransactionDetailMatcher: TypeSafeMatcher<CustomerTransactionDetail>() {
    override fun matchesSafely(item: CustomerTransactionDetail): Boolean {
        assertThat(item.transactionId, not(blankOrNullString()))
        assertThat(item.type, not(nullValue()))
        assertThat(item.executionTime, not(nullValue()))
        assertThat(item.status, not(nullValue()))
        assertThat(item.statusDetail, not(nullValue()))
        assertThat(item.refundReason, blankOrNullString())
        assertThat(item.paymentRequestId, not(blankOrNullString()))
        assertThat(item.merchantReferenceId, not(blankOrNullString()))
        assertThat(item.grossAmount, not(nullValue()))
        assertThat(item.merchantId, not(blankOrNullString()))
        assertThat(item.instruments, hasItems(withCustomerPaymentInstruments()))
        assertThat(item.basket, hasBasketItems())

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A Customer Transaction Detail")
    }
}

class CustomerTransactionSummaryMatcher: TypeSafeMatcher<CustomerTransactionSummary>() {
    override fun matchesSafely(item: CustomerTransactionSummary): Boolean {
        assertThat(item.merchantId, not(blankOrNullString()))
        assertThat(item.merchantReferenceId, not(blankOrNullString()))
        assertThat(item.paymentRequestId, not(blankOrNullString()))
        assertThat(item.type, not(nullValue()))
        assertThat(item.grossAmount, not(nullValue()))
        assertThat(item.executionTime, not(nullValue()))
        assertThat(item.status, not(nullValue()))
        assertThat(item.statusDetail, not(nullValue()))
        assertThat(item.instruments.size, greaterThanOrEqualTo(1))
        assertThat(item.instruments, hasItems(withCustomerPaymentInstruments()))
        assertThat(item.transactionId, not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A Customer Transaction Summary")
    }
}

fun withCustomerPaymentInstruments(): CustomerPaymentInstrumentsMatcher {
    return CustomerPaymentInstrumentsMatcher()
}

class CustomerPaymentInstrumentsMatcher: TypeSafeMatcher<CustomerTransactionSummaryAllOfInstruments>() {
    override fun matchesSafely(item: CustomerTransactionSummaryAllOfInstruments): Boolean {
        assertThat(item.paymentInstrumentId, not(blankOrNullString()))
        assertThat(item.amount, not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Customer Transaction Summary with instruments")
    }

}