package au.com.woolworths.village.sdk.model.digitalpay

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("DigitalPayThreeDS")
class DigitalPayThreeDSTest {
    @Nested
    @DisplayName("Ares Status")
    inner class AresStatusTest {
        @Test
        fun `should convert code to enum`() {
            DigitalPayThreeDS.AresStatus.values().forEach { status ->
                assertThat(DigitalPayThreeDS.AresStatus.fromCode(status.statusCode), equalTo(status))
            }
        }

        @Test
        fun `should return null if code not recognised`() {
            assertThat(DigitalPayThreeDS.AresStatus.fromCode("ABC"), equalTo(null))
        }
    }

    @Nested
    @DisplayName("Veres Enrolled")
    inner class VeresEnrolledTest {
        @Test
        fun `should convert code to enum`() {
            DigitalPayThreeDS.VeresEnrolled.values().forEach { status ->
                assertThat(DigitalPayThreeDS.VeresEnrolled.fromCode(status.statusCode), equalTo(status))
            }
        }

        @Test
        fun `should return null if code not recognised`() {
            assertThat(DigitalPayThreeDS.VeresEnrolled.fromCode("ABC"), equalTo(null))
        }
    }

    @Nested
    @DisplayName("Trans Status")
    inner class TransStatusTest {
        @Test
        fun `should convert code to enum`() {
            DigitalPayThreeDS.TransStatus.values().forEach { status ->
                assertThat(DigitalPayThreeDS.TransStatus.fromCode(status.statusCode), equalTo(status))
            }
        }

        @Test
        fun `should return null if code not recognised`() {
            assertThat(DigitalPayThreeDS.TransStatus.fromCode("ABC"), equalTo(null))
        }
    }
}