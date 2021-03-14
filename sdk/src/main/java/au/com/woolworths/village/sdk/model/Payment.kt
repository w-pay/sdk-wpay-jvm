package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * Properties common to all Payments and Payment Requests
 */
interface Payment : Serializable {
    /** The ID of this payment request */
    val paymentRequestId: String

    /** The unique reference for the payment as defined by the Merchant */
    val merchantReferenceId: String

    /** The gross amount to be paid. Must be positive except for refunds */
    val grossAmount: BigDecimal
}