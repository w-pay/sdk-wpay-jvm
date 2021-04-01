package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.CreatePaymentAgreementRequest
import au.com.woolworths.village.sdk.model.PaymentAgreement
import au.com.woolworths.village.sdk.model.PaymentAgreements
import au.com.woolworths.village.sdk.model.UpdatePaymentAgreementRequest

/**
 * @category API
 */
interface CustomerPaymentAgreementsApiRepository {
    /**
     * Retrieve a list of customer's [PaymentAgreement]s
     */
    fun list(): ApiResult<PaymentAgreements>

    /**
     * Retrieve a [PaymentAgreement] by its associated payment token
     *
     * @param paymentToken The ID.
     */
    fun getById(paymentToken: String): ApiResult<PaymentAgreement>

    /**
     * Create a [PaymentAgreement]
     *
     * @param paymentAgreement The details for the new payment agreement
     */
    fun create(paymentAgreement: CreatePaymentAgreementRequest): ApiResult<PaymentAgreement>

    /**
     * Update a [PaymentAgreement]
     *
     * @param paymentToken The payment token to update
     * @param paymentAgreement The updates to apply to the payment agreement
     */
    fun update(
        paymentToken: String,
        paymentAgreement: UpdatePaymentAgreementRequest
    ): ApiResult<PaymentAgreement>
}
