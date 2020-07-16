package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.CreatePaymentSessionRequest
import au.com.woolworths.village.sdk.model.DynamicPayload

class TestCreatePaymentSessionRequest: CreatePaymentSessionRequest {
    override fun location(): String {
        return "somewhere"
    }

    override fun additionalInfo(): DynamicPayload {
        return TestCreatePaymentSessionRequestPayload()
    }
}

class TestCreatePaymentSessionRequestPayload: DynamicPayload {
    override fun schemaId(): String? {
        return "abc123"
    }

    override fun payload(): Map<String, Any> {
        return emptyMap()
    }
}