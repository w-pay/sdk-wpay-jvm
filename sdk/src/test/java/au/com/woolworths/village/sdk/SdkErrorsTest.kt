package au.com.woolworths.village.sdk

import au.com.redcrew.apisdkcreator.httpclient.*
import au.com.woolworths.village.sdk.matchers.apiError
import au.com.woolworths.village.sdk.matchers.httpClientError
import au.com.woolworths.village.sdk.matchers.jsonMarshallingError
import au.com.woolworths.village.sdk.matchers.jsonUnmarshallingError
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SdkErrorsTest : DescribeSpec({
    describe("sdk errors") {
        it("should convert http client error") {
            val message = "Error calling server"
            val cause = Any()
            val error = SdkError(HTTP_CLIENT_ERROR_TYPE, message, cause)

            toApiError(error).shouldBe(httpClientError(message, cause))
        }

        it("should convert marshalling error") {
            val message = "Can't marshall data"
            val cause = Any()
            val error = SdkError(MARSHALLING_ERROR_TYPE, message, cause)

            toApiError(error).shouldBe(jsonMarshallingError(message, cause))
        }

        it("should convert unmarshalling error") {
            val message = "Can't unmarshall data"
            val cause = Any()
            val error = SdkError(UNMARSHALLING_ERROR_TYPE, message, cause)

            toApiError(error).shouldBe(jsonUnmarshallingError(message, cause))
        }

        it("should convert missing prop error") {
            val error = SdkError(MISSING_PROP_ERROR_TYPE, "key")

            toApiError(error).shouldBe(jsonUnmarshallingError(error.message, error))
        }

        it("should return generic error") {
            val error = SdkError(ILLEGAL_STATE_ERROR_TYPE, "Something bad happened", Any())

            toApiError(error).shouldBe(apiError(error.message, error))
        }
    }
})