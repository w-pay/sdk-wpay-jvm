package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.*

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
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     * @param fraudPayload Used to complete the fraud check
     */
    fun create(
        paymentAgreement: CreatePaymentAgreementRequest,
        challengeResponses: List<ChallengeResponse>?,
        fraudPayload: FraudPayload?
    ): ApiResult<PaymentAgreement>

    /**
     * Update a [PaymentAgreement]
     *
     * @param paymentToken The payment token to update
     * @param paymentAgreement The updates to apply to the payment agreement
     * @param challengeResponses Used when needing to complete challenge(s) to complete payment.
     * @param fraudPayload Used to complete the fraud check
     */
    fun update(
        paymentToken: String,
        paymentAgreement: UpdatePaymentAgreementRequest,
        challengeResponses: List<ChallengeResponse>?,
        fraudPayload: FraudPayload?
    ): ApiResult<PaymentAgreement>
}
