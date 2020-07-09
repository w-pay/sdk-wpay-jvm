package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.HeathCheck
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun healthyService(): Matcher<HeathCheck> = HealthCheckMatcher()

class HealthCheckMatcher: TypeSafeMatcher<HeathCheck>() {
    override fun matchesSafely(item: HeathCheck): Boolean {
        return item.result() == HeathCheck.Status.SUCCESS
    }

    override fun describeTo(description: Description) {
        description.appendText("A healthy service")
    }
}