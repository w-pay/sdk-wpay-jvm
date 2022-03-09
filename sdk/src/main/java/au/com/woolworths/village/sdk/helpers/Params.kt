package au.com.woolworths.village.sdk.helpers

fun <A, B> MutableMap<A, B>.putAllFrom(from: Map<out A, B>): MutableMap<A, B> {
    putAll(from)

    return this
}

fun optionalParam(name: String, value: Any?): Map<String, String> =
    when (value) {
        null -> emptyMap()
        else -> mapOf(name to value.toString())
    }

fun params(vararg params: Map<String, String>): Map<String, String> =
    params(params.asList())

fun params(params: List<Map<String, String>>): Map<String, String> =
    // TODO: Replace with Monoid
    params.foldRight(mutableMapOf()) { map, acc -> acc.putAllFrom(map) }