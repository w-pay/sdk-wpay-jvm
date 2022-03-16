package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.data.walletContentsDTO
import au.com.woolworths.village.sdk.matchers.walletContentsFrom
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PaymentInstrumentsTest : DescribeSpec({
    describe("PaymentInstrument Transformers") {
        describe("from DTO") {
            it("should convert from dto") {
                val dto = walletContentsDTO()
                val result = fromDTO<WalletContents>(dto)

                result.shouldBe(walletContentsFrom(dto))
            }
        }
    }
})