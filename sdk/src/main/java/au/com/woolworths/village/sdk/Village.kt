package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.model.CustomerPaymentRequest
import au.com.woolworths.village.sdk.model.PaymentInstrument
import au.com.woolworths.village.sdk.model.PaymentInstruments
import au.com.woolworths.village.sdk.model.PaymentResult

/**
 * Entry point into the SDK. It is responsible for managing the relationship between app
 * concerns, and calling the API.
 */
class Village<A : Any>(
    private val api: VillageApiRepository,
    private val authenticator: ApiAuthenticator<A>
): Configurable {
    override fun setHost(host: String) {
        authenticator.setHost(host)
        api.setHost(host)
    }

    fun authenticate(): ApiResult<A> {
        return authenticator.authenticate()
    }

    fun retrievePaymentDetails(qrCode: String): ApiResult<CustomerPaymentRequest> {
        return api.retrievePaymentRequestDetails(qrCode)
    }

    fun retrievePaymentInstruments(): ApiResult<PaymentInstruments> {
        return api.retrievePaymentInstruments()
    }

    fun makePayment(
        paymentRequest: CustomerPaymentRequest,
        instrument: PaymentInstrument
    ): ApiResult<PaymentResult> {
        return api.makePayment(paymentRequest, instrument)
    }
}