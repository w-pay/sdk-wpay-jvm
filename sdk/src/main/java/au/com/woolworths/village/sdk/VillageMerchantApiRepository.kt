package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.model.*
import org.threeten.bp.OffsetDateTime

/**
 * Map of merchant preferences.
 */
typealias MerchantPreferences = Map<String, Map<String, String>>

/**
 * Defines the API operations that the SDK can use to call the Merchant Village API
 *
 * The SDK is technology agnostic with applications being able to choose an implementation that
 * meets the needs and preexisting technology choices of the application.
 *
 * Implementations of the protocol may provide additional constraints on the user.
 */
interface VillageMerchantApiRepository: VillageAdministrationApiRepository {
    /**
     * Retrieve a list of previously executed transactions with the merchant.
     *
     * @param page The page of results to return with 1 indicating the first page (defaults to 1).
     * @param pageSize The number of records to return for this page (current default is 25)
     * @param endTime If present, transactions newer than this time will not be returned.
     * @param startTime If present, transactions older than this time will not be returned
     */
    fun retrieveTransactions(
        page: Int?,
        pageSize: Int?,
        endTime: OffsetDateTime?,
        startTime: OffsetDateTime?
    ): ApiResult<MerchantTransactionSummaries>

    /**
     * Retrieve details about a specific transaction
     *
     * @param transactionId The transaction id
     */
    fun retrieveTransactionDetails(transactionId: String): ApiResult<MerchantTransactionDetails>

    /**
     * Create a new QR code for an existing payment request
     *
     * @param details The details for the new QR code.
     */
    fun createPaymentRequestQRCode(details: NewPaymentRequestQRCode): ApiResult<QRCode>

    /**
     * Retrieve a [QRCode] that is associated to a Payment Request by its ID
     *
     * @param qrCodeId The ID to use.
     */
    fun retrievePaymentRequestQRCodeContent(qrCodeId: String): ApiResult<QRCode>

    /**
     * Cancels a QR code making it unusable
     *
     * @param qrCodeId The ID of the QR code to cancel.
     */
    fun cancelPaymentQRCode(qrCodeId: String): ApiResult<Unit>

    /**
     * Retrieve a list of the payments initiated by the merchant, both pending and complete
     *
     * @param type The type of payment requests to return
     * @param page The page of results to return with 1 indicating the first page (defaults to 1).
     * @param pageSize The number of records to return for this page (current default is 25)
     */
    fun retrievePaymentList(
        type: String?,
        page: Int?,
        pageSize: Int?
    ): ApiResult<MerchantPaymentSummaries>

    /**
     * Create a new payment request for a customer
     *
     * @param paymentRequest The details of the new payment request
     */
    fun createNewPaymentRequest(paymentRequest: NewPaymentRequest): ApiResult<CreatePaymentRequestResult>

    /**
     * Retrieve a payment request by it's ID. The request may have been "completed" that is the customer has made a payment.
     *
     * @param paymentRequestId The ID of the payment request to return.
     */
    fun retrievePaymentRequestDetails(paymentRequestId: String): ApiResult<MerchantPaymentDetails>

    /**
     * Delete a payment request
     *
     * @param paymentRequestId The payment request to delete
     */
    fun deletePaymentRequest(paymentRequestId: String): ApiResult<Unit>

    /**
     * Refund a transaction to a customer
     *
     * @param transactionId The transaction to refund.
     * @param refundDetails The details of the refund.
     */
    fun refundTransaction(
        transactionId: String,
        refundDetails: TransactionRefundDetails
    ): ApiResult<MerchantTransactionSummary>

    /**
     * Retrieve a merchant's preferences.
     */
    fun retrievePreferences(): ApiResult<MerchantPreferences>

    /**
     * Update a merchant's preferences
     *
     * @param preferences The preferences to use
     */
    fun setPreferences(preferences: MerchantPreferences): ApiResult<Unit>

    /**
     * Retrieve the list of currently usable schemas previously added for the merchant
     */
    fun retrieveSchemas(): ApiResult<MerchantSchemaSummaries>

    /**
     * Retrieve details for a specific schema
     *
     * @param schemaId The schema to retrieve
     */
    fun retrieveSchemaDetails(schemaId: String): ApiResult<MerchantSchema>

    /**
     * Create a new schema for the merchant
     *
     * @param schema The schema definition
     */
    fun createSchema(schema: MerchantSchema): ApiResult<MerchantSchemaSummary>

    /**
     * Create a new [PaymentSession]
     *
     * @param request The details for the new session.
     */
    fun createPaymentSession(request: CreatePaymentSessionRequest): ApiResult<CreatePaymentSessionResult>

    /**
     * Retrieve a [PaymentSession]
     *
     * @param paymentSessionId The ID of the payment session to retrieve.
     */
    fun retrievePaymentSession(paymentSessionId: String): ApiResult<PaymentSession>

    /**
     * Update a [PaymentSession]
     *
     * @param paymentSessionId The payment session to update
     * @param session The details to update the session with
     */
    fun updatePaymentSession(paymentSessionId: String, session: MerchantUpdatePaymentSessionRequest): ApiResult<Unit>

    /**
     * Delete a [PaymentSession]
     *
     * @param paymentSessionId The payment session to delete.
     */
    fun deletePaymentSession(paymentSessionId: String): ApiResult<Unit>
}