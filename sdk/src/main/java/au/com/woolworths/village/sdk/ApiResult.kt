package au.com.woolworths.village.sdk

/**
 * A result from an API operation
 */
// The beginning of a poor mans Monad.
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
     * @property e The error that occurred.
     * @constructor
     */
    data class Error(val e: ApiException) : ApiResult<Nothing>()
}

/**
 * Base exception type. Used when no other error type is appropriate.
 */
open class ApiException(
    override val message: String,
    override val cause: Throwable?
) : Exception(message, cause) {
    constructor(message: String) : this(message, null)
}

/**
 * Throw when there is an error parsing JSON data
 */
class JsonParsingException(
    override val message: String,
    override val cause: Throwable?,

    /** Additional details about why the parsing failed. Is implementation specific. */
    val details: Map<String, Any>?
) : ApiException(message, cause) {
    constructor(message: String) : this(message, null, null)
}

/**
 * Thrown when the server returns an HTTP error
 */
class HttpErrorException(
    val statusCode: Int,
    val responseHeaders: Map<String, List<String>>,
    val responseBody: String
) : ApiException(
    when (statusCode) {
        400 -> "Invalid Input"
        401 -> "Unauthorized"
        422 -> "Processing Error"
        else -> "Server Error"
    },
    null
)