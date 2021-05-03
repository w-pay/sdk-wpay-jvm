package au.com.woolworths.village.sdk.model.digitalpay

import java.io.Serializable

/**
 * The JSON request structure of the Update Payment Agreement endpoint.
 *
 * @category Model
 */
interface DigitalPayUpdatePaymentAgreementRequest : Serializable {
    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the transaction in the merchant’s system.
     */
    val clientReference: String

    /**
     * A merchant application specific reference number.
     *
     * This number should uniquely identify the customer in the merchant’s system.
     */
    val customerRef: String?

    /** Customer billing address for this payment agreement */
    val billingAddress: DigitalPayAddress?

    /** Detail of the payment agreement to be created */
    val paymentAgreement: DigitalPayRequestPaymentAgreement?

    /** Digital pay fraud payload */
    val fraudPayload: DigitalPayFraudPayload?
}
