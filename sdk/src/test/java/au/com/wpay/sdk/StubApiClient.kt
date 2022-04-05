package au.com.wpay.sdk

import arrow.core.Either
import arrow.core.right
import au.com.redcrew.apisdkcreator.httpclient.*

class StubApiClient {
    lateinit var request: HttpRequest<*>
    lateinit var response: HttpResponse<UnstructuredData>

    fun client(): SdkApiClient =
        object: SdkApiClient {
            override suspend fun invoke(
                request: HttpRequest<*>
            ): Either<SdkError, HttpResult<*, UnstructuredData>> {
                this@StubApiClient.request = request

                return HttpResult(
                    request = request,
                    response = response
                ).right()
            }
        }
}
