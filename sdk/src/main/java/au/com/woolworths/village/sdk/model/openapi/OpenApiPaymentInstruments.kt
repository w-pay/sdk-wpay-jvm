package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.openapi.dto.GetCustomerPaymentInstrumentsResultsData
import au.com.woolworths.village.sdk.openapi.dto.GetCustomerPaymentInstrumentsResultsDataCreditCards
import au.com.woolworths.village.sdk.openapi.dto.GetCustomerPaymentInstrumentsResultsDataGiftCards
import au.com.woolworths.village.sdk.openapi.dto.GetCustomerPaymentInstrumentsResultsDataStepUp
import org.threeten.bp.OffsetDateTime
import java.net.URL

class OpenApiPaymentInstruments(
    private val instruments: GetCustomerPaymentInstrumentsResultsData
): PaymentInstruments {
    override fun creditCards(): List<CreditCard> {
        if (instruments.creditCards != null && instruments.creditCards.size > 0) {
            return instruments.creditCards.map { OpenApiCreditCard(it) }
        }

        return emptyList()
    }

    override fun giftCards(): List<GiftCard> {
        if (instruments.giftCards != null && instruments.giftCards.size > 0) {
            return instruments.giftCards.map { OpenApiGiftCard(it) }
        }

        return emptyList()
    }
}

class OpenApiCreditCard(
    private val card: GetCustomerPaymentInstrumentsResultsDataCreditCards
): CreditCard {
    override fun paymentInstrumentId(): String {
        return card.paymentInstrumentId
    }

    override fun paymentToken(): String {
        return card.paymentToken
    }

    override fun primary(): Boolean {
        return card.primary
    }

    override fun status(): PaymentInstrument.InstrumentStatus {
        return PaymentInstrument.InstrumentStatus.valueOf(card.status.value)
    }

    override fun cardName(): String {
        return card.cardName
    }

    override fun cvvValidated(): Boolean {
        return card.cvvValidated
    }

    override fun expired(): Boolean {
        return card.expired
    }

    override fun expiryMonth(): String {
        return card.expiryMonth
    }

    override fun expiryYear(): String {
        return card.expiryYear
    }

    override fun requiresCVV(): Boolean {
        return card.requiresCVV
    }

    override fun scheme(): String {
        return card.scheme
    }

    override fun stepUp(): PaymentInstrumentStepUp {
        return OpenApiPaymentInstrumentStepUp(card.stepUp)
    }

    override fun updateURL(): URL {
        return URL(card.updateURL)
    }

    override fun allowed(): Boolean {
        return card.allowed
    }

    override fun cardSuffix(): String {
        return card.cardSuffix
    }

    override fun lastUpdated(): OffsetDateTime {
        return card.lastUpdated
    }

    override fun lastUsed(): OffsetDateTime {
        return card.lastUsed
    }
}

class OpenApiGiftCard(
    private val card: GetCustomerPaymentInstrumentsResultsDataGiftCards
): GiftCard {
    override fun programName(): String {
        return card.programName
    }

    override fun allowed(): Boolean {
        return card.allowed
    }

    override fun cardSuffix(): String {
        return card.cardSuffix
    }

    override fun lastUpdated(): OffsetDateTime {
        return card.lastUpdated
    }

    override fun lastUsed(): OffsetDateTime {
        return card.lastUsed
    }

    override fun paymentInstrumentId(): String {
        return card.paymentInstrumentId
    }

    override fun paymentToken(): String {
        return card.paymentToken
    }

    override fun primary(): Boolean {
        return card.primary
    }

    override fun status(): PaymentInstrument.InstrumentStatus {
        return PaymentInstrument.InstrumentStatus.valueOf(card.status.value)
    }
}

class OpenApiPaymentInstrumentStepUp(
    private val stepUp: GetCustomerPaymentInstrumentsResultsDataStepUp
): PaymentInstrumentStepUp {
    override fun type(): String {
        return stepUp.type
    }

    override fun mandatory(): Boolean {
        return stepUp.mandatory
    }

    override fun url(): URL {
        return URL(stepUp.url)
    }
}