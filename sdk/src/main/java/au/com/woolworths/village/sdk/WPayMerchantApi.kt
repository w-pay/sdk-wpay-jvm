package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.api.*
import au.com.woolworths.village.sdk.api.digitalpay.DigitalPayRepository
import au.com.woolworths.village.sdk.auth.ApiAuthenticator
import au.com.woolworths.village.sdk.auth.HasAccessToken

/**
 * Defines the API operations that the SDK can use to call the Merchant WPay API
 *
 * The SDK is technology agnostic with applications being able to choose an implementation that
 * meets the needs and preexisting technology choices of the application.
 *
 * Implementations of the protocol may provide additional constraints on the user.
 */
class WPayMerchantApi(
    client: SdkApiClient,
    unmarshall: SdkJsonUnmarshaller,

    /**
     * Options that were given at SDK initialisation
     */
    val options: WPayMerchantOptions
) {
    val admin: AdministrationApi = AdministrationApi(client, unmarshall)
    val dp: DigitalPayRepository = DigitalPayRepository(client, unmarshall)
    val payments: MerchantPaymentsApi = MerchantPaymentsApi(client, unmarshall)
    val paymentAgreements: MerchantPaymentAgreementsApi = MerchantPaymentAgreementsApi(client, unmarshall)
    val paymentSession: MerchantPaymentSessionsApi = MerchantPaymentSessionsApi(client, unmarshall)
    val preferences: MerchantPreferencesApi = MerchantPreferencesApi(client, unmarshall)
    val qr: QRCodeApi = QRCodeApi(client, unmarshall)
    val schemas: SchemasApi = SchemasApi(client, unmarshall)
    val transactions: MerchantTransactionsApi = MerchantTransactionsApi(client, unmarshall)
}
