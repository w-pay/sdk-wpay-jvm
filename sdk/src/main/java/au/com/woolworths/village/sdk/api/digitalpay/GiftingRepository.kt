package au.com.woolworths.village.sdk.api.digitalpay

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.ChallengeResponse
import au.com.woolworths.village.sdk.model.FraudPayload
import au.com.woolworths.village.sdk.model.digitalpay.*
import org.threeten.bp.OffsetDateTime

interface GiftingRepository {
    /**
     * Obtains a detail of an available gift card product that can be purchased
     *
     * @param productId detail of payment to be made
     */
    fun getProductById(productId: String): ApiResult<DigitalPayGiftingProductDetail>

    /**
     * Obtains a list of available gift card products that can be purchased.
     *
     * @param page The page of results to return with 1 indicating the first page
     * @param pageSize The number of records to return for this page
     * @param lastUpdateDateTime If present, only products changed since this time will be returned
     */
    fun listProducts(
        page: Int,
        pageSize: Int,
        lastUpdateDateTime: OffsetDateTime
    ): ApiResult<List<DigitalPayGiftingProduct>>

    /**
     * Validates a gift card order and verifies discount prior to an order being placed.
     *
     * @param quoteRequest detail of gift card quote being obtained
     */
    fun getQuote(
        quoteRequest: DigitalPayGiftingQuoteRequest
    ): ApiResult<DigitalPayGiftingQuoteResponse>

    /**
     * Order a gift card product.
     *
     * @param orderRequest detail of gift card order being made
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     * @param fraudPayload Used to complete the fraud check.
     */
    fun order(
        orderRequest: DigitalPayGiftingOrderRequest,
        challengeResponses: List<ChallengeResponse>?,
        fraudPayload: FraudPayload?
    ): ApiResult<DigitalPayGiftingOrderResponse>
}