package au.com.woolworths.village.sdk.api.digitalpay

import au.com.woolworths.village.sdk.SdkApiClient
import au.com.woolworths.village.sdk.SdkJsonUnmarshaller
import au.com.woolworths.village.sdk.api.walletmanagement.*

class DigitalPayRepository(
    client: SdkApiClient,
    unmarshall: SdkJsonUnmarshaller
) {
    val androidPay: AndroidPayApi = AndroidPayApi(client, unmarshall)
    val applePay: ApplePayApi = ApplePayApi(client, unmarshall)
    val cards: CardCaptureApi = CardCaptureApi(client, unmarshall)
    val giftCards: GiftCardsApi = GiftCardsApi(client, unmarshall)
    val gifting: GiftingApi = GiftingApi(client, unmarshall)
    val googlePay: GooglePayApi = GooglePayApi(client, unmarshall)
    val instruments: InstrumentsApi = InstrumentsApi(client, unmarshall)
    val merchants: MerchantsApi = MerchantsApi(client, unmarshall)
    val openPay: OpenPayApi = OpenPayApi(client, unmarshall)
    val paymentAgreements: PaymentAgreementApi = PaymentAgreementApi(client, unmarshall)
    val payments: PaymentsApi = PaymentsApi(client, unmarshall)
    val paypal: PayPalApi = PayPalApi(client, unmarshall)
    val transactions: TransactionsApi = TransactionsApi(client, unmarshall)
    val wallet: WalletApi = WalletApi(client, unmarshall)
}
