package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.DigitalPayChargePaymentAgreementRequest
import au.com.woolworths.village.sdk.model.DigitalPayCreatePaymentAgreementRequest
import au.com.woolworths.village.sdk.model.DigitalPayPaymentAgreementResponse
import au.com.woolworths.village.sdk.model.DigitalPayUpdatePaymentAgreementRequest

/**
 * @category API
 */
interface PaymentAgreementApiRepository {
    /**
     * Create a new payment agreement which will be added to the users wallet after validating the payment instrument.
     *
     * This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param paymentAgreementRequest detail of payment agreement to be created
     */
    fun create(
        paymentAgreementRequest: DigitalPayCreatePaymentAgreementRequest
    ): ApiResult<DigitalPayPaymentAgreementResponse>

    /**
     * Update an existing payment agreement and validate the payment instrument if changed.
     *
     * This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param paymentToken The payment agreement to update
     * @param paymentAgreementRequest detail of payment agreement to be updated
     */
    fun update(
        paymentToken: String,
        paymentAgreementRequest: DigitalPayUpdatePaymentAgreementRequest
    ): ApiResult<DigitalPayPaymentAgreementResponse>

    /**
     * Perform charge transaction against a payment agreement.
     *
     * This service will use the provided information to perform the charge transaction.
     *
     * A charge payment is made by the merchant to charge a customer as per their payment agreement.
     *
     * This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param chargeRequest detail of payment agreement to be charged
     */
    fun charge(
        chargeRequest: DigitalPayChargePaymentAgreementRequest
    ): ApiResult<DigitalPayPaymentAgreementResponse>

    /**
     * Delete an existing payment agreement.
     *
     * This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param paymentToken The payment agreement to delete
     */
    fun delete(paymentToken: String): ApiResult<Void>
}
