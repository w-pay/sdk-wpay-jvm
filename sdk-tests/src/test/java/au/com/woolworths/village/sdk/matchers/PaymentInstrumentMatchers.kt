package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString
import org.junit.Assert.assertThat

fun allPaymentInstruments(): Matcher<AllPaymentInstruments> = AllPaymentInstrumentsMatcher()

fun hasPaymentInstruments(): Matcher<PaymentInstruments> = PaymentInstrumentMatcher()

fun <T : Any> hasCards(matcher: Matcher<T>): Matcher<List<T>> = CardsMatcher(matcher)

fun creditCard(): Matcher<CreditCard> = CreditCardMatcher()

fun giftCard(): Matcher<GiftCard> = GiftCardMatcher()

fun paymentInstrumentAdded(): Matcher<PaymentInstrumentAdditionResult> = PaymentInstrumentAdditionResultMatcher()

class AllPaymentInstrumentsMatcher: TypeSafeMatcher<AllPaymentInstruments>() {
    override fun matchesSafely(item: AllPaymentInstruments): Boolean {
        assertThat(item.creditCards(), hasCards(creditCard()))
        assertThat(item.giftCards(), hasCards(giftCard()))
        assertThat(item.everydayPay()!!, hasPaymentInstruments())

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of all payments instruments")
    }

}

class PaymentInstrumentMatcher: TypeSafeMatcher<PaymentInstruments>() {
    override fun matchesSafely(item: PaymentInstruments): Boolean {
        assertThat(item.creditCards(), hasCards(creditCard()))
        assertThat(item.giftCards(), hasCards(giftCard()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of payment instruments")
    }
}

class CardsMatcher<T : Any>(private val matcher: Matcher<T>): TypeSafeMatcher<List<T>>() {
    override fun matchesSafely(list: List<T>?): Boolean {
        assertThat(list, not(nullValue()))
        assertThat(list!!.size, greaterThanOrEqualTo(1))

        return list.fold(true, { result, item -> result && matcher.matches(item) })
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of cards with at least one card")
    }
}

class CreditCardMatcher: TypeSafeMatcher<CreditCard>() {
    override fun matchesSafely(card: CreditCard): Boolean {
        assertThat(card.allowed(), not(nullValue()))
        assertThat(card.cardName(), not(blankOrNullString()))
        assertThat(card.cardSuffix(), not(blankOrNullString()))
        assertThat(card.cvvValidated(), not(nullValue()))
        assertThat(card.expired(), not(nullValue()))
        assertThat(card.expiryMonth(), not(blankOrNullString()))
        assertThat(card.expiryYear(), not(blankOrNullString()))
        assertThat(card.lastUpdated(), not(nullValue()))
        assertThat(card.lastUsed(), not(nullValue()))
        assertThat(card.paymentInstrumentId(), not(blankOrNullString()))
        assertThat(card.paymentToken(), not(blankOrNullString()))
        assertThat(card.primary(), not(nullValue()))
        assertThat(card.requiresCVV(), not(nullValue()))
        assertThat(card.scheme(), not(blankOrNullString()))
        assertThat(card.status(), not(nullValue()))
        assertThat(card.updateURL(), not(nullValue()))

        val stepUp = card.stepUp()
        assertThat(stepUp, not(nullValue()))
        assertThat(stepUp.mandatory(), not(nullValue()))
        assertThat(stepUp.type(), not(blankOrNullString()))
        assertThat(stepUp.url(), not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A Credit Card")
    }
}

class GiftCardMatcher: TypeSafeMatcher<GiftCard>() {
    override fun matchesSafely(card: GiftCard): Boolean {
        assertThat(card.allowed(), not(nullValue()))
        assertThat(card.cardSuffix(), not(blankOrNullString()))
        assertThat(card.lastUpdated(), not(nullValue()))
        assertThat(card.lastUsed(), not(nullValue()))
        assertThat(card.paymentInstrumentId(), not(blankOrNullString()))
        assertThat(card.paymentToken(), not(blankOrNullString()))
        assertThat(card.primary(), not(nullValue()))
        assertThat(card.status(), not(nullValue()))
        assertThat(card.programName(), not(blankOrNullString()))

        val stepUp = card.stepUp()
        assertThat(stepUp, not(nullValue()))
        assertThat(stepUp?.mandatory(), not(nullValue()))
        assertThat(stepUp?.type(), not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A Gift Card")
    }
}

class PaymentInstrumentAdditionResultMatcher: TypeSafeMatcher<PaymentInstrumentAdditionResult>() {
    override fun matchesSafely(item: PaymentInstrumentAdditionResult): Boolean {
        assertThat(item.cardCaptureURL(), not(blankOrNullString()))
        assertThat(item.transactionRef(), not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A successful payment instrument addition result")
    }
}