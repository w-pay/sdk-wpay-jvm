package au.com.woolworths.village.sdk.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * List of terms and conditions acceptances
 */
interface TermsAndConditionsAcceptances : Serializable {
    /** An array of Ts and Cs the customer has accepted.. */
    val termsAndConditionsAcceptances: List<TermsAndConditionsAcceptance>
}

/**
 * The Terms and Condtions the customer has accepted.
 */
interface TermsAndConditionsAcceptance : Serializable {
    /** The type of the Ts and Cs. */
    val type: String

    /** The version of the Ts and Cs. */
    val version: String

    /** The timestamp when the shopper/customer agreed to the Everyday Pay Ts and Cs.  The timestamp format is milliseconds since epoch. */
    val timestamp: BigDecimal
}

interface AcceptTermsAndConditionsRequest : Serializable {
    /** The type of Ts and Cs that the shopper/customer has agreed to. */
    val type: String

    /** The current version of the Ts and Cs that the shopper/customer has agreed to. */
    val version: String
}
