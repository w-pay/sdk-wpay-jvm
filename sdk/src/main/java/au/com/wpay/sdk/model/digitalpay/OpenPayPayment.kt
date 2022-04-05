package au.com.wpay.sdk.model.digitalpay

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class OpenPayPaymentRequestTransactionType(
    val openPay: OpenPayTransactionType
) : ModelType

/**
 * The JSON request structure of the Openpay Payments endpoint
 */
@Serializable
data class OpenPayPaymentRequest(
    /** The container transaction type to use for openpay instruments */
    val transactionType: OpenPayPaymentRequestTransactionType,

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

    /** The channel from which this charge is originating, eg. Online, In-Store. */
    val channel: String,

    /** The unique identifier for the merchants trading account. */
    val tradingAccountId: String? = null,

    /** The merchants transaction date and time. The timestamp format is ISO8601. */
    val merchantTransactedAt: String? = null,

    /** List of payments */
    val payments: List<OpenPayPayments>,

    /** OpenPay store data */
    val storeData: OpenPayStoreData
) : ModelType

enum class OpenPayTransactionType {
    PREAUTH,
    PURCHASE
}

@Serializable
data class OpenPayStoreData(
    /** The payment transaction store id. */
    val storeId: String,

    /** A pin for the payment method id. */
    val pin: String
) : ModelType

@Serializable
data class OpenPayPayments(
    /** The payment token. */
    val paymentToken: String,

    /** The amount you want to pay with the payment instrument. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** The GST amount of the full amount you want to pay with the payment instrument. */
    @Serializable(with = CurrencySerializer::class)
    val gstAmount: BigDecimal? = null
) : ModelType

/**
 * The JSON success response structure of the Payments endpoint.
 */
@Serializable
data class OpenPayPaymentResponse(
    /**
     * Container reference in the transaction logs.
     *
     *  This number uniquely identifies the whole/grouped transaction in the container.
     */
    val transactionReceipt: String,

    /** List of OpenPay payment responses */
    val paymentResponses: List<OpenPayPaymentResponseDetails>
) : ModelType

@Serializable
data class OpenPayPaymentResponseDetails(
    /** The payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String,

    /** Container reference in the transaction logs. This number uniquely identifies the openpay transaction in the container. */
    val paymentTransactionRef: String,

    /** This array is only included in the response if it is enabled in the consumers API configuration. */
    val extendedTransactionData: List<OpenPayExtendedTransactionData>? = null,

    /**
     * The external service code (from eg. Openpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String? = null,

    /**
     * The external service message (from eg. Openpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String? = null
) : ModelType

@Serializable
data class OpenPayExtendedTransactionData(
    /**
     * The name of the extended transaction data field.
     *
     * The 'token' field is only included in the response if it is enabled in the consumers API configuration.
     */
    val field: OpenPayExtendedTransactionDataFieldName,

    /** The value of the extended transaction data field. */
    val value: String
) : ModelType

enum class OpenPayExtendedTransactionDataFieldName {
    OPEN_PAY_TRANSACTION_ID,
    OPEN_PAY_ORDER_ID,
    OPEN_PAY_PAYMENT_METHOD,
    OPEN_PAY_CREATED_AT,
    OPEN_PAY_BILLING_ACCOUNT_ID,
    OPEN_PAY_BILLING_ACCOUNT_NAME,
    OPEN_PAY_BILLING_ACCOUNT_ABN
}
