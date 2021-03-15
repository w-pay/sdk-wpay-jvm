package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.Wallet
import java.io.Serializable

/**
 * Initiate the addition of a new payment instrument for this customer.
 *
 * This API returns a URL to be used to access the DigiPay IFrame based interface to request the customer to enter a payment instrument details.
 */
interface PaymentInstrumentAddition : Serializable {
    /** The unique reference for this interaction as defined by the client application */
    val clientReference: String

    /** Which wallet to store the new instrument in */
    val wallet: Wallet
}