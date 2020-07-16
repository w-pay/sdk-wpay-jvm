package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.Wallet
import au.com.woolworths.village.sdk.model.*
import au.com.woolworths.village.sdk.openapi.dto.GetCustomerPaymentInstrumentsResultsDataEverydayPay
import org.threeten.bp.OffsetDateTime
import java.net.URL
import java.util.*

class OpenApiAllPaymentInstruments(
    creditCards: List<au.com.woolworths.village.sdk.openapi.dto.CreditCard>,
    giftCards: List<au.com.woolworths.village.sdk.openapi.dto.GiftCard>,
    private val everydayPay: GetCustomerPaymentInstrumentsResultsDataEverydayPay?
) : OpenApiPaymentInstruments(creditCards, giftCards, Wallet.MERCHANT), AllPaymentInstruments {
    override fun everydayPay(): PaymentInstruments? {
        return everydayPay?.let { OpenApiPaymentInstruments(it.creditCards, it.giftCards, Wallet.EVERYDAY_PAY) }
    }
}

open class OpenApiPaymentInstruments(
    private val creditCards: List<au.com.woolworths.village.sdk.openapi.dto.CreditCard>,
    private val giftCards: List<au.com.woolworths.village.sdk.openapi.dto.GiftCard>,
    private val wallet: Wallet
): PaymentInstruments {
    override fun creditCards(): List<CreditCard> {
        return creditCards.map { OpenApiCreditCard(it, wallet) }
    }

    override fun giftCards(): List<GiftCard> {
        return giftCards.map { OpenApiGiftCard(it, wallet) }
    }
}

class OpenApiCreditCard(
    private val card: au.com.woolworths.village.sdk.openapi.dto.CreditCard,
    private val wallet: Wallet
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
        return PaymentInstrument.InstrumentStatus.valueOf(card.status.value.toUpperCase(Locale.ROOT))
    }

    override fun wallet(): Wallet {
        return wallet
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

    override fun stepUp(): CreditCardStepUp {
        return OpenApiCreditCardStepUp(card.stepUp)
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

    override fun lastUsed(): OffsetDateTime? {
        return card.lastUsed
    }
}

class OpenApiGiftCard(
    private val card: au.com.woolworths.village.sdk.openapi.dto.GiftCard,
    private val wallet: Wallet
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

    override fun lastUsed(): OffsetDateTime? {
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

    override fun wallet(): Wallet {
        return wallet
    }

    override fun stepUp(): GiftCardStepUp? {
        return card.stepUp?.let { OpenApiGiftCardStepUp(it) }
    }
}

class OpenApiCreditCardStepUp(
    private val stepUp: au.com.woolworths.village.sdk.openapi.dto.CreditCardStepUp
): CreditCardStepUp {
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

class OpenApiGiftCardStepUp(
    private val stepUp: au.com.woolworths.village.sdk.openapi.dto.GiftCardStepUp
): GiftCardStepUp {
    override fun type(): String {
        return stepUp.type
    }

    override fun mandatory(): Boolean {
        return stepUp.mandatory
    }
}