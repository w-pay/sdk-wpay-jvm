package au.com.woolworths.village.sdk.model

import au.com.woolworths.village.sdk.data.basketDTO
import au.com.woolworths.village.sdk.matchers.basketFrom
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BasketTest : DescribeSpec({
    describe("Basket Transformer") {
        describe("from DTO") {
            it("should convert DTO") {
                val dto = basketDTO()

                val result: Basket = fromDTO(dto)
                result.shouldBe(basketFrom(dto))
            }
        }
    }
})