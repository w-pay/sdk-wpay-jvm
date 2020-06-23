package au.com.woolworths.village.sdk

/*
 * The beginning of a poor mans Monad.
 */
sealed class ApiResult<out T: Any> {
    data class Success<out T: Any>(val value: T): ApiResult<T>()
    data class Error(val e: Exception): ApiResult<Nothing>()
}