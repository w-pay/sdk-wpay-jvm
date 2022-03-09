package au.com.woolworths.village.sdk.model

import kotlinx.serialization.Serializable

/**
 * List of terms and conditions acceptances
 */
@Serializable
data class TermsAndConditionsAcceptances(
    /** An array of Ts and Cs the customer has accepted.. */
    val termsAndConditionsAcceptances: List<TermsAndConditionsAcceptance>
) : ModelType

/**
 * The Terms and Condtions the customer has accepted.
 */
@Serializable
data class TermsAndConditionsAcceptance(
    /** The type of the Ts and Cs. */
    val type: String,

    /** The version of the Ts and Cs. */
    val version: String,

    /** The timestamp when the shopper/customer agreed to the Everyday Pay Ts and Cs.  The timestamp format is milliseconds since epoch. */
    val timestamp: Long
) : ModelType

@Serializable
data class AcceptTermsAndConditionsRequest(
    /** The type of Ts and Cs that the shopper/customer has agreed to. */
    val type: String,

    /** The current version of the Ts and Cs that the shopper/customer has agreed to. */
    val version: String
) : ModelType
