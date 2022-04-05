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
    client: SdkApiClient,
    unmarshall: SdkJsonUnmarshaller,

    /**
     * Options that were given at SDK initialisation
     */
    val options: WPayCustomerOptions
) {
    val admin: AdministrationApi = AdministrationApi(client, unmarshall)
    val dp: DigitalPayRepository = DigitalPayRepository(client, unmarshall)
    val instruments: PaymentInstrumentsApi = PaymentInstrumentsApi(client, unmarshall)
    val paymentAgreements: CustomerPaymentAgreementsApi = CustomerPaymentAgreementsApi(client, unmarshall)
    val paymentRequests: CustomerPaymentRequestsApi = CustomerPaymentRequestsApi(client, unmarshall)
    val paymentSessions: CustomerPaymentSessionsApi = CustomerPaymentSessionsApi(client, unmarshall)
    val preferences: CustomerPreferencesApi = CustomerPreferencesApi(client, unmarshall)
    val termsAndConditions: CustomerTermsAndConditionsApi = CustomerTermsAndConditionsApi(client, unmarshall)
    val transactions: CustomerTransactionsApi = CustomerTransactionsApi(client, unmarshall)
}
