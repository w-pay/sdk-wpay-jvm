package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.ApiResult

/**
 * @category API
 */
interface ApplePayApiRepository {
	/**
	 * Create a payment instrument id for a provided Apple Pay wallet item.
	 *
	 * @param tokenizeApplePayRequest Detail of the Apple Pay wallet item to be tokenized.
	 */
	fun tokenize(tokenizeApplePayRequest: TokenizeApplePayRequest): ApiResult<TokenizeApplePayResponse>

	/**
	 * Create a payment instrument id for a provided Apple Pay wallet item of a guest user.
	 *
	 * @param tokenizeApplePayRequest Detail of the Apple Pay wallet item to be tokenized.
	 */
	fun guestTokenize(
		tokenizeApplePayRequest: TokenizeApplePayRequest
	): ApiResult<TokenizeApplePayResponse>

	/**
	 * Update an Apple Pay payment instrument.
	 *
	 * @param paymentInstrumentId The id of the Apple Pay payment instrument to update.
	 * @param tokenizeApplePayRequest Detail of the Apple Pay wallet item to be tokenized.
	 */
	fun update(
		paymentInstrumentId: String,
		tokenizeApplePayRequest: TokenizeApplePayRequest
	): ApiResult<TokenizeApplePayResponse>
}
