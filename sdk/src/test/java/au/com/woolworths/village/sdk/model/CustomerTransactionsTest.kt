package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.data.customerTransactionDetailsDTO
import au.com.woolworths.village.sdk.data.customerTransactionSummariesDTO
import au.com.woolworths.village.sdk.data.customerTransactionSummaryDTO
import au.com.woolworths.village.sdk.matchers.customerTransactionDetailsFrom
import au.com.woolworths.village.sdk.matchers.customerTransactionSummariesFrom
import au.com.woolworths.village.sdk.matchers.customerTransactionSummaryFrom
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CustomerTransactionsTest : DescribeSpec({
    describe("Customer Transactions Transformers") {
        describe("CustomerTransactionSummaries") {
            describe("from DTO") {
                it("should convert dto") {
                    val dto = customerTransactionSummariesDTO()

                    val result = fromDTO<CustomerTransactionSummaries>(dto)
                    result.shouldBe(customerTransactionSummariesFrom(dto))
                }
            }
        }

        describe("CustomerTransactionSummary") {
            describe("from DTO") {
                it("should convert dto") {
                    val dto = customerTransactionSummaryDTO()

                    val result = fromDTO<CustomerTransactionSummary>(dto)
                    result.shouldBe(customerTransactionSummaryFrom(dto))
                }
            }
        }

        describe("CustomerTransactionDetails") {
            describe("from DTO") {
                it("should convert dto") {
                    val dto = customerTransactionDetailsDTO()

                    val result = fromDTO<CustomerTransactionDetails>(dto)
                    result.shouldBe(customerTransactionDetailsFrom(dto))
                }
            }
        }
    }
})