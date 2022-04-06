package au.com.wpay.sdk

import au.com.wpay.sdk.api.*
import au.com.wpay.sdk.api.digitalpay.DigitalPayRepository

/**
 * Defines the API operations that the SDK can use to call the Customer WPay API
 *
 * The SDK is technology agnostic with applications being able to choose implementations of
 * constructor arguments that meets the needs and preexisting technology choices of the application.
 */
class WPayCustomerApi(
    client: SdkApiClientFactory,
    marshall: SdkJsonMarshaller,
    unmarshall: SdkJsonUnmarshaller,

    /**
     * Options that were given at SDK initialisation
     */
    val options: WPayCustomerOptions
) {
    val admin: AdministrationApi = AdministrationApi(client, marshall, unmarshall)
    val dp: DigitalPayRepository = DigitalPayRepository(client, marshall, unmarshall)
    val instruments: PaymentInstrumentsApi = PaymentInstrumentsApi(client, marshall, unmarshall)
    val paymentAgreements: CustomerPaymentAgreementsApi = CustomerPaymentAgreementsApi(client, marshall, unmarshall)
    val paymentRequests: CustomerPaymentRequestsApi = CustomerPaymentRequestsApi(client, marshall, unmarshall)
    val paymentSessions: CustomerPaymentSessionsApi = CustomerPaymentSessionsApi(client, marshall, unmarshall)
    val preferences: CustomerPreferencesApi = CustomerPreferencesApi(client, marshall, unmarshall)
    val termsAndConditions: CustomerTermsAndConditionsApi = CustomerTermsAndConditionsApi(client, marshall, unmarshall)
    val transactions: CustomerTransactionsApi = CustomerTransactionsApi(client, marshall, unmarshall)
}
