package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.*

/**
 * @category API
 */
interface PaymentApiRepository {
    /**
     * Make payments to a merchant using payment intruments.
     *
     * @param paymentRequest detail of payment to be made
     */
    fun pay(paymentRequest: DigitalPayPaymentRequest): ApiResult<DigitalPayPaymentResponse>

    /**
     * Make guest payments to a merchant using guest payment intruments.
     *
     * @param paymentRequest detail of payment to be made
     */
    fun guestPayment(paymentRequest: DigitalPayPaymentRequest): ApiResult<DigitalPayPaymentResponse>

    /**
     * Complete pre-authed Openpay payments. This API is IP restricted to allow unauthenticated server side calls.
     *
     *  @param completionRequest detail of payment to be completed
     */
    fun complete(
        completionRequest: DigitalPayCompletionRequest
    ): ApiResult<DigitalPayCompletionResponse>

    /**
     * Void (cancel) pre-authed Openpay payments. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param voidRequest detail of payment to be voided
     */
    fun voidPayment(voidRequest: DigitalPayVoidRequest): ApiResult<DigitalPayVoidResponse>

    /**
     * Make payments to a merchant using Openpay payment tokens.
     *
     * @param refundRequest detail of payment to be refunded
     */
    fun refund(refundRequest: DigitalPayRefundRequest): ApiResult<DigitalPayRefundResponse>
}
