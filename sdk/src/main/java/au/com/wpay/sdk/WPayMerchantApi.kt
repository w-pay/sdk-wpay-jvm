package au.com.wpay.sdk

import au.com.wpay.sdk.api.*
import au.com.wpay.sdk.api.digitalpay.DigitalPayRepository

/**
 * Defines the API operations that the SDK can use to call the Merchant WPay API
 *
 * The SDK is technology agnostic with applications being able to choose an implementation that
 * meets the needs and preexisting technology choices of the application.
 *
 * Implementations of the protocol may provide additional constraints on the user.
 */
class WPayMerchantApi(
    client: SdkApiClientFactory,
    marshall: SdkJsonMarshaller,
    unmarshall: SdkJsonUnmarshaller,

    /**
     * Options that were given at SDK initialisation
     */
    val options: WPayMerchantOptions
) {
    val admin: AdministrationApi = AdministrationApi(client, marshall, unmarshall)
    val dp: DigitalPayRepository = DigitalPayRepository(client, marshall, unmarshall)
    val payments: MerchantPaymentsApi = MerchantPaymentsApi(client, marshall, unmarshall)
    val paymentAgreements: MerchantPaymentAgreementsApi = MerchantPaymentAgreementsApi(client, marshall, unmarshall)
    val paymentSession: MerchantPaymentSessionsApi = MerchantPaymentSessionsApi(client, marshall, unmarshall)
    val preferences: MerchantPreferencesApi = MerchantPreferencesApi(client, marshall, unmarshall)
    val qr: QRCodeApi = QRCodeApi(client, marshall, unmarshall)
    val schemas: SchemasApi = SchemasApi(client, marshall, unmarshall)
    val transactions: MerchantTransactionsApi = MerchantTransactionsApi(client, marshall, unmarshall)
}
