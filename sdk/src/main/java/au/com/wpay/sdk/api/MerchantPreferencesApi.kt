package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.Unmarshaller
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
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a merchant's preferences.
     */
    suspend fun get(): ApiResult<MerchantPreferences> {
        val unmarshaller: Unmarshaller<MerchantPreferences> = { data ->
            @Suppress("MoveLambdaOutsideParentheses")
            unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<MerchantPreferencesResponse>(parser, el) })(data).map { it.data }
        }

        val client = factory(marshall(unitEncoder), unmarshaller)

        return apiResult(client(HttpRequest<MerchantPreferences>(
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
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: ApiRequestBody<MerchantPreferences, Meta> -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)(unitDecoder)
        )

        return apiResult(client(HttpRequest(
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
