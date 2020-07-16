package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.model.AllPaymentInstruments
import au.com.woolworths.village.sdk.model.CustomerPaymentRequest
import au.com.woolworths.village.sdk.model.CustomerTransactionSummary
import au.com.woolworths.village.sdk.model.PaymentInstrument

/**
 * Entry point into the SDK. It is responsible for managing the relationship between app
 * concerns, and calling the API.
 */
class CustomerVillage<A : Any>(
    private val api: VillageCustomerApiRepository,
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
        return api.retrievePaymentRequestDetailsByQRCode(qrCode)
    }

    fun retrievePaymentInstruments(wallet: Wallet): ApiResult<AllPaymentInstruments> {
        return api.retrievePaymentInstruments(wallet)
    }

    fun makePayment(
        paymentRequest: CustomerPaymentRequest,
        instrument: PaymentInstrument
    ): ApiResult<CustomerTransactionSummary> {
        return api.makePayment(paymentRequest, instrument)
    }
}