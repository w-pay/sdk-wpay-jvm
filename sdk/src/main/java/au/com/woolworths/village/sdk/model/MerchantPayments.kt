package au.com.woolworths.village.sdk.model

import org.threeten.bp.OffsetDateTime
import java.io.Serializable

interface MerchantPayments : Serializable {
}


/**
 * List of payments made involving a merchant.
 */
interface MerchantPaymentSummaries : MerchantPayments {
    /** The resulting list of payments. */
    val payments: List<MerchantPaymentSummary>
}

/**
 * Summary information for a single Payment Request
 */
interface MerchantPaymentSummary : Payment {
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
 * Detailed information for a single Payment Request
 */
interface MerchantPaymentDetails : MerchantPaymentSummary {
    /** The [Basket] associated to the transaction. */
    val basket: Basket?

    /** Optional extra details from the POS. */
    val posPayload: PosPayload?

    /** Optional extra details from the merchant. */
    val merchantPayload: MerchantPayload?
}