package au.com.woolworths.village.sdk.openapi.api

import au.com.woolworths.village.sdk.openapi.api.matchers.*
import au.com.woolworths.village.sdk.openapi.client.Configuration
import au.com.woolworths.village.sdk.openapi.dto.*

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.text.IsBlankString.blankOrNullString
import org.junit.BeforeClass
import org.junit.Test
import org.threeten.bp.OffsetDateTime

class CustomerApiTest {
    companion object {
        @BeforeClass
        @JvmStatic
        fun setupApiClient() {
            Configuration.getDefaultApiClient().basePath = "http://localhost:8080"
        }
    }

    private val api = CustomerApi()

    @Test
    fun getCustomerPaymentDetailsByPaymentIdTest() {
        val paymentRequestId = "abc123def"

        val result = api.getCustomerPaymentDetailsByPaymentId(paymentRequestId)
        assertThat(result.data, hasCustomerPaymentDetails())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun getCustomerPaymentDetailsByQRCodeIdTest() {
        val qrId = "abc123def"

        val result = api.getCustomerPaymentDetailsByQRCodeId(qrId)
        assertThat(result.data, hasCustomerPaymentDetails())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun getCustomerPaymentInstrumentsTest() {
        val instruments = api.customerPaymentInstruments

        assertThat(instruments.data, hasCreditCards())
        assertThat(instruments.data, hasGiftCards())
        assertThat(instruments.meta, not(nullValue()))
    }

    @Test
    fun getCustomerPreferencesTest() {
        val preferences = api.customerPreferences

        val preferenceGroup = preferences.data["preferenceGroup"]
        assertThat(preferenceGroup, not(nullValue()))
        assertThat(preferenceGroup?.get("preference"), not(blankOrNullString()))
    }

    @Test
    fun getCustomerTransactionDetailsTest() {
        val transactionId = "75ba5b0b-7e5d-47fe-9508-29ca69fdb1d5"

        val result = api.getCustomerTransactionDetails(transactionId)
        assertThat(result.data, hasCustomerTransactionDetails())
        assertThat(result.data.basket, hasBasketItems())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun getCustomerTransactionsTest() {
        val startTime = OffsetDateTime.parse("2017-11-06T19:38:09.890+11:00")
        val endTime = OffsetDateTime.parse("2017-11-06T19:38:09.890+11:00")
        val pageSize = 20
        val page = 2

        val results = api.getCustomerTransactions(startTime, endTime, pageSize, page)
        assertThat(results.data, hasCustomerTransactions())
        assertThat(results.meta, isPaginatedMeta())
    }

    @Test
    fun initiatePaymentInstrumentAdditionTest() {
        val data = CustomerInstrumentsData()
        data.clientReference = "75ba5b0b-7e5d-47fe-9508-29ca69fdb1d5"

        val instrumentAdditionDetails = InstrumentAdditionDetails()
        instrumentAdditionDetails.data = data

        val result = api.initiatePaymentInstrumentAddition(instrumentAdditionDetails)
        assertThat(result.data.cardCaptureURL, not(nullValue()))
        assertThat(result.data.transactionRef, not(nullValue()))
    }

    @Test
    fun makeCustomerPaymentTest() {
        val paymentRequestId = "75ba5b0b-7e5d-47fe-9508-29ca69fdb1d5"
        val customerPaymentDetails = CustomerPaymentDetails()
        customerPaymentDetails.data = CustomerPaymentsPaymentRequestIdData()
        customerPaymentDetails.data.primaryInstrumentId = "124456"
        customerPaymentDetails.data.secondaryInstruments = ArrayList()

        val result = api.makeCustomerPayment(paymentRequestId, customerPaymentDetails)
        assertThat(result.data, hasCustomerTransactionSummary())
        assertThat(result.meta, not(nullValue()))
    }

    @Test
    fun setCustomerPreferencesTest() {
        val customerPreferences = CustomerPreferences()
        customerPreferences.data = hashMapOf(
            "preferenceGroup" to hashMapOf(
                "preference" to "preference value"
            )
        ) as Map<String, Map<String, String>>

        api.setCustomerPreferences(customerPreferences)
    }
}