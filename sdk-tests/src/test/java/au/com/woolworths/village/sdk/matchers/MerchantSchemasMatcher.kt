package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.MerchantSchema
import au.com.woolworths.village.sdk.model.MerchantSchemaSummaries
import au.com.woolworths.village.sdk.model.MerchantSchemaSummary
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.text.IsBlankString.blankOrNullString
import org.junit.Assert.assertThat

fun merchantSchemaSummaries(): Matcher<MerchantSchemaSummaries> = MerchantSchemaSummariesMatcher()

class MerchantSchemaSummariesMatcher: TypeSafeMatcher<MerchantSchemaSummaries>() {
    override fun matchesSafely(item: MerchantSchemaSummaries): Boolean {
        val schemaMatcher = MerchantSummarySchemaMatcher()
        val schemas = item.schemas()

        assertThat(schemas.size, greaterThan(0))

        return schemas.fold(true, { result, it ->
            return result && schemaMatcher.matches(it)
        })
    }

    override fun describeTo(description: Description) {
        description.appendText("A list of schemas")
    }
}

fun merchantSchemaSummary(): Matcher<MerchantSchemaSummary> = MerchantSummarySchemaMatcher()

class MerchantSummarySchemaMatcher: TypeSafeMatcher<MerchantSchemaSummary>() {
    override fun matchesSafely(item: MerchantSchemaSummary): Boolean {
        assertThat(item.schemaId(), not(blankOrNullString()))
        assertThat(item.type(), not(blankOrNullString()))
        assertThat(item.description(), not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Merchant Schema Summary")
    }
}

fun merchantSchema(): Matcher<MerchantSchema> = MerchantSchemaMatcher()

class MerchantSchemaMatcher: TypeSafeMatcher<MerchantSchema>() {
    override fun matchesSafely(item: MerchantSchema): Boolean {
        assertThat(item.schema(), not(nullValue()))
        assertThat(item.type(), not(blankOrNullString()))
        assertThat(item.description(), not(blankOrNullString()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("A Merchant Schema Summary")
    }
}