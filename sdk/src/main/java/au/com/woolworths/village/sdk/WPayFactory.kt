package au.com.woolworths.village.sdk

import arrow.core.Either
import arrow.core.identity
import arrow.core.right
import au.com.redcrew.apisdkcreator.httpclient.*

typealias SdkApiClient = suspend (request: HttpRequest<*>) -> Either<SdkError, HttpResult<*, UnstructuredData>>

private fun unwrapUnstructuredData(data: UnstructuredData): String =
    when (data) {
        is UnstructuredData.String -> data.data
    }

private fun <T: Any> failureResponseHandler(result: HttpResult<*, UnstructuredData>): ApiResult<T> =
    ApiResult.Error(HttpFailureError(
        result.response.statusCode,
        result.response.headers,
        result.response.body?.let(::unwrapUnstructuredData)
    ))

@Suppress("UNCHECKED_CAST")
private suspend fun <T : Any> successResponseHandler(
    handler: HttpResultHandler<*, UnstructuredData, T>,
    result: HttpResult<*, UnstructuredData>
): ApiResult<T> =
    handler(result).fold(::toApiErrorResult) {
        when (val body = extractHttpBody(it)) {
            null -> ApiResult.Success(Unit as T)
            else -> ApiResult.Success(body)
        }
    }

fun <T: Any> resultHandler(
    onResult: HttpResultHandler<*, UnstructuredData, T>,
): suspend (HttpResult<*, UnstructuredData>) -> Either<SdkError, ApiResult<T>> =
    { result ->
        when (isSuccessfulResult(result)) {
            true -> successResponseHandler(onResult, result).right()
            false -> failureResponseHandler<T>(result).right()
        }
    }

fun <T : Any> apiResult(
    result: Either<SdkError, ApiResult<T>>
): ApiResult<T> =
    result.fold(::toApiErrorResult, ::identity)