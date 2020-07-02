package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.Basket
import au.com.woolworths.village.sdk.model.CustomerPaymentRequest
import java.math.BigDecimal

class OpenApiCustomerPaymentRequest(
    private val customerPaymentDetails: au.com.woolworths.village.sdk.openapi.dto.CustomerPaymentDetail
): CustomerPaymentRequest {

    override fun paymentRequestId(): String {
        return customerPaymentDetails.paymentRequestId
    }

    override fun merchantReferenceId(): String {
        return customerPaymentDetails.merchantReferenceId
    }

    override fun grossAmount(): BigDecimal {
        return customerPaymentDetails.grossAmount
    }

    override fun merchantId(): String {
        return customerPaymentDetails.merchantId
    }

    override fun basket(): Basket? {
        return customerPaymentDetails.basket?.let { OpenApiBasket(it) }
    }
}