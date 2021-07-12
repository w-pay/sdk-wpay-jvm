package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.ChargePaymentAgreementRequest
import au.com.woolworths.village.sdk.model.FraudPayload
import au.com.woolworths.village.sdk.model.digitalpay.DigitalPayPaymentAgreementResponse

/**
 * @category API
 */
interface MerchantPaymentAgreementsRepository {
    /**
     * Charge a [PaymentAgreement]s
     *
     * @param paymentToken The ID.
     * @param chargePaymentAgreementRequest details of charge to make against the payment agreement
     * @param fraudPayload Used to complete the fraud check
     */
    fun charge(
        paymentToken: String,
        chargePaymentAgreementRequest: ChargePaymentAgreementRequest,
        fraudPayload: FraudPayload?
    ): ApiResult<DigitalPayPaymentAgreementResponse>

    /**
     * Delete a [PaymentAgreement] by its associated payment token
     *
     * @param paymentToken The ID.
     */
    fun delete(paymentToken: String): ApiResult<Unit>
}
