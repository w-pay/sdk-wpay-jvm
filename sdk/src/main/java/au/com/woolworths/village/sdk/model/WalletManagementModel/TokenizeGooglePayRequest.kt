package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The JSON request structure of the Tokenize Google Pay endpoint.
 *
 * @category Model
 */
interface TokenizeGooglePayRequest {
	/** The type/schema value from the Google Pay wallet. */
	val instrumentType: String

	/** The display text returned by the Google Pay wallet. */
	val comment: String

	/** The "tokenData" payload from the Google Pay wallet. */
	val tokenData: String
}
