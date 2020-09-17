package au.com.woolworths.village.sdk.model

/**
 * Request containing the details of the [PaymentSession]
 */
interface CreatePaymentSessionRequest {
    /** The location of the payment session (used to group payment sessions). */
    val location: String

    /** Payload used to pass merchant information to the customer. */
    val merchantInfo: DynamicPayload

    /**
     * Flag indicating whether a QR code should be created and returned in the response.
     *
     * @returns `false` by default
     */
    val generateQR: Boolean
        get() = false

    /**
     * The time in seconds that the payment request should remain valid
     *
     * Default value is 0 which indicates that the code will not expire until it is deleted
     *
     * @returns: `0` by default
     */
    val timeToLivePaymentSession: Int
        get() = 0

    /**
     * The time in seconds that the QR code should remain valid.
     *
     * Default value is 0 which indicates that the code will not expire until it is deleted
     *
     * @returns `0` by default
     */
    val timeToLiveQR: Int
        get() = 0
}