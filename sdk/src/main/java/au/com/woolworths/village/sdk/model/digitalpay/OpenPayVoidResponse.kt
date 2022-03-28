package au.com.woolworths.village.sdk.model.digitalpay

import au.com.woolworths.village.sdk.CurrencySerializer
import au.com.woolworths.village.sdk.model.ModelType
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * The JSON success response structure of the Openpay Voids endpoint.
 */
@Serializable
data class OpenPayVoidResponse(
    /** Container reference in the transaction logs. This number uniquely identifies the whole/grouped transaction in the container. */
    val transactionReceipt: String,

    /** List of void responses */
    val voidResponses: List<OpenPayVoidTransactionResponse>
) : ModelType

@Serializable
data class OpenPayVoidTransactionResponse(
    /** Container reference in the transaction logs. This number uniquely identifies the openpay transaction in the container. */
    val paymentTransactionRef: String,

    /** Container reference in the transaction logs. This number uniquely identifies the void transaction in the container. */
    val voidTransactionRef: String,

    /** The amount processed in the void. */
    @Serializable(with = CurrencySerializer::class)
    val amount: BigDecimal,

    /** This array is only included in the response if it is enabled in the consumers API configuration. */
    val extendedTransactionData: List<OpenPayExtendedTransactionData>? = null,

    /**
     * The external service code (from eg. Openpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceCode: String? = null,

    /**
     * The external service message (from eg. Openpay).
     *
     * This property is only included in the response if it is enabled in the consumers API configuration.
     */
    val externalServiceMessage: String? = null
) : ModelType
