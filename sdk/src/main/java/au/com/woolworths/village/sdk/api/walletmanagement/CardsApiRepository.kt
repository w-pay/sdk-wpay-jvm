package au.com.woolworths.village.sdk.api.walletmanagement

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.walletmanagement.InitiateCardCaptureRequest
import au.com.woolworths.village.sdk.model.walletmanagement.InitiateCardCaptureResponse

/**
 * @category API
 */
interface CardsApiRepository {
    /**
     * Get a credit card detials input iframe (URL) for the consumer. This API is rate limited to 10 requests per minute per shopper id.
     *
     * @param initiateCardCaptureRequest Detail of the card capture to recieve the iframe (URL) for.
     */
    fun initCapture(
        initiateCardCaptureRequest: InitiateCardCaptureRequest
    ): ApiResult<InitiateCardCaptureResponse>

    /**
     * Get a credit card detials input iframe (URL) for a guest user. This API is rate limited to 10 requests per minute per guest shopper id.
     *
     * @param initiateCardCaptureRequest Detail of the card capture to recieve the iframe (URL) for.
     */
    fun guestInitCapture(
        initiateCardCaptureRequest: InitiateCardCaptureRequest
    ): ApiResult<InitiateCardCaptureResponse>
}
