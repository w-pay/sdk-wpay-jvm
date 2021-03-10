package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.CreatePaymentSessionRequest
import au.com.woolworths.village.sdk.model.CreatePaymentSessionResult
import au.com.woolworths.village.sdk.model.MerchantUpdatePaymentSessionRequest
import au.com.woolworths.village.sdk.model.PaymentSession

interface MerchantPaymentSessionsRepository {
    /**
     * Create a new [PaymentSession]
     *
     * @param request The details for the new session.
     */
    fun create(request: CreatePaymentSessionRequest): ApiResult<CreatePaymentSessionResult>

    /**
     * Retrieve a [PaymentSession]
     *
     * @param paymentSessionId The ID of the payment session to retrieve.
     */
    fun getById(paymentSessionId: String): ApiResult<PaymentSession>

    /**
     * Update a [PaymentSession]
     *
     * @param paymentSessionId The payment session to update
     * @param session The details to update the session with
     */
    fun update(
        paymentSessionId: String,
        session: MerchantUpdatePaymentSessionRequest
    ): ApiResult<Unit>

    /**
     * Delete a [PaymentSession]
     *
     * @param paymentSessionId The payment session to delete.
     */
    fun delete(paymentSessionId: String): ApiResult<Unit>
}