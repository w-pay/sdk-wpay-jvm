package au.com.woolworths.village.sdk.model.walletmanagement

/**
 * The JSON request structure of the Initiate Card Capture endpoint.
 *
 * @category Model
 */
interface InitiateCardCaptureRequest {
    /* A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String
}
