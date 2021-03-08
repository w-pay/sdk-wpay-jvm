package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The JSON request structure of the Delete Wallet endpoint.
 *
 * @category Model
 */
interface WalletDeleteRequest {
	/* The IDM (Gigya) UID or merchant shopper id of the user. Do NOT use an email address! */
	val uid String

	/* The merchant shopper id of the user. */
	val shopperId String
}
