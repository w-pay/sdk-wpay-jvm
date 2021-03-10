package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult

/**
 * Map of customer preferences.
 */
typealias CustomerPreferences = Map<String, Map<String, String>>

interface CustomerPreferencesRepository {
    /**
     * Retrieve a customer's preferences
     */
    fun get(): ApiResult<CustomerPreferences>

    /**
     * Update the preferences for a customer
     *
     * @param preferences The preferences of the customer.
     */
    fun set(preferences: CustomerPreferences): ApiResult<Unit>
}