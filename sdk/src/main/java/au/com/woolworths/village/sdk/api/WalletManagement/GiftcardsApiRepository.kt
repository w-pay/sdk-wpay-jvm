package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.GiftcardsBalanceRequest
import au.com.woolworths.village.sdk.model.GiftcardsBalanceResponse
import au.com.woolworths.village.sdk.model.TokenizeGiftcardRequest
import au.com.woolworths.village.sdk.model.TokenizeGiftcardResponse

/**
 * @category API
 */
interface GiftcardsApiRepository {
    /**
     * Create a paymment intrument id for a provided gift card.
     *
     * @param tokenizeGiftcardRequest Detail of the Gift Card to be tokenized.
     */
    fun tokenize(tokenizeGiftcardRequest: TokenizeGiftcardRequest): ApiResult<TokenizeGiftcardResponse>

    /**
     * Create a paymment intrument id for a provided gift card of a guest user.
     *
     * @param tokenizeGiftcardRequest Detail of the Gift Card to be tokenized.
     */
    fun guestTokenize(
        tokenizeGiftcardRequest: TokenizeGiftcardRequest
    ): ApiResult<TokenizeGiftcardResponse>

    /**
     * Get the balance and expiryinfo for the provided gift cards. This API is rate limited to 5 requests per minute per shopper id.
     *
     * @param giftcardsBalanceRequest Detail of the Gift Card to recieve balences for.
     */
    fun balance(giftcardsBalanceRequest: GiftcardsBalanceRequest): ApiResult<GiftcardsBalanceResponse>
}
