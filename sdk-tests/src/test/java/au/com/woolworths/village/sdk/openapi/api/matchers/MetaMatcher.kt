package au.com.woolworths.village.sdk.openapi.api.matchers

import au.com.woolworths.village.sdk.openapi.dto.MetaPaginated

import org.hamcrest.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.TypeSafeMatcher

fun isPaginatedMeta(): MetaMatcher {
    return MetaMatcher()
}

class MetaMatcher: TypeSafeMatcher<MetaPaginated>() {
    override fun matchesSafely(item: MetaPaginated): Boolean {
        assertThat(item.totalRecords, not(nullValue()))
        assertThat(item.recordCount, not(nullValue()))

        return true
    }

    override fun describeTo(description: Description) {
        description.appendText("Paginated meta")
    }
}