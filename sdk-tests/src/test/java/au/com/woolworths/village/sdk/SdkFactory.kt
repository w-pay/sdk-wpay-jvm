package au.com.woolworths.village.sdk

interface SdkFactory {
    fun createCustomerApi(): VillageCustomerApiRepository

    fun createMerchantApi(): VillageMerchantApiRepository
}