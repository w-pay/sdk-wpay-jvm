package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.model.HealthCheck
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun healthyService(): Matcher<HealthCheck> = HealthCheckMatcher()

class HealthCheckMatcher: TypeSafeMatcher<HealthCheck>() {
    override fun matchesSafely(item: HealthCheck): Boolean {
        return item.result() == HealthCheck.Status.SUCCESS
    }

    override fun describeTo(description: Description) {
        description.appendText("A healthy service")
    }
}