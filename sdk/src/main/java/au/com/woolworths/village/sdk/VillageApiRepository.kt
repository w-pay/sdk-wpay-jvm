package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.model.CustomerPaymentRequest
import au.com.woolworths.village.sdk.model.PaymentInstrument
import au.com.woolworths.village.sdk.model.PaymentInstruments
import au.com.woolworths.village.sdk.model.PaymentResult

/**
 * Defines the low level API operations that the SDK can use to call the Village API
 */
interface VillageApiRepository: Configurable {
    fun retrievePaymentRequestDetails(qrCodeId: String): ApiResult<CustomerPaymentRequest>

    fun retrievePaymentInstruments(): ApiResult<PaymentInstruments>

    fun makePayment(
        paymentRequest: CustomerPaymentRequest,
        instrument: PaymentInstrument
    ): ApiResult<PaymentResult>
}