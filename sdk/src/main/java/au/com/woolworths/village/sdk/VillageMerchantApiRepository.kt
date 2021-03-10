package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.api.*
import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.auth.HasAccessToken

/**
 * Defines the API operations that the SDK can use to call the Merchant Village API
 *
 * The SDK is technology agnostic with applications being able to choose an implementation that
 * meets the needs and preexisting technology choices of the application.
 *
 * Implementations of the protocol may provide additional constraints on the user.
 */
interface VillageMerchantApiRepository : ApiAuthenticator<HasAccessToken> {
    val admin: AdministrationApiRepository
    val payments: MerchantPaymentsRepository
    val paymentSession: MerchantPaymentSessionsRepository
    val preferences: MerchantPreferencesRepository
    val qr: QRCodeRepository
    val schemas: SchemasRepository
    val transactions: MerchantTransactionsRepository

    /**
     * Options that were given at SDK initialisation
     */
    val options: VillageMerchantOptions
}