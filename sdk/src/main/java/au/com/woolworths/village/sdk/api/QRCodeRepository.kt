package au.com.woolworths.village.sdk.api

import au.com.woolworths.village.sdk.ApiResult
import au.com.woolworths.village.sdk.model.NewPaymentRequestQRCode
import au.com.woolworths.village.sdk.model.QRCode

interface QRCodeRepository {
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
    fun getPaymentRequestQRCodeContent(qrCodeId: String): ApiResult<QRCode>

    /**
     * Cancels a QR code making it unusable
     *
     * @param qrCodeId The ID of the QR code to cancel.
     */
    fun cancelPaymentQRCode(qrCodeId: String): ApiResult<Unit>
}