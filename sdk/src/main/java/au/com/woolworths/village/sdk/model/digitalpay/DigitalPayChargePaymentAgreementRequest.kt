package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Charge Payment Agreement endpoint.
 */
@Serializable
data class DigitalPayChargePaymentAgreementRequest(
    /** Transaction type containers to use for all instruments. */
    val transactionType: PaymentTransactionType,

    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String,

    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the customer in the merchant’s system.
     */
    val customerRef: String? = null,

    /** The merchant order number of the transaction. */
    val orderNumber: String,

    /**
     * The payment token of the payment agreement.
     *
     * The payment token is a unique identifier for the payment agreement.
     */
    val paymentToken: String,

    /** The amount that will be charged against the payment instrument linked to the payment agreement. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** Digital Pay fraud payload */
    val fraudPayload: DigitalPayFraudPayload? = null
) : ModelType
