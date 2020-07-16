package au.com.woolworths.village.sdk.openapi.api

import au.com.woolworths.village.sdk.openapi.api.data.basketWithItem
import au.com.woolworths.village.sdk.openapi.api.matchers.*
import au.com.woolworths.village.sdk.openapi.client.Configuration
import au.com.woolworths.village.sdk.openapi.dto.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.text.IsBlankString.blankOrNullString
import org.junit.BeforeClass
import org.junit.Test
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

class MerchantApiTest {
    companion object {
        @BeforeClass
        @JvmStatic
        fun setupApiClient() {
            Configuration.getDefaultApiClient().basePath = "http://localhost:8080"
        }
    }

    private val api = MerchantApi()

    @Test
    fun cancelPaymentQRCodeTest() {
        val qrId = "dca8edc5-bbb7-44c0-8056-a5daf4327601"

        api.cancelPaymentQRCode(
            X_WALLET_ID,
            qrId
        )
    }

    @Test
    fun createMerchantSchemaTest() {
        val schema = MerchantSchema()
        schema.data = MerchantSchemaData()
        schema.data.type = "MERCHANT"
        schema.data.description = "Schema to capture information about the sale"
        schema.data.schema = hashMapOf(
            "type" to "object",
            "required" to arrayListOf("channel", "campaign"),
            "properties" to hashMapOf(
                "channel" to hashMapOf(
                    "type" to "string"
                )
            )
        ) as Map<String, Any>

        val result = api.createMerchantSchema(
            X_WALLET_ID,
            schema
        )

        assertThat(result.data.type, equalTo(schema.data.type))
        assertThat(result.data.description, equalTo(schema.data.description))
        assertThat(result.data.schemaId, not(blankOrNullString()))
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun createPaymentQRCodeTest() {
        val paymentQRCodeDetails = PaymentQRCodeDetails()
        paymentQRCodeDetails.data = MerchantQrData()
        paymentQRCodeDetails.data.referenceId = "1075839a-8923-11ea-bb06-6b3f2264ccd7"
        paymentQRCodeDetails.data.referenceType = MerchantQrData.ReferenceTypeEnum.SESSION
        paymentQRCodeDetails.data.timeToLive = 300

        val result = api.createPaymentQRCode(
            X_WALLET_ID,
            paymentQRCodeDetails
        )

        assertThat(result.data, isQrCode())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun createPaymentRequestTest() {
        val merchantPaymentRequest = MerchantPaymentRequest()
        merchantPaymentRequest.data = MerchantPaymentsData()
        merchantPaymentRequest.data.merchantReferenceId = "123456"
        merchantPaymentRequest.data.grossAmount = BigDecimal(12000)
        merchantPaymentRequest.data.generateQR = true
        merchantPaymentRequest.data.maxUses = 1
        merchantPaymentRequest.data.timeToLivePayment = 300
        merchantPaymentRequest.data.timeToLiveQR = 60
        merchantPaymentRequest.data.specificWalletId = ""
        merchantPaymentRequest.data.basket = basketWithItem()

        merchantPaymentRequest.data.posPayload = PosPayload().apply {
            schemaId = "abc123def0"
            payload = HashMap()
        }

        merchantPaymentRequest.data.merchantPayload = MerchantPayload().apply {
            schemaId = "abc123def0"
            payload = HashMap()
        }

        val result = api.createPaymentRequest(
            X_WALLET_ID,
            merchantPaymentRequest
        )

        assertThat(result.data.paymentRequestId, not(blankOrNullString()))
        assertThat(result.data.qr, isQrCode())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun deleteMerchantPaymentTest() {
        val paymentRequestId = "2c6ffba4-8912-11ea-80d7-1766761cedbe"

        api.deleteMerchantPayment(X_WALLET_ID, paymentRequestId)
    }

    @Test
    fun getMerchantPaymentDetailsTest() {
        val paymentRequestId = "2c6ffba4-8912-11ea-80d7-1766761cedbe"
        val result = api.getMerchantPaymentDetails(X_WALLET_ID, paymentRequestId)

        assertThat(result.data, hasMerchantPaymentDetails())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun getMerchantPaymentsTest() {
        val type = "ALL"
        val pageSize = 50
        val page = 2

        val result = api.getMerchantPayments(
            X_WALLET_ID,
            type,
            pageSize,
            page
        )

        assertThat(result.data.payments.size, greaterThanOrEqualTo(1))
        assertThat(result.data.payments, hasItems(withMerchantPaymentSummary()))
        assertThat(result.meta, isPaginatedMeta())
    }

    @Test
    fun getMerchantPreferencesTest() {
        val preferences = api.getMerchantPreferences(X_WALLET_ID)

        val preferenceGroup = preferences.data["preferenceGroup"]
        assertThat(preferenceGroup, not(nullValue()))
        assertThat(preferenceGroup?.get("preference"), not(blankOrNullString()))
    }

    @Test
    fun getMerchantSchemaTest() {
        val schemas = api.getMerchantSchemas(X_WALLET_ID)

        assertThat(schemas.data.schemas.size, greaterThanOrEqualTo(1))
        assertThat(schemas.data.schemas, hasItems(withSchemaSummary()))
        assertThat(schemas.meta, isPaginatedMeta())
    }

    @Test
    fun getMerchantSchemaDetailsTest() {
        val schemaId = "f13fc2fb-bfe1-4ec2-92e8-78f0f6dfced0"
        val result = api.getMerchantSchemaDetails(
            X_WALLET_ID,
            schemaId
        )

        assertThat(result.data.type, not(blankOrNullString()))
        assertThat(result.data.description, not(blankOrNullString()))
        assertThat(result.data.schema, not(nullValue()))
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun getMerchantTransactionDetailsTest() {
        val transactionId = "0756eaaf-34cf-456f-a870-6da2bf4761a9"
        val result = api.getMerchantTransactionDetails(
            X_WALLET_ID,
            transactionId
        )

        assertThat(result.data, hasMerchantTransactionDetails())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun getMerchantTransactionsTest() {
        val startTime = OffsetDateTime.parse("2017-11-06T19:38:09.890+11:00")
        val endTime = OffsetDateTime.parse("2017-11-06T19:38:09.890+11:00")
        val pageSize = 20
        val page = 2

        val result = api.getMerchantTransactions(
            X_WALLET_ID,
            startTime,
            endTime,
            pageSize,
            page
        )

        assertThat(result.data.transactions.size, greaterThanOrEqualTo(1))
        assertThat(result.data.transactions, hasItem(withMerchantTransactionSummary()))
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun getPaymentQRCodeContentTest() {
        val qrId = "60006a56-892d-11ea-9d87-f3644d81edc2"
        val results = api.getPaymentQRCodeContent(
            X_WALLET_ID,
            qrId
        )

        assertThat(results.data, isQrCode())
        assertThat(results.meta, not(nullValue()))
    }

    @Test
    fun refundMerchantTransactionTest() {
        val transactionId = "60006a56-892d-11ea-9d87-f3644d81edc2"
        val refundMerchantTransactionRequest = RefundMerchantTransactionRequest()
        refundMerchantTransactionRequest.data = MerchantTransactionsTransactionIdRefundData()
        refundMerchantTransactionRequest.data.reason = "Customer returned item"

        val result = api.refundMerchantTransaction(
            X_WALLET_ID,
            transactionId,
            refundMerchantTransactionRequest
        )

        assertThat(result.data, hasMerchantTransactionSummary())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun setMerchantPreferencesTest() {
        val merchantPreferences = MerchantPreferences()
        merchantPreferences.data = hashMapOf(
            "preferenceGroup" to hashMapOf(
                "preference" to "preference value"
            )
        ) as Map<String, Map<String, String>>

        api.setMerchantPreferences(
            X_WALLET_ID,
            merchantPreferences
        )
    }

    @Test
    fun createPaymentSessionTest() {
        val createRequest = CreatePaymentSessionRequest()
        createRequest.data = MerchantPaymentSessionData()
        createRequest.data.location = "somewhere"
        createRequest.data.additionalInfo = DynamicPayload()

        val result = api.createCustomerPaymentSession(
            X_WALLET_ID,
            createRequest
        )

        assertThat(result.data, hasPaymentSessionCreated())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun shouldRetrievePaymentSession() {
        val paymentSessionId = "a5bbfe1a-c1b9-11ea-924f-33c96a9759eb"

        val result = api.getMerchantPaymentSession(
            X_WALLET_ID,
            paymentSessionId
        )

        assertThat(result.data, hasPaymentSession())
        assertThat(result.meta, not(nullValue()))
    }
}