package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.SecondaryPaymentInstrument
import java.math.BigDecimal
import java.util.*

fun aSecondaryPaymentInstrument(): SecondaryPaymentInstrument =
    SecondaryPaymentInstrument(UUID.randomUUID().toString(), BigDecimal(10))