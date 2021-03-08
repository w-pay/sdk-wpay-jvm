package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * The JSON request structure of the Openpay Voids endpoint.
 *
 * @category Model
 */
interface OpenPayVoidRequest: Serializable {
	/** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
	val clientReference String

	/** The merchant order number of the transaction. */
	val orderNumber String?

	/** List of voided payments */
	val voids List<DigitalPayVoid>
}
