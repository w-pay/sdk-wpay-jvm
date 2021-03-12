package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.ApiResult

/**
 * @category API
 */
interface WalletApiRepository {
	/**
	 * Delete a consumers wallet. This API is IP restricted to allow unauthenticated server side calls.
	 *
	 * @param walletDeleteRequest Detail of the consumer who will have their the wallet deleted.
	 */
	fun delete(walletDeleteRequest: WalletDeleteRequest): ApiResult<WalletDeleteResponse>
}
