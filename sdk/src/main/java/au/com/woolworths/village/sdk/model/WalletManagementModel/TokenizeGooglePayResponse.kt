package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The JSON response structure of the Tokenize Google Pay endpoint.
 *
 * @category Model
 */
interface TokenizeGooglePayResponse {
	/** The new payment token to be used for payments. The payment token is a unique identifier for the payment instrument.*/
	val paymentToken String
}
