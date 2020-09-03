package au.com.woolworths.village.sdk

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test

class ApiResultTest {
    @Test
    fun shouldDistinguishErrorType() {
        var distinguishedError = false

        when(val result = causeAnError()) {
            is ApiResult.Error -> {
                when(result.e) {
                    is JsonParsingException -> distinguishedError = true
                }
            }
        }

        assertThat(distinguishedError, equalTo(true))
    }

    @Test
    fun shouldMatchInvalidInputStatusCode() {
        val error = HttpErrorException(400, emptyMap(), "")

        assertThat(error.message, equalTo("Invalid Input"))
    }

    @Test
    fun shouldMatchUnauthorizedStatusCode() {
        val error = HttpErrorException(401, emptyMap(), "")

        assertThat(error.message, equalTo("Unauthorized"))
    }

    @Test
    fun shouldMatchProcessingErrorStatusCode() {
        val error = HttpErrorException(422, emptyMap(), "")

        assertThat(error.message, equalTo("Processing Error"))
    }

    @Test
    fun shouldMatchServerErrorStatusCode() {
        val error = HttpErrorException(500, emptyMap(), "")

        assertThat(error.message, equalTo("Server Error"))
    }

    @Test
    fun shouldDefaultToServerErrorWhenStatusCodeUnrecognised() {
        val error = HttpErrorException(503, emptyMap(), "")

        assertThat(error.message, equalTo("Server Error"))
    }
}

fun causeAnError(): ApiResult<Any> {
    return ApiResult.Error(JsonParsingException("Something went wrong"))
}
