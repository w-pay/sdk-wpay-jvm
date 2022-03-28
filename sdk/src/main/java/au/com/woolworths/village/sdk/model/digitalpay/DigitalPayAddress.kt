package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable

/**
 * A customer's address
 */
@Serializable
data class DigitalPayAddress(
    /** The recipient's first name. */
    val firstName: String,

    /** The recipient's last name. */
    val lastName: String,

    /** The recipient’s email address. */
    val email: String? = null,

    /** The recipient's company name. */
    val company: String? = null,

    /** The recipient's extended address line. */
    val extendedAddress: String? = null,

    /** The recipient's street address line. */
    val streetAddress: String,

    /** The recipient's suburb. */
    val suburb: String,

    /** The recipient's abbreviated state or territory. */
    val stateOrTerritory: String,

    /** The recipient's postal code */
    val postalCode: String,

    /** The recipient's Alpha-2 (2-character) ISO-3166-1 country code. */
    val countryCode: String,
) : ModelType
