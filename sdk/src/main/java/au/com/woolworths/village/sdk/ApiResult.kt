package au.com.woolworths.village.sdk

import arrow.core.Either
import arrow.core.left
import arrow.core.right

/**
 * A result from an API operation
 *
 * An ApiResult can be examined using an Imperative style (Kotlin's when), or can be converted
 * to an Arrow Either so that Functional programming (map) can be used.
 */
sealed class ApiResult<out T : Any> {
    /**
     * A successful response from the API was received
     *
     * @param T The type of response data
     * @property value The data parsed from the API response
     * @constructor
     */
    data class Success<out T : Any>(val value: T) : ApiResult<T>()

    /**
     * An error that occurred while making an API call
     *
     * @property error The error that occurred.
     * @constructor
     */
    data class Error(val error: ApiError) : ApiResult<Nothing>()

    fun toEither(): Either<ApiError, T> =
        when(this) {
            is Success -> this.value.right()
            is Error -> this.error.left()
        }
}

/**
 * Base error type. Used when no other error type is appropriate.
 */
open class ApiError(
    val message: String,
    val cause: Any?
) {
    constructor(message: String) : this(message, null)
}

/**
 * Returned when there is an error parsing JSON data
 */
class JsonParsingError(
    message: String,
    cause: Any?,

    /** Additional details about why the parsing failed. Is implementation specific. */
    val details: Map<String, Any>?
) : ApiError(message, cause) {
    constructor(message: String) : this(message, null, null)
}

/**
 * Returned when the server returns an HTTP failure
 */
class HttpFailureError(
    val statusCode: Int,
    val responseHeaders: Map<String, List<String>>,
    val responseBody: String
) : ApiError(
    when (statusCode) {
        400 -> "Invalid Input"
        401 -> "Unauthorized"
        422 -> "Processing Error"
        else -> "Server Error"
    },
    null
)