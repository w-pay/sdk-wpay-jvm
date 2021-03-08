package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The JSON request structure of the Tokenize Apple Pay endpoint.
 *
 * @category Model
 */
interface TokenizeApplePayRequest {
	/* The "data" value from the Apple Pay wallet. */
	val data String

	/* The "ephemeralPublicKey" value from the Apple Pay wallet. */
	val ephemeralPublicKey String

	/* The "publicKeyHash" value from the Apple Pay wallet. */
	val publicKeyHash String

	/* The "transactionId" value from the Apple Pay wallet. */
	val transactionId String

	/* The "signature" value from the Apple Pay wallet. */
	val signature String

	/* The "version" value from the Apple Pay wallet. */
	val version String

	/* The type/schema value from the Apple Pay wallet. */
	val instrumentType String

	/* A flag to indicate if this payment instrument has to be set as the primary instrument. */
	val primary Boolean?

	/* The display text returned by the Apple Pay wallet. */
	val comment String

	/* The "applicationData" value from the Apple Pay wallet. */
	val applicationData String
}
