package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.api.*
import au.com.woolworths.village.sdk.api.digitalpay.DigitalPayRepository
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
interface VillageMerchantApiRepository {
    val admin: AdministrationApi
    val dp: DigitalPayRepository
    val payments: MerchantPaymentsRepository
    val paymentAgreements: MerchantPaymentAgreementsApi
    val paymentSession: MerchantPaymentSessionsApi
    val preferences: MerchantPreferencesApi
    val qr: QRCodeRepository
    val schemas: SchemasRepository
    val transactions: MerchantTransactionsRepository

    /**
     * Options that were given at SDK initialisation
     */
    val options: VillageMerchantOptions

    /**
     * An [ApiAuthenticator] that can be used to update the access token the SDK uses
     */
    var authenticator: ApiAuthenticator<HasAccessToken>
}