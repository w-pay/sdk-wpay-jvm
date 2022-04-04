package au.com.wpay.sdk.model

import au.com.wpay.sdk.data.merchantTransactionDetailsDTO
import au.com.wpay.sdk.data.merchantTransactionSummariesDTO
import au.com.wpay.sdk.data.merchantTransactionSummaryDTO
import au.com.wpay.sdk.matchers.merchantTransactionDetailsFrom
import au.com.wpay.sdk.matchers.merchantTransactionSummariesFrom
import au.com.wpay.sdk.matchers.merchantTransactionSummaryFrom
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MerchantTransactionsTest : DescribeSpec({
    describe("Merchant Transactions Transformers") {
        describe("MerchantTransactionSummaries") {
            describe("from DTO") {
                it("should convert dto") {
                    val dto = merchantTransactionSummariesDTO()

                    val result = fromDTO<MerchantTransactionSummaries>(dto)
                    result.shouldBe(merchantTransactionSummariesFrom(dto))
                }
            }
        }

        describe("MerchantTransactionSummary") {
            describe("from DTO") {
                it("should convert dto") {
                    val dto = merchantTransactionSummaryDTO()

                    val result = fromDTO<MerchantTransactionSummary>(dto)
                    result.shouldBe(merchantTransactionSummaryFrom(dto))
                }
            }
        }

        describe("MerchantTransactionDetails") {
            describe("from DTO") {
                it("should convert dto") {
                    val dto = merchantTransactionDetailsDTO()

                    val result = fromDTO<MerchantTransactionDetails>(dto)
                    result.shouldBe(merchantTransactionDetailsFrom(dto))
                }
            }
        }
    }
})
