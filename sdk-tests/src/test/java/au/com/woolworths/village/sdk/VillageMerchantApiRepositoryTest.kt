package au.com.woolworths.village.sdk

import au.com.woolworths.village.sdk.data.*
import au.com.woolworths.village.sdk.matchers.*
import au.com.woolworths.village.sdk.openapi.OpenApiSdkFactory
import org.hamcrest.Matchers
import org.hamcrest.text.IsBlankString
import org.junit.Assert.assertThat
import org.junit.Test
import org.threeten.bp.OffsetDateTime

class VillageMerchantApiRepositoryTest {
    private val apiFactory = OpenApiSdkFactory()
    private val api = apiFactory.createMerchantApi().apply {
        setHost("http://localhost:8080")
    }

    @Test
    fun shouldRetrieveTransactions() {
        val startTime = OffsetDateTime.parse("2017-11-06T19:38:09.890+11:00")
        val endTime = OffsetDateTime.parse("2017-11-06T19:38:09.890+11:00")
        val pageSize = 20
        val page = 2

        val result = api.retrieveTransactions(page, pageSize, endTime, startTime)

        assertThat(result, isSuccessfulWith(merchantTransactionSummaries()))
    }

    @Test
    fun shouldRetrieveTransactionDetails() {
        val transactionId = "a5bbfe1a-c1b9-11ea-924f-33c96a9759eb"

        val result = api.retrieveTransactionDetails(transactionId)

        assertThat(result, isSuccessfulWith(merchantTransactionDetails()))
    }

    @Test
    fun shouldCreatePaymentRequestQRCode() {
        val result = api.createPaymentRequestQRCode(aNewPaymentRequestQRCode())

        assertThat(result, isSuccessfulWith(aQrCode()))
    }

    @Test
    fun shouldRetrievePaymentRequestQRCodeContent() {
        val qrCodeId = "a5bbfe1a-c1b9-11ea-924f-33c96a9759eb"

        val result = api.retrievePaymentRequestQRCodeContent(qrCodeId)

        assertThat(result, isSuccessfulWith(aQrCode()))
    }

    @Test
    fun shouldCancelPaymentQRCode() {
        val qrCodeId = "a5bbfe1a-c1b9-11ea-924f-33c96a9759eb"

        val result = api.cancelPaymentQRCode(qrCodeId)

        assertThat(result, isSuccessful())
    }

    @Test
    fun shouldRetrievePaymentList() {
        val type = "ALL"
        val pageSize = 50
        val page = 2

        val result = api.retrievePaymentList(type, page, pageSize)

        assertThat(result, isSuccessfulWith(merchantPaymentSummaries()))
    }

    @Test
    fun shouldCreateNewPaymentRequest() {
        val request = aNewPaymentRequest()

        val result = api.createNewPaymentRequest(request)

        assertThat(result, isSuccessfulWith(paymentRequestCreated()))
    }

    @Test
    fun shouldRetrievePaymentRequestDetails() {
        val paymentRequestId = "a5bbfe1a-c1b9-11ea-924f-33c96a9759eb"

        val result = api.retrievePaymentRequestDetails(paymentRequestId)

        assertThat(result, isSuccessfulWith(merchantPaymentDetails()))
    }

    @Test
    fun shouldDeletePaymentRequest() {
        val paymentRequestId = "a5bbfe1a-c1b9-11ea-924f-33c96a9759eb"

        val result = api.deletePaymentRequest(paymentRequestId)

        assertThat(result, isSuccessful())
    }

    @Test
    fun shouldRefundTransaction() {
        val transactionId = "a5bbfe1a-c1b9-11ea-924f-33c96a9759eb"

        val result = api.refundTransaction(transactionId, aNewTransactionRefund())

        assertThat(result, isSuccessfulWith(merchantTransactionSummary()))
    }

    @Test
    fun shouldRetrievePreferences() {
        val result = api.retrievePreferences()

        assertThat(result, isSuccessfulWith(
            Matchers.hasEntry(
                Matchers.equalTo("preferenceGroup"),
                Matchers.hasEntry(
                    Matchers.equalTo("preference"),
                    Matchers.not(IsBlankString.blankOrNullString())
                )
            )
        ))
    }

    @Test
    fun shouldSetPreferences() {
        val result = api.setPreferences(hashMapOf(
            "preferenceGroup" to hashMapOf(
                "preference" to "value"
            )
        ))

        assertThat(result, isSuccessful())
    }

    @Test
    fun shouldRetrieveSchemas() {
        val result = api.retrieveSchemas()

        assertThat(result, isSuccessfulWith(merchantSchemaSummaries()))
    }

    @Test
    fun shouldRetrieveSchemaDetails() {
        val schemaId = "a5bbfe1a-c1b9-11ea-924f-33c96a9759eb"

        val result = api.retrieveSchemaDetails(schemaId)

        assertThat(result, isSuccessfulWith(merchantSchema()))
    }

    @Test
    fun shouldCreateSchema() {
        val result = api.createSchema(aNewSchema())

        assertThat(result, isSuccessfulWith(merchantSchemaSummary()))
    }

    @Test
    fun shouldCreatePaymentSession() {
        val result = api.createPaymentSession(TestCreatePaymentSessionRequest())

        assertThat(result, isSuccessfulWith(paymentSessionCreated()))
    }

    @Test
    fun shouldRetrievePaymentSession() {
        val paymentSessionId = "a5bbfe1a-c1b9-11ea-924f-33c96a9759eb"

        val result = api.retrievePaymentSession(paymentSessionId)

        assertThat(result, isSuccessfulWith(paymentSession()))
    }

    @Test
    fun shouldCheckHealth() {
        val result = api.checkHealth()

        assertThat(result, isSuccessfulWith(healthyService()))
    }
}