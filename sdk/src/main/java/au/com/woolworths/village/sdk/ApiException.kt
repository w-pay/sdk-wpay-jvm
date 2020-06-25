package au.com.woolworths.village.sdk

class ApiException(
    val code: Int,
    val responseHeaders: Map<String, List<String>>,
    val responseBody: String
): Exception()