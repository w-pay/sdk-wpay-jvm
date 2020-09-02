package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

interface Payment: Serializable {
    val paymentRequestId: String

    val merchantReferenceId: String

    val grossAmount: BigDecimal
}