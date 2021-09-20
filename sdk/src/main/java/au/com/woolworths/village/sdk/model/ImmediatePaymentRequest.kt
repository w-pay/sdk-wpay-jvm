package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.model.digitalpay.PaymentTransactionType
import java.math.BigDecimal

/**
 * Detailed information for a payment being made.
 */
interface ImmediatePaymentRequest {
    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String

    /** The merchant order number of the transaction. */
    val orderNumber: String

    /** An optional flag allowing the consumer to indicate that automatic rollback step should be skipped in the case of failure */
    val skipRollback: Boolean?

    /** An optional flag allowing the consumer to indicate that a partial success will not trigger a failure and rollback */
    val allowPartialSuccess: Boolean?

    /** List of payment to be made as part of this transaction  */
    val payments: List<ImmediatePaymentItem>

    /** Payload describing the specific POS system.  This payload originates in the payment request and is carried with any resulting transactions.  Provided in a generic schema previous configured by the merchant */
    val posPayload: PosPayload?

    /** Payload provided by the merchant to support other types of messaging.  This payload originates in the payment request and is carried with any resulting transactions.  Provided in a generic schema previous configured by the merchant */
    val merchantPayload: MerchantPayload?

    /** The transaction types to use for each instrument type. */
    val transactionType: PaymentTransactionType?
}

interface ImmediatePaymentItem {
    /** The payment instrument id from the card capture iframe response or the list payment instruments response. */
    val paymentInstrumentId: String

    /** The amount you want to pay with the payment instrument. */
    val amount: BigDecimal
}