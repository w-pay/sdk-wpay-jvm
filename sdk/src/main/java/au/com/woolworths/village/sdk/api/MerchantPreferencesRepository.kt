package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult

/**
 * Map of merchant preferences.
 */
typealias MerchantPreferences = Map<String, Map<String, String>>

interface MerchantPreferencesRepository {
    /**
     * Retrieve a merchant's preferences.
     */
    fun get(): ApiResult<MerchantPreferences>

    /**
     * Update a merchant's preferences
     *
     * @param preferences The preferences to use
     */
    fun set(preferences: MerchantPreferences): ApiResult<Unit>
}