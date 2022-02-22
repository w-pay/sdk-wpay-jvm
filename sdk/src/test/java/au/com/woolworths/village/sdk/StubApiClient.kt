package au.com.woolworths.village.sdk

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.woolworths.village.sdk.SdkApiClient
import kotlin.reflect.KClass

class StubApiClient {
    lateinit var request: HttpRequest<*>
    lateinit var result: ApiResult<*>

    fun client(): SdkApiClient =
        object: SdkApiClient {
            @Suppress("UNCHECKED_CAST")
            override fun <T : Any> invoke(p1: KClass<T>): suspend (HttpRequest<*>) -> ApiResult<T> =
                { req ->
                    this@StubApiClient.request = req

                    this@StubApiClient.result as ApiResult<T>
                }
        }
}