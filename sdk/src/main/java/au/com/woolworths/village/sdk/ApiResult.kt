package au.com.woolworths.village.sdk

/*
 * The beginning of a poor mans Monad.
 */
sealed class ApiResult<out T: Any> {
    data class Success<out T: Any>(val value: T): ApiResult<T>()
    data class Error(val e: ApiException): ApiResult<Nothing>()
}

/*
 * Base exception type. Used when no other error type is appropriate.
 */
open class ApiException(
    override val message: String,
    override val cause: Throwable?
): Exception(message, cause) {
    constructor(message: String): this(message, null)
}

class JsonParsingException(
    override val message: String,
    override val cause: Throwable?,

    /** Additional details about why the parsing failed. Is implementation specific. */
    val details: Map<String, Any>?
): ApiException(message, cause) {
    constructor(message: String): this(message, null, null)
}

class HttpErrorException(
    val statusCode: Int,
    val responseHeaders: Map<String, List<String>>,
    val responseBody: String
): ApiException(
    when (statusCode) {
        400 -> "Invalid Input"
        401 -> "Unauthorized"
        422 -> "Processing Error"
        else -> "Server Error"
    },
    null
)