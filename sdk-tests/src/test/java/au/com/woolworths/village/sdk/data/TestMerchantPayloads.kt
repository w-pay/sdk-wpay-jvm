package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.MerchantPayload
import au.com.woolworths.village.sdk.model.PosPayload

fun aNewPosPayload(): PosPayload {
    return TestPosPayload()
}

fun aNewMerchantPayload(): MerchantPayload {
    return TestMerchantPayload()
}

class TestPosPayload: PosPayload {
    override fun schemaId(): String? {
        return "abc123"
    }

    override fun payload(): Map<String, Any> {
        return emptyMap()
    }
}

class TestMerchantPayload: MerchantPayload {
    override fun schemaId(): String? {
        return "abc123"
    }

    override fun payload(): Map<String, Any> {
        return emptyMap()
    }
}