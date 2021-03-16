package au.com.woolworths.village.sdk.api.digitalpay

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.digitalpay.*

/**
 * @category API
 */
interface OpenPayApiRepository {
    /**
     * Make payments to a merchant using Openpay payment tokens.
     *
     * @param paymentRequest detail of payment to be made
     */
    fun pay(paymentRequest: OpenPayPaymentRequest): ApiResult<OpenPayPaymentResponse>

    /**
     * Complete pre-authed Openpay payments. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param completionRequest detail of payment to be completed
     */
    fun complete(completionRequest: OpenPayCompletionRequest): ApiResult<OpenPayCompletionResponse>

    /**
     * Void (cancel) pre-authed Openpay payments. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param voidRequest detail of payment to be voided
     */
    fun voidPayment(voidRequest: OpenPayVoidRequest): ApiResult<OpenPayVoidResponse>

    /**
     * Make payments to a merchant using Openpay payment tokens.
     *
     * @param refundRequest detail of payment to be refunded
     */
    fun refund(refundRequest: OpenPayRefundRequest): ApiResult<OpenPayRefundResponse>
}
