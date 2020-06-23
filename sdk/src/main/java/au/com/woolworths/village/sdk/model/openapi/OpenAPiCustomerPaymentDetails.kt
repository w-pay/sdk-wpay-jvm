package au.com.woolworths.village.sdk.model.openapi

import au.com.woolworths.village.sdk.model.Basket
import au.com.woolworths.village.sdk.model.CustomerPaymentDetails
import java.math.BigDecimal

class OpenApiCustomerPaymentDetails(
    private val customerPaymentDetails: au.com.woolworths.village.sdk.dto.CustomerPaymentDetail
): CustomerPaymentDetails {

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