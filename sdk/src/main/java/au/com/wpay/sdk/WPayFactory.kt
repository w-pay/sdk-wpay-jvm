package au.com.wpay.sdk

import arrow.core.Either
import arrow.core.identity
import arrow.core.right
import au.com.redcrew.apisdkcreator.httpclient.*
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.wpay.sdk.auth.ApiAuthenticator
import au.com.wpay.sdk.auth.HasAccessToken
import au.com.wpay.sdk.headers.defaultCustomerHeaders
import au.com.wpay.sdk.headers.defaultHeaders
import au.com.wpay.sdk.headers.defaultMerchantHeaders

typealias HttpClientFactory = () -> HttpClient

typealias SdkApiClient = suspend (request: HttpRequest<*>) -> Either<SdkError, HttpResult<*, UnstructuredData>>

sealed class ApiTokenType {
    data class StringToken(val token: String) : ApiTokenType()
    data class ApiAuthenticatorToken(val authenticator: ApiAuthenticator<HasAccessToken>) :
        ApiTokenType()

    data class NoToken(val token: Unit = Unit) : ApiTokenType()
}

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

fun createApiClient(
    httpClient: HttpClientFactory,
    options: WPayOptions,
): SdkApiClient {
    val headers = when(options) {
        is WPayCustomerOptions -> defaultCustomerHeaders(options)
        is WPayMerchantOptions -> defaultMerchantHeaders(options)
        else -> defaultHeaders(options)
    }

    return resolveUrl(options.baseUrl) pipe
            addHeaders(headers) pipe
            jsonMarshaller(kotlinxSerialisationMarshaller()) pipe
            httpClient()
}
