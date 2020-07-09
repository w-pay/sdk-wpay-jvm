package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.PaymentInstrument
import au.com.woolworths.village.sdk.model.PaymentInstrumentAddition
import au.com.woolworths.village.sdk.model.PaymentInstrumentStepUp
import org.threeten.bp.OffsetDateTime

fun aSelectedPaymentInstrument(): PaymentInstrument = TestPaymentInstrument()
fun aNewPaymentInstrument(): PaymentInstrumentAddition = TestPaymentInstrumentAddition()

class TestPaymentInstrument: PaymentInstrument {
    override fun allowed(): Boolean {
        return true
    }

    override fun cardSuffix(): String {
        return "1234"
    }

    override fun lastUpdated(): OffsetDateTime {
        return OffsetDateTime.now()
    }

    override fun lastUsed(): OffsetDateTime {
        return OffsetDateTime.now()
    }

    override fun paymentInstrumentId(): String {
        return "abc123"
    }

    override fun paymentToken(): String {
        return "def123fgh"
    }

    override fun primary(): Boolean {
        return true
    }

    override fun status(): PaymentInstrument.InstrumentStatus {
        return PaymentInstrument.InstrumentStatus.VERIFIED
    }

    override fun stepUp(): PaymentInstrumentStepUp? {
        return null
    }
}

class TestPaymentInstrumentAddition: PaymentInstrumentAddition {
    override fun clientReference(): String {
        return "abc123"
    }

}