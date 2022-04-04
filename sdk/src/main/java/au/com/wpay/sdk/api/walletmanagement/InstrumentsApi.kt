package au.com.wpay.sdk.api.walletmanagement

import au.com.redcrew.apisdkcreator.httpclient.HttpRequest
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestMethod
import au.com.redcrew.apisdkcreator.httpclient.HttpRequestUrl
import au.com.redcrew.apisdkcreator.httpclient.arrow.pipe
import au.com.redcrew.apisdkcreator.httpclient.jsonUnmarshaller
import au.com.wpay.sdk.*
import au.com.wpay.sdk.model.walletmanagement.*

class InstrumentsApi(
    private val client: SdkApiClient,
    private val unmarshall: SdkJsonUnmarshaller
) {
    /**
     * Import a consumers credit cards (from WebPay) and paypal accounts to a new wallet. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param importPaymentInstrumentsRequest Details of the consumers credits and paypal accounts to be imported.
     */
    suspend fun import(
        importPaymentInstrumentsRequest: ImportPaymentInstrumentsRequest
    ): ApiResult<ImportPaymentInstrumentsResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(ImportPaymentInstrumentsResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instruments/import"),
            body = importPaymentInstrumentsRequest
        )))
    }

    /**
     * Verify if a provided payment instrument is valid and optionally perform a fraud check on the instrument.
     *
     * @param verifyPaymentInstrumentsRequest Details of the payment instrument to be verified.
     */
    suspend fun verify(
        verifyPaymentInstrumentsRequest: VerifyPaymentInstrumentsRequest
    ): ApiResult<VerifyPaymentInstrumentsSuccessResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(VerifyPaymentInstrumentsSuccessResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instruments/verify"),
            body = verifyPaymentInstrumentsRequest
        )))
    }

    /**
     * Get the stored payment intruments of a consumer.
     */
    suspend fun getList(): ApiResult<ListPaymentInstrumentsResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(ListPaymentInstrumentsResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest<Unit>(
            method = HttpRequestMethod.GET,
            url = HttpRequestUrl.String("/instruments")
        )))
    }

    /**
     * Get the stored payment intruments of a consumer. This API is IP restricted to allow unauthenticated server side calls.
     *
     * @param listPaymentInstrumentsRequest Details of the consumer to list payment instruments for.
     */
    suspend fun postList(
        listPaymentInstrumentsRequest: ListPaymentInstrumentsRequest
    ): ApiResult<ListPaymentInstrumentsResponse> {
        val unmarshaller = unmarshall(::jsonPassthrough)(ListPaymentInstrumentsResponse::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.POST,
            url = HttpRequestUrl.String("/instruments"),
            body = listPaymentInstrumentsRequest
        )))
    }

    /**
     * Delete a stored payment intrument of a consumer.
     *
     * @param paymentInstrumentId The id of the payment instrument to delete.
     */
    suspend fun delete(paymentInstrumentId: String): ApiResult<Unit> {
        val unmarshaller = unmarshall(::jsonPassthrough)(Unit::class)
        val pipe = client pipe resultHandler(jsonUnmarshaller(unmarshaller))

        return apiResult(pipe(HttpRequest(
            method = HttpRequestMethod.DELETE,
            url = HttpRequestUrl.String("/instruments/:paymentInstrumentId"),
            headers = emptyMap(),
            pathParams = mapOf(
                "paymentInstrumentId" to paymentInstrumentId
            ),
            queryParams = null,
            body = null
        )))
    }
}
