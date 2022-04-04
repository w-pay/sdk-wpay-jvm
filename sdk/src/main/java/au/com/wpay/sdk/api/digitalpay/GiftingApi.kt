package au.com.wpay.sdk.api.digitalpay

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.helpers.optionalParam
import au.com.wpay.sdk.helpers.params
import au.com.wpay.sdk.model.ChallengeResponse
import au.com.wpay.sdk.model.FraudPayload
import au.com.wpay.sdk.model.digitalpay.*
import org.threeten.bp.OffsetDateTime

class GiftingApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Obtains a detail of an available gift card product that can be purchased
     *
     * @param productId detail of payment to be made
     */
    suspend fun getProductById(productId: String): ApiResult<DigitalPayGiftingProductDetail> {
        val unmarshaller = unmarshall(::fromData)(DigitalPayGiftingProductDetail::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/gifting/products/:productId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "productId" to productId
            ),
            queryParams = null,
            body = null
        )))
    }

    /**
     * Obtains a list of available gift card products that can be purchased.
     *
     * @param page The page of results to return with 1 indicating the first page
     * @param pageSize The number of records to return for this page
     * @param lastUpdateDateTime If present, only products changed since this time will be returned
     */
    suspend fun listProducts(
        page: Int? = null,
        pageSize: Int? = null,
        lastUpdateDateTime: OffsetDateTime? = null
    ): ApiResult<DigitalPayGiftingProducts> {
        val unmarshaller = unmarshall(::fromData)(DigitalPayGiftingProducts::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/gifting/products"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = params(
                optionalParam("page", page),
                optionalParam("page-size", pageSize),
                optionalParam("last-updated-date-time", lastUpdateDateTime?.toIsoDateTime()),
            ),
            body = null
        )))
    }

    /**
     * Validates a gift card order and verifies discount prior to an order being placed.
     *
     * @param quoteRequest detail of gift card quote being obtained
     */
    suspend fun getQuote(
        quoteRequest: DigitalPayGiftingQuoteRequest
    ): ApiResult<DigitalPayGiftingQuoteResponse> {
        val unmarshaller = unmarshall(::fromData)(DigitalPayGiftingQuoteResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/gifting/products/quote"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = quoteRequest,
                meta = Meta()
            )
        )))
    }

    /**
     * Order a gift card product.
     *
     * @param orderRequest detail of gift card order being made
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     * @param fraudPayload Used to complete the fraud check.
     */
    suspend fun order(
        orderRequest: DigitalPayGiftingOrderRequest,
        challengeResponses: List<ChallengeResponse>? = null,
        fraudPayload: FraudPayload? = null
    ): ApiResult<DigitalPayGiftingOrderResponse> {
        val unmarshaller = unmarshall(::fromData)(DigitalPayGiftingOrderResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/gifting/products/order"),
            headers = emptyMap(),
            pathParams = null,
            queryParams = null,
            body = ApiRequestBody(
                data = orderRequest,
                meta = when (challengeResponses) {
                    null -> Meta(fraudPayload)
                    else -> Meta(fraudPayload, challengeResponses)
                }
            )
        )))
    }
}
