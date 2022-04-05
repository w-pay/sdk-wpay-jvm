package au.com.wpay.sdk.api

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.model.*
import au.com.wpay.sdk.*
import au.com.wpay.sdk.helpers.optionalParam
import au.com.wpay.sdk.helpers.params
import au.com.wpay.sdk.model.AcceptTermsAndConditionsRequest
import au.com.wpay.sdk.model.TermsAndConditionsAcceptances

class CustomerTermsAndConditionsApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Get the terms and conditions agreed to by the customer
     *
     * @param type The type of Ts and Cs that the shopper/customer has agreed to. Defaults to all if absent
     * @param version The version of Ts and Cs that the shopper/customer has agreed to.  Defaults to all if absent
     */
    suspend fun get(
        type: String? = null,
        version: String? = null
    ): ApiResult<TermsAndConditionsAcceptances> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::fromData)({ parser, el -> tryDecoding<TermsAndConditionsAcceptances>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instore/customer/termsandconditions/acceptance"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = params(
                optionalParam("type", type),
                optionalParam("version", version)
            ),
            body = null
        )))
    }

    /**
     * Customer accepts terms and conditions"
     *
     * @param type The type of Ts and Cs that the shopper/customer has agreed to. Defaults to all if absent
     */
    suspend fun accept(request: AcceptTermsAndConditionsRequest): ApiResult<Unit> {
        @Suppress("MoveLambdaOutsideParentheses")
        val unmarshaller = unmarshall(::jsonPassthrough)({ parser, el -> tryDecoding<Unit>(parser, el) })
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instore/customer/termsandconditions/acceptance"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = request,
                meta = Meta()
            )
        )))
    }
}
