package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The JSON request structure of the Tokenize Android Pay endpoint.
 *
 * @category Model
 */

interface TokenizeAndroidPayRequest {
	/** The "encryptedMessage" value from the Android Pay wallet.*/
	val encryptedMessage String

	/** The "ephemeralPublicKey" value from the Android Pay wallet. */
	val ephemeralPublicKey String

	/** The "tag" value from the Android Pay wallet. */
	val tag String

	/** The "publicKeyHash" value from the merchant profile response. */
	val publicKeyHash String

	/** The type/schema value from the Android Pay wallet. */
	val instrumentType String

	/** A flag to indicate if this payment instrument has to be set as the primary instrument. */
	val primary Boolean?

	/** The display text returned by the Android Pay wallet. */
	val comment String
}
