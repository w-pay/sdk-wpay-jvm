package au.com.wpay.sdk.model

import au.com.wpay.sdk.CurrencySerializer
import au.com.wpay.sdk.ISODateSerializer
import kotlinx.serialization.Serializable
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

interface MerchantPayments : ModelType

/**
 * List of payments made involving a merchant.
 */
@Serializable
data class MerchantPaymentSummaries(
    /** The resulting list of payments. */
    val payments: List<MerchantPaymentSummary>
) : MerchantPayments

interface MerchantPaymentSummaryType : PaymentType {
    /**
     * The number of times that the payment request can be used to create a payment.
     *
     * If absent then request can be used an unlimited number of times.
     */
    val usesRemaining: Int?

    /**
     * The Timestamp for when the payment request will expire and become unusable for payments.
     *
     * If absent then the payment request will not expire until it is deleted
     */
    val expiryTime: OffsetDateTime?

    /**
     * The ID of a specific wallet for which the payment is intended.
     *
     * If present then the payment can only be used by the intended wallet.
     * If absent then any wallet can create a payment against the Payment Request.
     */
    val specificWalletId: String?
}

/**
 * Summary information for a single Payment Request
 */
@Serializable
data class MerchantPaymentSummary(
    override val paymentRequestId: String,
    override val merchantReferenceId: String,

    @Serializable(with = CurrencySerializer::class)
    override val grossAmount: BigDecimal,
    override val usesRemaining: Int? = null,

    @Serializable(with = ISODateSerializer::class)
    override val expiryTime: OffsetDateTime? = null,
    override val specificWalletId: String? = null
) : MerchantPaymentSummaryType

/**
 * Detailed information for a single Payment Request
 */
@Serializable
data class MerchantPaymentDetails(
    override val paymentRequestId: String,
    override val merchantReferenceId: String,

    @Serializable(with = CurrencySerializer::class)
    override val grossAmount: BigDecimal,
    override val usesRemaining: Int? = null,
    @Serializable(with = ISODateSerializer::class)
    override val expiryTime: OffsetDateTime? = null,
    override val specificWalletId: String? = null,

    /** The [Basket] associated to the transaction. */
    val basket: Basket? = null,

    /** Optional extra details from the POS. */
    val posPayload: PosPayload? = null,

    /** Optional extra details from the merchant. */
    val merchantPayload: MerchantPayload? = null
) : MerchantPaymentSummaryType
