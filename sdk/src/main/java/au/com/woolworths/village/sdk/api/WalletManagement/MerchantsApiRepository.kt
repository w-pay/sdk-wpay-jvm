package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.ApiResult

/**
 * @category API
 */
interface MerchantsApiRepository {
	/**
	 * Get the current configuration set of the merchant. If this API is called without a valid access token it is IP restricted to allow unauthenticated server side calls.
	 */
	fun profile(): ApiResult<MerchantProfileResponse>
}
