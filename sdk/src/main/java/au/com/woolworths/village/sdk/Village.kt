package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.CustomerPaymentDetails
import au.com.woolworths.village.sdk.model.PaymentInstrument
import au.com.woolworths.village.sdk.model.PaymentInstruments
import au.com.woolworths.village.sdk.model.PaymentResult

/**
 * Entry point into the SDK. It is responsible for managing the relationship between app
 * concerns, and calling the API.
 */
class Village(
    private val api: VillageApiRepository
) {
    fun retrievePaymentDetails(qrCode: String): ApiResult<CustomerPaymentDetails> {
        return api.retrievePaymentRequestDetails(qrCode)
    }

    fun retrievePaymentInstruments(): ApiResult<PaymentInstruments> {
        return api.retrievePaymentInstruments()
    }

    fun makePayment(
        paymentDetails: CustomerPaymentDetails,
        instrument: PaymentInstrument
    ): ApiResult<PaymentResult> {
        return api.makePayment(paymentDetails, instrument)
    }
}