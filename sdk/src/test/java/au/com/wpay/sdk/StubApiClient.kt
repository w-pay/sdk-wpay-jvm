package au.com.wpay.sdk

import arrow.core.Either
import arrow.core.identity
import arrow.core.right
import au.com.redcrew.apisdkcreator.httpclient.*

class StubApiClient {
    lateinit var request: HttpRequest<*>
    lateinit var response: HttpResponse<UnstructuredData>

    fun factory(): SdkApiClientFactory =
        object: SdkApiClientFactory {
            @Suppress("MoveLambdaOutsideParentheses", "UNCHECKED_CAST")
            override fun <T : Any> invoke(
                p1: Marshaller,
                p2: Unmarshaller<T>
            ): suspend (request: HttpRequest<*>) -> Either<SdkError, ApiResult<T>> =
                { request ->
                    this@StubApiClient.request = request

                    /*
                     * Test that the request body (if any) can be marshalled.
                     */
                    val body = request.body?.let { p1(it) } ?: Either.Right(null)
                    body.fold(
                        { error -> throw error.toException() },
                        ::identity
                    )

                    /*
                     * Unmarshall the response body
                     */
                    val result = response.body?.let { p2(it) } ?: Either.Right(Unit)
                    result.fold(::toApiErrorResult, { ApiResult.Success(it as T) }).right()
                }
        }
}
