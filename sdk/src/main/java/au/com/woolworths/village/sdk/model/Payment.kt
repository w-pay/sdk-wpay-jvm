package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.model.digitalpay.PaymentTransactionType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * Properties common to all Payments and Payment Requests
 */
interface PaymentType : ModelType {
    /** The ID of this payment request */
    val paymentRequestId: String

    /** The unique reference for the payment as defined by the Merchant */
    val merchantReferenceId: String

    /** The gross amount to be paid. Must be positive except for refunds */
    val grossAmount: BigDecimal
}

@Serializable
data class PaymentDetailsDTO(
    val primaryInstrumentId: String? = null,
    val secondaryInstruments: List<SecondaryPaymentInstrument>? = null,
    val skipRollback: Boolean? = null,
    val allowPartialSuccess: Boolean? = null,
    val clientReference: String? = null,
    val preferences: PaymentPreferences? = null,
    val transactionType: PaymentTransactionType? = null
)
