package au.com.woolworths.village.sdk.openapi

import au.com.woolworths.village.sdk.RequestHeadersFactory
import au.com.woolworths.village.sdk.SdkFactory
import au.com.woolworths.village.sdk.VillageCustomerApiRepository
import au.com.woolworths.village.sdk.VillageMerchantApiRepository

const val NO_ROOT = ""

class OpenApiSdkFactory: SdkFactory {
    override fun createCustomerApi(): VillageCustomerApiRepository {
        return OpenApiVillageCustomerApiRepository(NoRequestHeadersFactory(), NO_ROOT)
    }

    override fun createMerchantApi(): VillageMerchantApiRepository {
        return OpenApiVillageMerchantApiRepository(NoRequestHeadersFactory(), NO_ROOT)
    }
}

class NoRequestHeadersFactory: RequestHeadersFactory {
    override fun createHeaders(): Map<String, String> {
        return emptyMap()
    }
}