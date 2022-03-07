package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.PaymentPreferences
import java.util.*

fun paymentPreferences(): PaymentPreferences =
    PaymentPreferences(UUID.randomUUID().toString(), null)