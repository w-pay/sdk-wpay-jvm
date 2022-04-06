package au.com.wpay.sdk.api.digitalpay

import au.com.wpay.sdk.SdkApiClientFactory
import au.com.wpay.sdk.SdkJsonMarshaller
import au.com.wpay.sdk.SdkJsonUnmarshaller
import au.com.wpay.sdk.api.walletmanagement.*

class DigitalPayRepository(
    client: SdkApiClientFactory,
    marshall: SdkJsonMarshaller,
    unmarshall: SdkJsonUnmarshaller
) {
    val androidPay: AndroidPayApi = AndroidPayApi(client, marshall, unmarshall)
    val applePay: ApplePayApi = ApplePayApi(client, marshall, unmarshall)
    val cards: CardCaptureApi = CardCaptureApi(client, marshall, unmarshall)
    val giftCards: GiftCardsApi = GiftCardsApi(client, marshall, unmarshall)
    val gifting: GiftingApi = GiftingApi(client, marshall, unmarshall)
    val googlePay: GooglePayApi = GooglePayApi(client, marshall, unmarshall)
    val instruments: InstrumentsApi = InstrumentsApi(client, marshall, unmarshall)
    val merchants: MerchantsApi = MerchantsApi(client, marshall, unmarshall)
    val openPay: OpenPayApi = OpenPayApi(client, marshall, unmarshall)
    val paymentAgreements: PaymentAgreementApi = PaymentAgreementApi(client, marshall, unmarshall)
    val payments: PaymentsApi = PaymentsApi(client, marshall, unmarshall)
    val paypal: PayPalApi = PayPalApi(client, marshall, unmarshall)
    val transactions: TransactionsApi = TransactionsApi(client, marshall, unmarshall)
    val wallet: WalletApi = WalletApi(client, marshall, unmarshall)
}
