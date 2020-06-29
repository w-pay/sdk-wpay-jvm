package au.com.woolworths.village.sdk.openapi.api.matchers

import au.com.woolworths.village.sdk.openapi.dto.MerchantSchemaSummary

import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString

fun withSchemaSummary(): MerchantSchemaSummaryMatcher {
    return MerchantSchemaSummaryMatcher()
}

class MerchantSchemaSummaryMatcher: TypeSafeMatcher<MerchantSchemaSummary>() {
    override fun matchesSafely(item: MerchantSchemaSummary): Boolean {
        assertThat(item.schemaId, not(blankOrNullString()))
        assertThat(item.type, not(blankOrNullString()))
        assertThat(item.description, not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant Schema Summary")
    }
}