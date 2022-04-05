package au.com.wpay.sdk.model

import au.com.wpay.sdk.data.merchantPaymentDetailsDTO
import au.com.wpay.sdk.data.merchantPaymentSummariesDTO
import au.com.wpay.sdk.data.merchantPaymentSummaryDTO
import au.com.wpay.sdk.matchers.merchantPaymentDetailsFrom
import au.com.wpay.sdk.matchers.merchantPaymentSummariesFrom
import au.com.wpay.sdk.matchers.merchantPaymentSummaryFrom
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MerchantPaymentsTest : DescribeSpec({
    describe("Merchant Payments Transformers") {
        describe("MerchantPaymentSummaries") {
            describe("from DTO") {
                it("should convert dto") {
                    val dto = merchantPaymentSummariesDTO()

                    val result = fromDTO<MerchantPaymentSummaries>(dto)
                    result.shouldBe(merchantPaymentSummariesFrom(dto))
                }
            }
        }

        describe("MerchantPaymentSummary") {
            describe("from DTO") {
                it("should convert dto") {
                    val dto = merchantPaymentSummaryDTO()

                    val result = fromDTO<MerchantPaymentSummary>(dto)
                    result.shouldBe(merchantPaymentSummaryFrom(dto))
                }
            }
        }

        describe("MerchantPaymentDetails") {
            describe("from DTO") {
                it("should convert dto") {
                    val dto = merchantPaymentDetailsDTO()

                    val result = fromDTO<MerchantPaymentDetails>(dto)
                    result.shouldBe(merchantPaymentDetailsFrom(dto))
                }
            }
        }
    }
})
