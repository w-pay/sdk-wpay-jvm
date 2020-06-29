package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.openapi.dto.GetCustomerPaymentInstrumentsResultsData
import au.com.woolworths.village.sdk.openapi.dto.GetCustomerPaymentInstrumentsResultsDataCreditCards
import au.com.woolworths.village.sdk.model.CreditCard
import au.com.woolworths.village.sdk.model.GiftCard
import au.com.woolworths.village.sdk.model.PaymentInstruments

class OpenApiPaymentInstruments(
    private val instruments: GetCustomerPaymentInstrumentsResultsData
): PaymentInstruments {
    override fun creditCards(): List<CreditCard> {
        if (instruments.creditCards != null && instruments.creditCards.size > 0) {
            return instruments.creditCards.map { OpenApiCreditCard(it) }
        }

        // TODO: Transform gift cards

        return emptyList()
    }

    override fun giftCards(): List<GiftCard> {
        return emptyList()
    }
}

class OpenApiCreditCard(
    private val card: GetCustomerPaymentInstrumentsResultsDataCreditCards
): CreditCard {
    override fun paymentInstrumentId(): String {
        return card.paymentInstrumentId
    }

    override fun cardSuffix(): String {
        return card.cardSuffix
    }
}