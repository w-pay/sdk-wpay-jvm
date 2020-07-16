package au.com.woolworths.village.sdk.openapi.api.matchers

import au.com.woolworths.village.sdk.openapi.dto.GetCustomerPaymentInstrumentsResultsData

import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun hasCreditCards(): CreditCardsMatcher {
    return CreditCardsMatcher()
}

fun hasGiftCards(): GiftCardsMatcher {
    return GiftCardsMatcher()
}

class CreditCardsMatcher : TypeSafeMatcher<GetCustomerPaymentInstrumentsResultsData>() {
    override fun matchesSafely(item: GetCustomerPaymentInstrumentsResultsData): Boolean {
        val creditCards = item.creditCards

        assertThat(creditCards, not(nullValue()))
        assertThat(creditCards.size, greaterThanOrEqualTo(1))

        creditCards.forEach {
            assertThat(it.allowed, not(nullValue()))
            assertThat(it.cardName, not(blankOrNullString()))
            assertThat(it.cardSuffix, not(blankOrNullString()))
            assertThat(it.cvvValidated, not(nullValue()))
            assertThat(it.expired, not(nullValue()))
            assertThat(it.expiryMonth, not(blankOrNullString()))
            assertThat(it.expiryYear, not(blankOrNullString()))
            assertThat(it.lastUpdated, not(nullValue()))
            assertThat(it.lastUsed, not(nullValue()))
            assertThat(it.paymentInstrumentId, not(blankOrNullString()))
            assertThat(it.paymentToken, not(blankOrNullString()))
            assertThat(it.primary, not(nullValue()))
            assertThat(it.requiresCVV, not(nullValue()))
            assertThat(it.scheme, not(blankOrNullString()))
            assertThat(it.status, not(nullValue()))
            assertThat(it.updateURL, not(blankOrNullString()))

            val stepUp = it.stepUp
            assertThat(stepUp, not(nullValue()))
            assertThat(stepUp.mandatory, not(nullValue()))
            assertThat(stepUp.type, not(blankOrNullString()))
            assertThat(stepUp.url, not(blankOrNullString()))
        }

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of credit cards")
    }
}

class GiftCardsMatcher : TypeSafeMatcher<GetCustomerPaymentInstrumentsResultsData>() {
    override fun matchesSafely(item: GetCustomerPaymentInstrumentsResultsData): Boolean {
        val giftCards = item.giftCards

        assertThat(giftCards, not(nullValue()))
        assertThat(giftCards.size, greaterThanOrEqualTo(1))

        giftCards.forEach {
            assertThat(it.allowed, not(nullValue()))
            assertThat(it.cardSuffix, not(blankOrNullString()))
            assertThat(it.lastUpdated, not(nullValue()))
            assertThat(it.lastUsed, not(nullValue()))
            assertThat(it.paymentInstrumentId, not(blankOrNullString()))
            assertThat(it.paymentToken, not(blankOrNullString()))
            assertThat(it.primary, not(nullValue()))
            assertThat(it.status, not(nullValue()))
            assertThat(it.programName, not(blankOrNullString()))

            val stepUp = it.stepUp
            assertThat(stepUp, not(nullValue()))
            assertThat(stepUp?.mandatory, not(nullValue()))
            assertThat(stepUp?.type, not(blankOrNullString()))
        }

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of credit cards")
    }
}