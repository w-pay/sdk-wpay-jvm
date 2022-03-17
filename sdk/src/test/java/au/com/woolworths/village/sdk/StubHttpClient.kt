package au.com.woolworths.village.sdk

import arrow.core.right
import au.com.redcrew.apisdkcreator.httpclient.*

class StubHttpClient {
    lateinit var request: HttpRequest<UnstructuredData>
    var response: HttpResponse<UnstructuredData> = HttpResponse(
        statusCode = 200,
        statusMessage = "OK",
        headers = emptyMap()
    )

    fun factory(): () -> HttpClient = ::client

    fun client(): HttpClient =
        { request ->
            this@StubHttpClient.request = request

            HttpResult(
                request = request,
                response = response
            ).right()
        }
}