package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.CurrencySerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * Detailed information for a single Payment Request
 */
@Serializable
data class CustomerPaymentRequest(
    override val paymentRequestId: String,
    override val merchantReferenceId: String,

    @Serializable(with = CurrencySerializer::class)
    override val grossAmount: BigDecimal,

    /** The ID of the merchant associated with this transaction */
    val merchantId: String,

    /** The [Basket] associated to this Payment Request` */
    val basket: Basket? = null
) : PaymentType