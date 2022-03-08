package au.com.woolworths.village.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.woolworths.village.sdk.*
import au.com.woolworths.village.sdk.model.CustomerPreferences

class CustomerPreferencesApi(
    val client: SdkApiClient,
    val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Retrieve a customer's preferences
     */
    suspend fun get(): ApiResult<CustomerPreferences> {
        val unmarshaller = unmarshall(::fromData)(CustomerPreferences::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
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
        val unmarshaller = unmarshall(::jsonPassthrough)(Unit::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
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