package au.com.wpay.sdk.model.walletmanagement

import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable

/**
 * The JSON request structure of the Initiate Card Capture endpoint.
 */
@Serializable
data class InitiateCardCaptureRequest(
    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String
) : ModelType

/**
 * The JSON response structure of the Initiate Card Capture endpoint.
 */
@Serializable
data class InitiateCardCaptureResponse(
    /** The URL of an iframe. This iframe is used to capture a credit card number, expiry and CVV. */
    val cardCaptureURL: String,

    /** Container reference in the transaction logs. This number uniquely identifies the transaction in the container. */
    val transactionRef: String
) : ModelType
