package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.data.customerPaymentRequestDTO
import au.com.woolworths.village.sdk.matchers.customerPaymentRequestFrom
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PaymentRequestTest : DescribeSpec({
    describe("Customer Payment Request Transformer") {
        describe("from DTO") {
            it("should convert DTO") {
                val dto = customerPaymentRequestDTO()

                val result: CustomerPaymentRequest = fromDTO(dto)
                result.shouldBe(customerPaymentRequestFrom(dto))
            }
        }
    }
})