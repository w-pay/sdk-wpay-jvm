package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.data.createPaymentSessionResultDTO
import au.com.woolworths.village.sdk.data.paymentSessionDTO
import au.com.woolworths.village.sdk.matchers.paymentSessionCreatedFrom
import au.com.woolworths.village.sdk.matchers.paymentSessionFrom
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PaymentSessionTest : DescribeSpec({
    describe("Payment Session Transformers") {
        describe("CreatePaymentSessionResult") {
            /*
             * This will only ever be a result from the API
             */
            describe("from DTO") {
                it("should dto to create payment session result") {
                    val dto = createPaymentSessionResultDTO()

                    val result = fromDTO<CreatePaymentSessionResult>(dto)
                    result.shouldBe(paymentSessionCreatedFrom(dto))
                }
            }
        }

        describe("PaymentSession") {
            describe("from DTO") {
                it("should convert dto to payment session") {
                    val dto = paymentSessionDTO()

                    val result = fromDTO<PaymentSession>(dto)
                    result.shouldBe(paymentSessionFrom(dto))
                }
            }
        }
    }
})