package au.com.woolworths.village.sdk.matchers

import arrow.core.*
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult

private fun firstFailedResult(results: List<MatcherResult>): Option<MatcherResult> =
    results
        .map { Either.conditionally(it.passed(), { it }, { it }) }
        .reduce { acc, it -> acc.flatMap(constant(it)) }
        .fold(::Some, constant(None))

fun <T> equal(expected: T?, actual: T?): Boolean =
    expected == actual

fun <T> notEqual(expected: T?, actual: T?): Boolean =
    expected != actual

fun <T> MatcherResult.Companion.test(
    predicate: (T?, T?) -> Boolean,
    expected: T?,
    actual: T?
): MatcherResult {
    val failureMessage = { "$actual should be $expected" }
    val negatedFailureMessage = { "$actual should not be $expected" }

    val result = predicate(expected, actual)

    return  MatcherResult(result, failureMessage, negatedFailureMessage)
}

fun <A, B> MatcherResult.Companion.test(
    test: (A) -> Matcher<B>,
    expected: A?,
    actual: B?
): MatcherResult {
    val expectedPresent = { "expected is present but actual is null" }
    val actualPresent = { "actual is present but expected is null" }

    return when {
        expected == null && actual == null -> MatcherResult(true,
            { "actual does not match expected"},
            { "actual matches expected" }
        )
        expected != null && actual == null -> MatcherResult(false, expectedPresent, actualPresent)
        actual != null && expected == null -> MatcherResult(false, actualPresent, expectedPresent)
        else -> test(expected!!).test(actual!!)
    }
}

fun <A, B> MatcherResult.Companion.forEach(
    test: (A) -> Matcher<B>
): (List<A>) -> Matcher<List<B>> =
    { expected ->
        object: Matcher<List<B>> {
            override fun test(value: List<B>): MatcherResult {
                if (expected.size != value.size) {
                    return MatcherResult(false,
                        { "expected and actual sizes don't match" },
                        { "expected and actual sizes match" }
                    )
                }

                val testItem = { idx: Int, item: B -> test(expected[idx]).test(item) }
                val listsMatchResult = { MatcherResult(true,
                    { "expected list and actual list don't match" },
                    { "expected list and actual list match" }
                ) }

                return firstFailedResult(value.mapIndexed(testItem)).fold(listsMatchResult, ::identity)
            }
        }
    }

fun Matcher.Companion.tests(results: List<MatcherResult>): MatcherResult =
    firstFailedResult(results).fold(
        { MatcherResult(true, { "value does not match expected" }, { "value matches expected" } ) },
        ::identity
    )

fun Matcher.Companion.tests(vararg results: MatcherResult): MatcherResult =
    Matcher.tests(results.asList())
