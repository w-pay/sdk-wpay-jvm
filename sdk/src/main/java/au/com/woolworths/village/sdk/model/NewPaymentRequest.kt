package au.com.woolworths.village.sdk.model

import java.math.BigDecimal

/**
 * The details of the payment to be created.
 */
interface NewPaymentRequest {
    /** The unique reference for the payment */
    val merchantReferenceId: String

    /** The gross amount to be paid. Must be positive */
    val grossAmount: BigDecimal

    /** Whether a [QRCode] should be created and returned in the response */
    val generateQR: Boolean

    /**
     * The number of times that the payment request can be used to create a payment.
     *
     * If absent, the API will default value to 1.
     *
     * If set to 0 then the request can be used an unlimited number of times.
     */
    val maxUses: Int?

    /**
     * The time in seconds that the payment request should remain valid.
     *
     * After this time any use of the request to create a payment will fail.
     *
     * If absent, the API will default value to 0 which indicates that the payment request will not expire until it is deleted
     */
    val timeToLivePayment: Int?

    /**
     * The time in seconds that the QR code should remain valid.
     *
     * After this time any use of the request to create a payment will fail.
     *
     * If absent, the API will default value to 0 which indicates that the code will not expire until it is deleted
     */
    val timeToLiveQR: Int?

    /**
     * The ID of a specific wallet for which the payment is intended.
     *
     * Can be used in scenarios where a customer has previously requested that their wallet ID is retained for repeat purchase.
     *
     * If supplied the payment can only be used by the intended wallet.
     *
     * If absent then any wallet can create a payment against the Payment Request
     */
    val specificWalletId: String?

    /** The [Basket] associated to the transaction. */
    val basket: Basket?

    /** Optional extra details from the POS. */
    val posPayload: PosPayload?

    /** Optional extra details from the merchant. */
    val merchantPayload: MerchantPayload?
}