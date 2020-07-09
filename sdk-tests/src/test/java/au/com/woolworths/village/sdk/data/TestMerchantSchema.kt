package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.MerchantSchema

fun aNewSchema(): MerchantSchema = TestMerchantSchema()

class TestMerchantSchema: MerchantSchema {
    override fun schema(): Map<String, Any> {
        return emptyMap()
    }

    override fun type(): String? {
        return null
    }

    override fun description(): String? {
        return null
    }
}