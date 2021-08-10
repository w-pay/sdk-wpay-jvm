package au.com.woolworths.village.sdk.model.walletmanagement

import java.io.Serializable

interface VerifyPaymentInstrumentsSuccessResponse : Serializable {
    /* Container reference in the transaction logs. This number uniquely identifies the whole/grouped transaction in the container. */
    val transactionReceipt: String

    /* Not in use. A property that will be used in future for multi-instrument verification.*/
    val partialSuccess: Boolean?

    val fraudResponse: FraudResponse
    val verifyResponses: List<VerifyResponse>
}

interface FraudResponse : Serializable {
    /* The fraud check client id. Will be null if the fraud check was skipped. */
    val clientId: String

    /* The fraud check reason code. Will be null if the fraud check was skipped. */
    val reasonCode: String

    /* The fraud check decision. Will be null if the fraud check was skipped. */
    val decision: String
}

interface VerifyResponse : Serializable {
    /* The payment token. */
    val paymentToken: String

    /* Container reference in the transaction logs. This number uniquely identifies the transaction in the container. */
    val verifyTransactionRef: String

    /* The external service code (from eg. Webpay). This property is only included in the response if it is enabled in the consumers API configuration. */
    val externalServiceCode: String

    /* The external service message (from eg. Webpay). This property is only included in the response if it is enabled in the consumers API configuration. */
    val externalServiceMessage: String
}
