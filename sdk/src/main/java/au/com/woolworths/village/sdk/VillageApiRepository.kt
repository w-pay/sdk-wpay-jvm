package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.CustomerPaymentDetails
import au.com.woolworths.village.sdk.model.PaymentInstrument
import au.com.woolworths.village.sdk.model.PaymentInstruments
import au.com.woolworths.village.sdk.model.PaymentResult

/**
 * Defines the low level API operations that the SDK can use to call the Village API
 */
interface VillageApiRepository {
    fun retrievePaymentRequestDetails(qrCodeId: String): ApiResult<CustomerPaymentDetails>

    fun retrievePaymentInstruments(): ApiResult<PaymentInstruments>

    fun makePayment(
        paymentDetails: CustomerPaymentDetails,
        instrument: PaymentInstrument
    ): ApiResult<PaymentResult>
}