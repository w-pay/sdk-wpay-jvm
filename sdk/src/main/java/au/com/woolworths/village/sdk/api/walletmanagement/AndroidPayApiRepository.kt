package au.com.woolworths.village.sdk.api.walletmanagement

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizeAndroidPayRequest
import au.com.woolworths.village.sdk.model.walletmanagement.TokenizeAndroidPayResponse

/**
 * @category API
 */
interface AndroidPayApiRepository {
    /**
     * Create a payment instrument id for a provided Android Pay wallet item.
     *
     * @param tokenizeAndroidPayRequest Detail of the Android Pay wallet item to be tokenized.
     */
    fun tokenize(
        tokenizeAndroidPayRequest: TokenizeAndroidPayRequest
    ): ApiResult<TokenizeAndroidPayResponse>

    /**
     * Update an Android Pay payment instrument.
     *
     * @param paymentInstrumentId The id of the Android Pay payment instrument to update.
     * @param tokenizeAndroidPayRequest Detail of the Android Pay wallet item to be tokenized.
     */
    fun update(
        paymentInstrumentId: String,
        tokenizeAndroidPayRequest: TokenizeAndroidPayRequest
    ): ApiResult<TokenizeAndroidPayResponse>
}
