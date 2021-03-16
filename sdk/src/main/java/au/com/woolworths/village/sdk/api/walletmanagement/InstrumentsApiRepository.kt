package au.com.woolworths.village.sdk.api.walletmanagement

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.walletmanagement.*

/**
 * @category API
 */
interface InstrumentsApiRepository {
    /**
     * Import a consumers credit cards (from WebPay) and paypal accounts to a new wallet. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param importPaymentInstrumentsRequest Details of the consumers credits and paypal accounts to be imported. */
    fun import(
        importPaymentInstrumentsRequest: ImportPaymentInstrumentsRequest
    ): ApiResult<ImportPaymentInstrumentsResponse>

    /**
     * Verify if a provided payment instrument is valid and optionally perform a fraud check on the instrument.
     *
     * @param verifyPaymentInstrumentsRequest Details of the payment instrument to be verified.
     */
    fun verify(
        verifyPaymentInstrumentsRequest: VerifyPaymentInstrumentsRequest
    ): ApiResult<VerifyPaymentInstrumentsSuccessResponse>

    /**
     * Get the stored payment intruments of a consumer.
     */
    fun getList(): ApiResult<ListPaymentInstrumentsResponse>

    /**
     * Get the stored payment intruments of a consumer. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param listPaymentInstrumentsRequest Details of the consumer to list payment instruments for.
     */
    fun postList(
        listPaymentInstrumentsRequest: ListPaymentInstrumentsRequest
    ): ApiResult<ListPaymentInstrumentsResponse>

    /**
     * Delete a stored payment intrument of a consumer.
     *
     * @param paymentInstrumentId The id of the payment instrument to delete.
     */
    fun delete(paymentInstrumentId: String): ApiResult<Map<String, Any>>
}
