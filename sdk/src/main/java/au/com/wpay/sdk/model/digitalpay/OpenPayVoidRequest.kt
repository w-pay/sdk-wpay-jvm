package au.com.wpay.sdk.model.digitalpay

import au.com.wpay.sdk.model.ModelType
import kotlinx.serialization.Serializable

/**
 * The JSON request structure of the Openpay Voids endpoint.
 */
@Serializable
data class OpenPayVoidRequest(
    /** A merchant application specific reference number. This number should uniquely identify the transaction in the merchant’s system. */
    val clientReference: String,

    /** The merchant order number of the transaction. */
    val orderNumber: String? = null,

    /** List of voided payments */
    val voids: List<DigitalPayVoid>
) : ModelType
