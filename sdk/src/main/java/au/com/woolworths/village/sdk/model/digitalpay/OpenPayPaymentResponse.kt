package au.com.woolworths.village.sdk.model.digitalpay

import java.io.Serializable

/**
 * The JSON success response structure of the Payments endpoint.
 *
 * @category Model
 */
interface OpenPayPaymentTransactionResponse : Serializable {
    /**
     * Container reference in the transaction logs.
     *
     *  This number uniquely identifies the whole/grouped transaction in the container.
     */
    val transactionReceipt: String

    /** List of OpenPay payment responses */
    val paymentResponses: List<OpenPayPaymentResponse>
}

interface OpenPayPaymentResponse : Serializable {
    /** The payment token. The payment token is a unique identifier for the payment instrument. */
    val paymentToken: String

    /** Container reference in the transaction logs. This number uniquely identifies the openpay transaction in the container. */
    val paymentTransactionRef: String

    /** This array is only included in the response if it is enabled in the consumers API configuration. */
    val extendedTransactionData: List<OpenPayExtendedTransactionData>?

    /**
     * The external service code (from eg. Openpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String?

    /**
     * The external service message (from eg. Openpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String?
}

interface OpenPayExtendedTransactionData : Serializable {
    /**
     * The name of the extended transaction data field.
     *
     * The 'token' field is only included in the response if it is enabled in the consumers API configuration.
     */
    val field: OpenPayExtendedTransactionDataFieldName

    /** The value of the extended transaction data field. */
    val value: String
}

enum class OpenPayExtendedTransactionDataFieldName {
    OPEN_PAY_TRANSACTION_ID,
    OPEN_PAY_ORDER_ID,
    OPEN_PAY_PAYMENT_METHOD,
    OPEN_PAY_CREATED_AT,
    OPEN_PAY_BILLING_ACCOUNT_ID,
    OPEN_PAY_BILLING_ACCOUNT_NAME,
    OPEN_PAY_BILLING_ACCOUNT_ABN
}
