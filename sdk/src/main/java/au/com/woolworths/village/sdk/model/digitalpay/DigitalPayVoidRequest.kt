package au.com.woolworths.village.sdk.model.digitalpay

import java.io.Serializable

/**
 * The JSON request structure of the Voids endpoint.
 *
 * @category Model
 */
interface DigitalPayVoidRequest : Serializable {
    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String

    /** The merchant order number of the transaction. */
    val orderNumber: String

    /** List of voided payments */
    val voids: List<DigitalPayVoid>
}

interface DigitalPayVoid {
    /** Container reference in the transaction logs. This number uniquely identifies the payment transaction in the container. */
    val paymentTransactionRef: String
}
