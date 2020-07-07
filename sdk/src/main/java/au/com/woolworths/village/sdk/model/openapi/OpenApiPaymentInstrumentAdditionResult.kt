package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.PaymentInstrumentAdditionResult
import au.com.woolworths.village.sdk.openapi.dto.InitiatePaymentInstrumentAdditionResultsData

class OpenApiPaymentInstrumentAdditionResult(
    private val result: InitiatePaymentInstrumentAdditionResultsData
): PaymentInstrumentAdditionResult {
    override fun cardCaptureURL(): String {
        return result.cardCaptureURL
    }

    override fun transactionRef(): String? {
        return result.transactionRef
    }
}