package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.CustomerTransactions
import au.com.woolworths.village.sdk.openapi.dto.CustomerTransactionSummaryAllOfInstruments
import java.math.BigDecimal

class OpenApiUsedPaymentInstrument(
    private val instrument: CustomerTransactionSummaryAllOfInstruments
): CustomerTransactions.UsedPaymentInstrument {
    override fun paymentInstrumentId(): String {
        return instrument.paymentInstrumentId
    }

    override fun amount(): BigDecimal {
        return instrument.amount
    }

    override fun paymentTransactionRef(): String? {
        return instrument.paymentTransactionRef
    }
}