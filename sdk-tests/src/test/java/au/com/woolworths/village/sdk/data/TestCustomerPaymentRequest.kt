package au.com.woolworths.village.sdk.data

import au.com.woolworths.village.sdk.model.Basket
import au.com.woolworths.village.sdk.model.CustomerPaymentRequest
import java.math.BigDecimal

fun aNewCustomerPaymentRequest(): CustomerPaymentRequest = TestCustomerPaymentRequest()

class TestCustomerPaymentRequest: CustomerPaymentRequest {
    override fun merchantId(): String {
        return "abc123"
    }

    override fun basket(): Basket? {
        return aNewBasket()
    }

    override fun paymentRequestId(): String {
        return "def456"
    }

    override fun merchantReferenceId(): String {
        return "hij789"
    }

    override fun grossAmount(): BigDecimal {
        return BigDecimal.TEN
    }
}