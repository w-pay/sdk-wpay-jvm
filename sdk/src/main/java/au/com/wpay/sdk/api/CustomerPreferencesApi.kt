package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.CustomerPreferences

class CustomerPreferencesApi(
    private val factory: SdkApiClientFactory,
    private val marshall: SdkJsonMarshaller,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a customer's preferences
     */
    suspend fun get(): ApiResult<CustomerPreferences> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall(unitEncoder),
            unmarshall(::fromData)({ parser, el -> tryDecoding<CustomerPreferences>(parser, el) })
        )

        return apiResult(client(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/preferences")
        )))
    }

    /**
     * Update the preferences for a customer
     *
     * @param preferences The preferences of the customer.
     */
    suspend fun set(preferences: CustomerPreferences): ApiResult<Unit> {
        @Suppress("MoveLambdaOutsideParentheses")
        val client = factory(
            marshall({ parser, data: ApiRequestBody<CustomerPreferences, Meta> -> tryEncoding(parser, data) }),
            unmarshall(::jsonPassthrough)(unitDecoder)
        )

        return apiResult(client(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/customer/preferences"),
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
