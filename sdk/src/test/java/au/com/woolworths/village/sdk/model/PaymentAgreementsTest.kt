package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.data.paymentAgreementDTO
import au.com.woolworths.village.sdk.data.paymentAgreementsDTO
import au.com.woolworths.village.sdk.matchers.paymentAgreementFrom
import au.com.woolworths.village.sdk.matchers.paymentAgreementsFrom
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PaymentAgreementsTest: DescribeSpec({
    describe("Payment Agreement Transformer") {
        describe("Payment Agreements") {
            describe("from DTO") {
                it("should convert DTO") {
                    val dto = paymentAgreementsDTO()

                    val result = fromDTO<PaymentAgreements>(dto)
                    result.shouldBe(paymentAgreementsFrom(dto))
                }
            }
        }

        describe("Payment Agreement") {
            describe("from DTO") {
                it("should convert DTO") {
                    val dto = paymentAgreementDTO()

                    val result = fromDTO<PaymentAgreement>(dto)
                    result.shouldBe(paymentAgreementFrom(dto))
                }
            }
        }
    }
})