package au.com.woolworths.village.sdk.api.digitalpay

import au.com.woolworths.village.sdk.api.walletmanagement.*

interface DigitalPayRepository {
    val androidPay: AndroidPayApi
    val applePay: ApplePayApi
    val cards: CardCaptureApi
    val giftcards: GiftCardsApi
    val gifting: GiftingApi
    val googlePay: GooglePayApi
    val instruments: InstrumentsApiRepository
    val merchants: MerchantsApi
    val openPay: OpenPayApi
    val paymentAgreements: PaymentAgreementApi
    val payments: PaymentsApi
    val paypal: PayPalApi
    val transactions: TransactionsApi
    val wallet: WalletApi
}
