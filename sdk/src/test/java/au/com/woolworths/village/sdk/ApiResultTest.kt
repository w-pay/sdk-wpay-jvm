package au.com.woolworths.village.sdk

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ApiResultTest : DescribeSpec({
    describe("ApiResult") {
        it("should distinguish error type") {
            var distinguishedError = false

            when(val result = causeAnError()) {
                is ApiResult.Error -> {
                    when(result.error) {
                        is JsonParsingError -> distinguishedError = true
                    }
                }
                else -> {}
            }

            distinguishedError.shouldBe(true)
        }

        describe("HttpFailureError") {
            it("should match invalid input status code") {
                val error = HttpFailureError(400, emptyMap(), "")

                error.message.shouldBe("Invalid Input")
            }

            it("should match unauthorized status code") {
                val error = HttpFailureError(401, emptyMap(), "")

                error.message.shouldBe("Unauthorized")
            }

            it("should match processing error status code") {
                val error = HttpFailureError(422, emptyMap(), "")

                error.message.shouldBe("Processing Error")
            }

            it("should match server error status code") {
                val error = HttpFailureError(500, emptyMap(), "")

                error.message.shouldBe("Server Error")
            }

            it("should default to server error when status code unrecognised") {
                val error = HttpFailureError(503, emptyMap(), "")

                error.message.shouldBe("Server Error")
            }
        }
    }
})

fun causeAnError(): ApiResult<Any> {
    return ApiResult.Error(JsonParsingError("Something went wrong"))
}
