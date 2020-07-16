package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.DynamicPayload
import au.com.woolworths.village.sdk.model.UpdatePaymentSessionRequest

class TestUpdatePaymentSessionRequest: UpdatePaymentSessionRequest {
    override fun additionalInfo(): DynamicPayload {
        return TestUpdatePaymentSessionRequestPayload()
    }
}

class TestUpdatePaymentSessionRequestPayload: DynamicPayload {
    override fun schemaId(): String? {
        return "abc123"
    }

    override fun payload(): Map<String, Any> {
        return emptyMap()
    }
}