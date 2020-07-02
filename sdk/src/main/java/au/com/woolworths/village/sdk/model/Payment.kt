package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

interface Payment: Serializable {
    fun paymentRequestId(): String

    fun merchantReferenceId(): String

    fun grossAmount(): BigDecimal
}