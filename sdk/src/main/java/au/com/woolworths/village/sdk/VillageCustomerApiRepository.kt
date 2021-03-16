package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.api.*
import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.auth.HasAccessToken

/**
 * Defines the API operations that the SDK can use to call the Customer Village API
 *
 * The SDK is technology agnostic with applications being able to choose an implementation that
 * meets the needs and preexisting technology choices of the application.
 *
 * Implementations of the protocol may provide additional constraints on the user.
 */
interface VillageCustomerApiRepository {
    val admin: AdministrationApiRepository;
    val instruments: PaymentInstrumentsRepository;
    val paymentRequests: CustomerPaymentRequestsRepository;
    val paymentSessions: CustomerPaymentSessionsRepository;
    val preferences: CustomerPreferencesRepository;
    val transactions: CustomerTransactionsRepository;

    /**
     * Options that were given at SDK initialisation
     */
    val options: VillageCustomerOptions;
}