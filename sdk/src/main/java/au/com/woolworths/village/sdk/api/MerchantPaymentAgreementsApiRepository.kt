package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.ChargePaymentAgreementRequest
import au.com.woolworths.village.sdk.model.DigitalPayPaymentAgreementResponse

/**
 * @category API
 */
interface MerchantPaymentAgreementsApi {
    /**
     * Charge a {@link PaymentAgreement}s
     *
     * @param paymentToken The ID.
     * @param chargePaymentAgreementRequest details of charge to make against the payment agreement
     */
    fun charge(
        paymentToken: String,
        chargePaymentAgreementRequest: ChargePaymentAgreementRequest
    ): ApiResult<DigitalPayPaymentAgreementResponse>

    /**
     * Delete a {@link PaymentAgreement} by its associated payment token
     *
     * @param paymentToken The ID.
     */
    fun delete(paymentToken: String): ApiResult<Void>
}
