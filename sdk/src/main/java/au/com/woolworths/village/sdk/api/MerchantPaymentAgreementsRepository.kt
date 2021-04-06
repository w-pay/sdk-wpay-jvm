package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.ChargePaymentAgreementRequest
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
     */
    fun charge(
        paymentToken: String,
        chargePaymentAgreementRequest: ChargePaymentAgreementRequest
    ): ApiResult<DigitalPayPaymentAgreementResponse>

    /**
     * Delete a [PaymentAgreement] by its associated payment token
     *
     * @param paymentToken The ID.
     */
    fun delete(paymentToken: String): ApiResult<Unit>
}
