package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.model.digitalpay.DigitalPayTransactionType
import java.math.BigDecimal

/**
 * Data required to charge against a [PaymentAgreement]
 *
 * @category Model
 */
interface ChargePaymentAgreementRequest {
    /**
     * The payment token of the payment agreement.
     *
     * The payment token is a unique identifier for the payment agreement.
     */
    val paymentToken: String

    /** The amount that will be charged against the payment instrument linked to the payment agreement. */
    val amount: BigDecimal

    /** A merchant application specific reference number for the transaction. */
    val clientReference: String

    /** A merchant order number for the transaction. */
    val orderNumber: String

    /** Transaction type containers to use for all instruments. */
    val transactionType: TransactionType

    /** A merchant application specific reference number for the customer. */
    val customerRef: String?
}
