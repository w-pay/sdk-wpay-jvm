package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.model.*

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

    fun retrievePaymentRequestDetailsByQRCode(qrCodeId: String): ApiResult<CustomerPaymentRequest> {
        return api.retrievePaymentRequestDetailsByQRCode(qrCodeId)
    }

    fun retrievePaymentInstruments(wallet: Wallet): ApiResult<AllPaymentInstruments> {
        return api.retrievePaymentInstruments(wallet)
    }

    fun makePayment(
        paymentRequestId: String,
        instrument: PaymentInstrumentIdentifier,
        secondaryInstruments: List<SecondaryPaymentInstrument>?,
        clientReference: String?,
        challengeResponses: List<ChallengeResponse>?
    ): ApiResult<CustomerTransactionSummary> {
        return api.makePayment(
            paymentRequestId,
            instrument,
            secondaryInstruments,
            clientReference,
            challengeResponses
        )
    }

    fun retrievePaymentSessionById(paymentSessionId: String): ApiResult<PaymentSession> {
        return api.retrievePaymentSessionById(paymentSessionId)
    }

    fun retrievePaymentSessionByQRCode(qrCodeId: String): ApiResult<PaymentSession> {
        return api.retrievePaymentSessionByQRCode(qrCodeId)
    }
}