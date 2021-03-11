package au.com.woolworths.village.sdk.model

import java.io.Serializable

/**
 * A customer's address
 *
 * @category Model
 */
interface DigitalPayAddress: Serializable {
	/** The recipient's first name. */
	val firstName: String

	/** The recipient's last name. */
	val lastName: String

	/** The recipient’s email address. */
	val email: String?

	/** The recipient's company name. */
	val company: String?

	/** The recipient's extended address line. */
	val extendedAddress: String?

	/** The recipient's street address line. */
	val streetAddress: String

	/** The recipient's suburb. */
	val suburb: String

	/** The recipient's abbreviated state or territory. */
	val stateOrTerritory: String

	/** The recipient's postal code */
	val postalCode: String

	/** The recipient's Alpha-2 (2-character) ISO-3166-1 country code. */
	val countryCode: String
}
