package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.CustomerPreferences

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