package au.com.woolworths.village.sdk.model.walletmanagement

import java.io.Serializable

interface TokenizeGooglePayRequest : Serializable {
    /** The type/schema value from the Google Pay wallet. */
    val instrumentType: String

    /** The display text returned by the Google Pay wallet. */
    val comment: String

    /** The "tokenData" payload from the Google Pay wallet. */
    val tokenData: String
}
