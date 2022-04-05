package au.com.wpay.sdk.model.digitalpay

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DigitalPayThreeDSTest: DescribeSpec({
    describe("DigitalPayThreeDS") {
        describe("Ares Status") {
            it("should convert code to enum") {
                DigitalPayThreeDS.AresStatus.values().forEach { status ->
                    DigitalPayThreeDS.AresStatus.fromCode(status.statusCode).shouldBe(status)
                }
            }

            it("should return null if code not recognised") {
                DigitalPayThreeDS.AresStatus.fromCode("ABC").shouldBe(null)
            }
        }
    }

    describe("Veres Enrolled") {
        it("should convert code to enum") {
            DigitalPayThreeDS.VeresEnrolled.values().forEach { status ->
                DigitalPayThreeDS.VeresEnrolled.fromCode(status.statusCode).shouldBe(status)
            }
        }

        it("should return null if code not recognised") {
            DigitalPayThreeDS.VeresEnrolled.fromCode("ABC").shouldBe(null)
        }
    }

    describe("Trans Status") {
        it("should convert code to enum") {
            DigitalPayThreeDS.TransStatus.values().forEach { status ->
                DigitalPayThreeDS.TransStatus.fromCode(status.statusCode).shouldBe(status)
            }
        }

        it("should return null if code not recognised") {
            DigitalPayThreeDS.TransStatus.fromCode("ABC").shouldBe(null)
        }
    }
})
