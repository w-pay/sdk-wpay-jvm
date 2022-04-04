package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.*
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.wpay.sdk.*
import kotlinx.serialization.Serializable

/**
 * Map of merchant preferences.
 */
typealias MerchantPreferences = Map<String, Map<String, String>>

/*
 * Needed to get at the inner map.
 */
@Serializable
private data class MerchantPreferencesResponse(
    val data: MerchantPreferences
)

class MerchantPreferencesApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a merchant's preferences.
     */
    suspend fun get(): ApiResult<MerchantPreferences> {
        val unmarshaller: Unmarshaller<MerchantPreferences> = { data ->
            unmarshall(::jsonPassthrough)(MerchantPreferencesResponse::class)(data).map { it.data }
        }

        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<MerchantPreferences>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/merchant/preferences")
        )))
    }

    /**
     * Update a merchant's preferences
     *
     * @param preferences The preferences to use
     */
    suspend fun set(preferences: MerchantPreferences): ApiResult<Unit> {
        val unmarshaller = unmarshall(::jsonPassthrough)(Unit::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/merchant/preferences"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = preferences,
                meta = Meta()
            )
        )))
    }
}
