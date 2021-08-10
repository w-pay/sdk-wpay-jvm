package au.com.woolworths.village.sdk.model.walletmanagement

import java.io.Serializable

/**
 * The JSON response structure of the Initiate Card Capture endpoint.
 *
 * @category Model
 */
interface InitiateCardCaptureResponse : Serializable {
    /* The URL of an iframe. This iframe is used to capture a credit card number, expiry and CVV. */
    val cardCaptureURL: String

    /* Container reference in the transaction logs. This number uniquely identifies the transaction in the container. */
    val transactionRef: String
}
