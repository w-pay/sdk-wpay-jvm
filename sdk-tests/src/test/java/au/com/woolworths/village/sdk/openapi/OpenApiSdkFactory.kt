package au.com.woolworths.village.sdk.openapi

import au.com.woolworths.village.sdk.*

const val NO_ROOT = ""

class OpenApiSdkFactory: SdkFactory {
    override fun createCustomerApi(): VillageCustomerApiRepository {
        return OpenApiVillageCustomerApiRepository(TestRequestHeadersFactory(), NO_ROOT)
    }

    override fun createMerchantApi(): VillageMerchantApiRepository {
        return OpenApiVillageMerchantApiRepository(TestRequestHeadersFactory(), NO_ROOT)
    }
}

class TestRequestHeadersFactory: RequestHeadersFactory {
    override fun createHeaders(): Map<String, String> {
        return hashMapOf(
            X_WALLET_ID to "a value"
        )
    }
}