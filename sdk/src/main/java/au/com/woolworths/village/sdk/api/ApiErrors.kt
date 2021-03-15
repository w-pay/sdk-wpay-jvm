package au.com.woolworths.village.sdk.api

import java.math.BigDecimal

/**
 * Base exception type. Used when no other error type is appropriate.
 */
open class ApiException(message: String) : Exception(message)

/**
 * Throw when there is an error parsing JSON data
 */
class JsonParsingException(message: String, var details: Map<String, Any>?) :
    ApiException(message) {
}

/**
 * Thrown when the server returns an HTTP error
 */
class HttpErrorException(
    var statusCode: BigDecimal,
    var responseHeaders: Map<String, List<String>>,
    var responseBody: String
) : ApiException(responseBody) {
}
