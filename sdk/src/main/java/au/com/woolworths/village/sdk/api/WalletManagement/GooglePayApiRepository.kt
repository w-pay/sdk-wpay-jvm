package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.TokenizeGooglePayRequest
import au.com.woolworths.village.sdk.model.TokenizeGooglePayResponse

/**
 * @category API
 */
interface GooglePayApiRepository {
    /**
     * Create a payment token for a provided Google Pay wallet item.
     *
     * @param tokenizeGooglePayRequest Detail of the Google Pay wallet item to be tokenized.
     */
    fun tokenize(
        tokenizeGooglePayRequest: TokenizeGooglePayRequest
    ): ApiResult<TokenizeGooglePayResponse>

    /**
     * Create a payment token for a provided Google Pay wallet item of a guest user.
     *
     * @param tokenizeGooglePayRequest Detail of the Google Pay wallet item to be tokenized.
     */
    fun guestTokenize(
        tokenizeGooglePayRequest: TokenizeGooglePayRequest
    ): ApiResult<TokenizeGooglePayResponse>

    /**
     * Update a Google Pay payment instrument.
     *
     * @param paymentToken The payment token of the google pay payment instrument to update.
     * @param tokenizeGooglePayRequest Detail of the Google Pay wallet item to be tokenized.
     */
    fun update(
        paymentToken: String,
        tokenizeGooglePayRequest: TokenizeGooglePayRequest
    ): ApiResult<TokenizeGooglePayRequest>
}
