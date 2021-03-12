package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.ApiResult

/**
 * @category API
 */
interface CustomerTermsAndConditionsApiRepository {
	/**
	 * Get the terms and conditions agreed to by the customer
	 *
	 * @param type The type of Ts and Cs that the shopper/customer has agreed to. Defaults to all if absent
	 * @param version The version of Ts and Cs that the shopper/customer has agreed to.  Defaults to all if absent
	 */
	fun get(type: String?, version: String?): ApiResult<TermsAndConditionsAcceptances>

	/**
	 * Customer accepts terms and conditions"
	 *
	 * @param type The type of Ts and Cs that the shopper/customer has agreed to. Defaults to all if absent
	 */
	fun accept(acceptTermsAndConditionsRequest: AcceptTermsAndConditionsRequest): ApiResult<Void>
}
