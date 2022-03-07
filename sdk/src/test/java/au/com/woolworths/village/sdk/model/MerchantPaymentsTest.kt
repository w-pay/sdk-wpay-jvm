package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.data.merchantPaymentDetailsDTO
import au.com.woolworths.village.sdk.data.merchantPaymentSummariesDTO
import au.com.woolworths.village.sdk.data.merchantPaymentSummaryDTO
import au.com.woolworths.village.sdk.matchers.merchantPaymentDetailsFrom
import au.com.woolworths.village.sdk.matchers.merchantPaymentSummariesFrom
import au.com.woolworths.village.sdk.matchers.merchantPaymentSummaryFrom
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