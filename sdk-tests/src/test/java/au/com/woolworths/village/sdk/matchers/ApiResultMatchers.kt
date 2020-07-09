package au.com.woolworths.village.sdk.matchers

import au.com.woolworths.village.sdk.ApiResult
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun <T : Any> isSuccessfulWith(matcher: Matcher<T>): Matcher<ApiResult<T>> {
    return SuccessfulApiResultMatcher(matcher)
}

fun isSuccessful(): Matcher<ApiResult<Unit>> {
    return SuccessfulApiResultMatcher(null)
}

class SuccessfulApiResultMatcher<T : Any>(private val matcher: Matcher<T>?) : TypeSafeMatcher<ApiResult<T>>() {
    override fun matchesSafely(result: ApiResult<T>): Boolean {
        return when (result) {
            is ApiResult.Success -> matcher?.matches(result.value) ?: true

            is ApiResult.Error -> false
        }
    }

    override fun describeTo(description: Description) {
        description.appendText("An ApiResult with success")
    }
}