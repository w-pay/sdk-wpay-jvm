package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON request structure of the Openpay Refunds endpoint.
 */
@Serializable
data class OpenPayRefundRequest(
    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String,

    /** The merchant order number of the transaction. */
    val orderNumber: String? = null,

    /** The merchants transaction date and time. The timestamp format is ISO8601. */
    val merchantTransactedAt: String? = null,

    /** List of refunds */
    val refunds: List<OpenPayRefund>,

    val storeData: StoreData? = null
) : ModelType

@Serializable
data class StoreData(
    /** The refund transaction store id. */
    val storeId: String
) : ModelType

@Serializable
data class OpenPayRefund(
    /**
     * Container reference in the transaction logs.
     *
     * This number uniquely identifies the payment transaction in the container.
     */
    val paymentTransactionRef: String,

    /** The amount you want to refund. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** The GST amount of the amount you want to refund. */
    @Serializable(with = CurrencySerializer::class)
    val gstAmount: BigDecimal? = null,

    /** The reason or justification for the refund. */
    val reason: String? = null
) : ModelType
