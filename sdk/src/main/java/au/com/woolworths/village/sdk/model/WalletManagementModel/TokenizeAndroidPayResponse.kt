package au.com.woolworths.village.sdk.model

/**
 * The JSON response structure of the Tokenize Android Pay endpoint.
 *
 * @category Model
 */
interface TokenizeAndroidPayResponse {
    /** The new payment instrument id to be used for payments. */
    val paymentInstrumentId: String

    /** The step-up token to be used for payments. */
    val stepUpToken: String
}
