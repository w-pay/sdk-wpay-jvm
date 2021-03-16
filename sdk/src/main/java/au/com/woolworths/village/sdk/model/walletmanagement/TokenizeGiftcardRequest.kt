package au.com.woolworths.village.sdk.model.walletmanagement

/**
 * The JSON request structure of the Tokenize Giftcard endpoint.
 *
 * @category Model
 */
interface TokenizeGiftcardRequest {
    /* The gift card number. */
    val cardNumber: String

    /* The gift card pin code. */
    val pinCode: String

    /* A flag to indicate if this payment instrument has to be set as the primary instrument. */
    val primary: Boolean?

    /* A flag to indicate if this payment instrument has to be saved in the container or tokenized for one-off use. */
    val save: Boolean?
}
